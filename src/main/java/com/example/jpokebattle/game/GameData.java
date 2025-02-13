package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;

import java.util.ArrayList;
import java.util.List;

/*
    * This class is used to store the data of the current session
    * It contains the current player data, the current pokemon data, and the current move data
 */

public class GameData {
    public Player player;
    public Trainer trainer;
    public List<Pokemon> playerPokemons;
    public List<Pokemon> enemyPokemons;
    public Pokemon currentPlayerPokemon;
    public boolean isGUI = true;

    public final MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
    public final PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

    public GameData(boolean isGUI) {
        this.isGUI = isGUI;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void giveStartingPokemon(String name) {
        // For testing purposes the player and enemy pokemon are hardcoded
        playerPokemons = new ArrayList<>(List.of(
                new Pokemon(pl.getPokemonByName(name), isGUI)
        ));
        // Increase the EV of the first pokemon to almost assure a first turn win
        playerPokemons.getFirst().getStats().gainEV(2, 2, 2, 2, 2, 2);
        currentPlayerPokemon = playerPokemons.getFirst();
        currentPlayerPokemon.getStats().setLevel(10);   // DEBUG: Used to win first fight

        System.out.println("You have received a " + name + "!");
    }
}
