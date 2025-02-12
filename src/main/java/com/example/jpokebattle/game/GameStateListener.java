package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

public interface GameStateListener {
    void onGameStart();
    void onNeedPokemonSelection();
    void onBattleStart();
    void onPokemonFainted(boolean isPlayer, Pokemon pokemon);
    void onBattleEnd(BattleOutcome outcome);
}
