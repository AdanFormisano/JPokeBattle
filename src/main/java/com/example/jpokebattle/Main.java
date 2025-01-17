package com.example.jpokebattle;

import com.example.jpokebattle.service.PokeLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        PokeLoader pokeLoader = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemon.json");
        pokeLoader.loadAllPokemons();

    }
}
