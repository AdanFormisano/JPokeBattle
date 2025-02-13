package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

public interface BattleEventListener {
    void onBattleEnd(BattleOutcome battleOutcome);
    void onPokemonFainted(String pokemon);
    void onPokemonFainted(String faintedPokemon, String playerPokemon, double exp);
    void onLevelUp(Pokemon pokemon);
}
