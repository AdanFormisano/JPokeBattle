package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.poke.Pokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PokeLoader {
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private static List<Pokemon> pokemonsCache;
    private static volatile boolean isCacheLoaded = false;

    public PokeLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Pokemon> loadAllPokemons() {
        if (!isCacheLoaded) {
            synchronized (PokeLoader.class) {
                if (!isCacheLoaded) {
                    try {
                        List<Pokemon> loadedPokemons = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<Pokemon>>(){});
                        pokemonsCache = Collections.unmodifiableList(loadedPokemons);
                        isCacheLoaded = true;
                    } catch (IOException e) {
                        System.out.println("Error loading pokemons from JSON file: " + e.getMessage());
                    }
                }
            }
        }
        return pokemonsCache;
    }

    public Pokemon getPokemonById(int id) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getId() == id).findFirst().orElse(null);
    }

    public Pokemon getPokemonByName(String name) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Pokemon> getPokemonByType(String type) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getType().equals(type)).collect(Collectors.toList());
    }
}
