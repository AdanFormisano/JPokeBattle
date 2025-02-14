package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.loader.LeaderboardLoader;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;

import java.io.IOException;
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
    public LeaderboardLoader leaderboardLoader;

    public final MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
    public final PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

    public GameData(boolean isGUI) throws IOException {
        this.isGUI = isGUI;

        try{
            leaderboardLoader = new LeaderboardLoader("src/main/resources/com/example/jpokebattle/data/leaderboard.json");
        } catch (IOException e) {
            System.out.println("Error loading leaderboard from JSON file: " + e.getMessage());
        }
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
//        currentPlayerPokemon.getStats().setLevel(10);   // DEBUG: Used to win first fight
        currentPlayerPokemon.getStats().increaseAttack(new PositiveInt(10));   // DEBUG: Used to win first fight
//        currentPlayerPokemon.learnMoves(new ArrayList<>(List.of("Tackle", "Tail Whip", "Vine Whip", "Razor Leaf")));

        System.out.println("You have received a " + name + "!");
    }

    public Pokemon getPokemonFromName(String name) {
        for (Pokemon pokemon : playerPokemons) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return null;
    }

    public List<Pokemon> getAlivePokemons(List<Pokemon> pokemon) {
        List<Pokemon> alivePokemons = new ArrayList<>();
        for (Pokemon p : pokemon) {
            if (!p.isFainted) {
                alivePokemons.add(p);
            }
        }
        return alivePokemons;
    }
}
