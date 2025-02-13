package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.List;

public interface GameStateListener {
    void onGameStart();
    void onNeedPokemonSelection();
    void onBattleStart();
    void onPokemonFainted(String pokemon);
    void onPokemonFainted(String faintedPokemon, String playerPokemon, double exp);
    void onBattleEnd(BattleOutcome outcome);
    void onLevelUp(Pokemon pokemon, List<String> moves);
}
