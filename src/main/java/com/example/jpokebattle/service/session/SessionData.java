package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Player;
import com.example.jpokebattle.game.Trainer;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;

/*
    * This class is used to store the data of the current session
    * It contains the current player data, the current pokemon data, and the current move data
 */
public class SessionData {
    public Player player;    // TODO: Implement input for player name
    public Trainer trainer = new Trainer("Trainer", 0);
    public Pokemon[] playerPokemons = new Pokemon[6];
    public Pokemon[] enemyPokemons = new Pokemon[6];

    private int currentLvl = 1; // Current level of the session
    private final MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
    private final PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

    public SessionData() {
        // For testing purposes the player and enemy pokemons are hardcoded
        playerPokemons[0] = new Pokemon(pl.getPokemonByName("Bulbasaur"));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
