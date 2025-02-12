package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.List;

public class BattleOutcome {
    private boolean playerWon;
    public int level;
    List<Pokemon> playerPokemons;
    List<Pokemon> enemyPokemons;

    public BattleOutcome(boolean playerWon, List<Pokemon> playerPokemons, List<Pokemon> enemyPokemons) {
        this.playerWon = playerWon;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
    }

    public boolean getPlayerWon() { return playerWon; }
    public void setLevel(int level) { this.level = level; }
}
