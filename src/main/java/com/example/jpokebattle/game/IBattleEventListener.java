package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.function.Consumer;

public interface IBattleEventListener {
    void onBattleEnd(BattleOutcome battleOutcome);
    void onPokemonFainted(String pokemon);
    void onPokemonFainted(Pokemon faintedPokemon, String playerPokemon, int exp);
    void onLevelUp(Pokemon pokemon);

    void onBattleContinue();
}
