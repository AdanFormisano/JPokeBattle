package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class BattleOutcome {
    private boolean playerWon;
    public int level;
    List<Pokemon> playerPokemons = new ArrayList<>();
    List<Pokemon> enemyPokemons = new ArrayList<>();

    BattleOutcome(int level) {
        this.level = level;
    }

    public boolean getPlayerWon() { return playerWon; }
    public Pokemon getPokemon(int index) { return playerPokemons.get(index); }

    public void setPlayerWon(boolean playerWon) { this.playerWon = playerWon; }
    public void setLevel(int level) { this.level = level; }
    public void addPlayerPokemon(Pokemon pokemon) { playerPokemons.add(pokemon); }
    public void addEnemyPokemon(Pokemon pokemon) { enemyPokemons.add(pokemon); }
    public List<Pokemon> getPlayerPokemons() { return playerPokemons; }
    public List<Pokemon> getEnemyPokemons() { return enemyPokemons; }
}
