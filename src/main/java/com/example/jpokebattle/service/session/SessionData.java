package com.example.jpokebattle.service.session;

import com.example.jpokebattle.poke.Move;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;

import java.util.List;

/*
    * This class is used to store the data of the current session
    * It contains the current player data, the current pokemon data, and the current move data
 */
public class SessionData {
//    private Player player;
    private Pokemon[] playerPokemons = new Pokemon[6];
    private Pokemon[] enemyPokemons = new Pokemon[6];

    private int currentLvl = 1; // Current level of the session
    private final MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
    private final PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

    public SessionData() {
        // For testing purposes the player and enemy pokemons are hardcoded
        this.playerPokemons[0] = pl.getPokemonByName("Bulbasaur");
    }

    public Pokemon[] getPlayerPokemons() { return playerPokemons; }

}
