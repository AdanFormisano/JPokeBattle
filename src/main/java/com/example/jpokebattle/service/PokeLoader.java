package com.example.jpokebattle.service;

import com.example.jpokebattle.poke.Pokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PokeLoader implements PokeService {
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private List<Pokemon> pokemonsCache;

    public PokeLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
        this.pokemonsCache = new ArrayList<>();
    }

    @Override
    public List<Pokemon> loadAllPokemons() {
        if (this.pokemonsCache.isEmpty()) {
            try {
                this.pokemonsCache = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<Pokemon>>(){});
            } catch (IOException e) {
                System.out.println("Error loading pokemons from JSON file: " + e.getMessage());
            }
        }
        return this.pokemonsCache;
    }

    @Override
    public Pokemon getPokemonById(int id) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Pokemon> getPokemonByType(String type) {
        return this.loadAllPokemons().stream().filter(pokemon -> pokemon.getType().equals(type)).collect(Collectors.toList());
    }
}
