package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class BattleOutcome {
    private boolean playerWon;
    public int level;
    List<Pokemon> playerPokemons = new ArrayList<>();
    List<Pokemon> enemyPokemons = new ArrayList<>();
    Pokemon currentOpponentPokemon;
    Pokemon currentPlayerPokemon;

    public BattleOutcome(int level) {
        this.level = level;
    }
    public BattleOutcome(int level, List<Pokemon> playerPokemons, List<Pokemon> enemyPokemons) {
        this.level = level;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
    }

    public boolean getPlayerWon() { return playerWon; }
    public Pokemon getPokemon(int index) { return playerPokemons.get(index); }
    public List<Pokemon> getPlayerPokemons() { return playerPokemons; }
    public List<Pokemon> getEnemyPokemons() { return enemyPokemons; }
    public int getLevel() { return level; }
    public Pokemon getCurrentPlayerPokemon() { return currentPlayerPokemon; }
    public Pokemon getCurrentOpponentPokemon() { return currentOpponentPokemon; }

    public void setPlayerWon(boolean playerWon) { this.playerWon = playerWon; }
    public void setLevel(int level) { this.level = level; }
    public void setCurrentPlayerPokemon(Pokemon pokemon) { currentPlayerPokemon = pokemon; }
    public void setCurrentOpponentPokemon(Pokemon pokemon) { currentOpponentPokemon = pokemon; }
    public void addPlayerPokemon(Pokemon pokemon) { playerPokemons.add(pokemon); }
    public void addEnemyPokemon(Pokemon pokemon) { enemyPokemons.add(pokemon); }
}
