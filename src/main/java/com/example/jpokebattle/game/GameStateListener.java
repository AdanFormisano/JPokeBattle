package com.example.jpokebattle.game;

public interface GameStateListener {
    void onGameStart();
    void onNeedPokemonSelection();
    void onBattleStart();
    void onBattleEnd(BattleOutcome outcome);
}
