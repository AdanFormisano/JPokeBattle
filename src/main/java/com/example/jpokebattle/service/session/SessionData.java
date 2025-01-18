package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Player;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;

/*
    * This class is used to store the data of the current session
    * It contains the current player data, the current pokemon data, and the current move data
 */
public class SessionData {
    private Player player = new Player("Player", 0, 0);    // TODO: Implement input for player name
    private DataPokemon[] playerDataPokemons = new DataPokemon[6];
    private DataPokemon[] enemyDataPokemons = new DataPokemon[6];

    private int currentLvl = 1; // Current level of the session
    private final MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
    private final PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

    public SessionData() {
        // For testing purposes the player and enemy pokemons are hardcoded
        this.playerDataPokemons[0] = pl.getPokemonByName("Bulbasaur");
    }

    public DataPokemon[] getPlayerPokemons() { return playerDataPokemons; }

}
