package com.example.jpokebattle.service;

import com.example.jpokebattle.poke.Pokemon;

import java.util.List;

public interface PokeService {
    List<Pokemon> loadAllPokemons();
    Pokemon getPokemonById(int id);
    Pokemon getPokemonByName(String name);
    List<Pokemon> getPokemonByType(String type);
}

