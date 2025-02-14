package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

public interface IBattleEventListener {
    void onBattleEnd(BattleOutcome battleOutcome);
    void onPokemonFainted(String pokemon);
    void onPokemonFainted(String faintedPokemon, String playerPokemon, int exp);
    void onLevelUp(Pokemon pokemon);
}
