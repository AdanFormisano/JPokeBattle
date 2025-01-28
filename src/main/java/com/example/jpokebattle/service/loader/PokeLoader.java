package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.data.DataType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class PokeLoader {
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private static List<DataPokemon> pokemonsCache;
    private static volatile boolean isCacheLoaded = false;
    private static final RandomGenerator random = RandomGenerator.getDefault();

    public PokeLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<DataPokemon> loadAllPokemons() {
        if (!isCacheLoaded) {
            synchronized (PokeLoader.class) {
                if (!isCacheLoaded) {
                    try {
                        List<DataPokemon> loadedDataPokemons = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<DataPokemon>>(){});
                        pokemonsCache = Collections.unmodifiableList(loadedDataPokemons);
                        isCacheLoaded = true;
                    } catch (IOException e) {
                        System.out.println("Error loading pokemons from JSON file: " + e.getMessage());
                    }
                }
            }
        }
        return pokemonsCache;
    }

    public DataPokemon getPokemonById(int id) {
        return this.loadAllPokemons().stream().filter(dataPokemon -> dataPokemon.getId() == id).findFirst().orElse(null);
    }

    public DataPokemon getPokemonByName(String name) {
        return this.loadAllPokemons().stream().filter(dataPokemon -> dataPokemon.getName().equals(name)).findFirst().orElse(null);
    }

    public List<DataPokemon> getPokemonByType(List<DataType> types) {
        return this.loadAllPokemons().stream().filter(dataPokemon -> dataPokemon.getType().equals(types)).collect(Collectors.toList());
    }

    public DataPokemon getRandomPokemon() {
        return this.loadAllPokemons().get((int) random.nextInt(this.loadAllPokemons().size()));
    }
}
