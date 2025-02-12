package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

public interface BattleEventListener {
    void onBattleEnd(BattleOutcome battleOutcome);
    void onPokemonFainted(boolean isPlayer, Pokemon pokemon);
}
