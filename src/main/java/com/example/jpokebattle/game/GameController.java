package com.example.jpokebattle.game;

import com.example.jpokebattle.service.session.SessionData;
import com.example.jpokebattle.service.session.SessionGame;

public class GameController {
    private SessionGame sessionGame;
    private SessionData sessionData;
    private GameStateListener gameStateListener;

    private GameController() {
        sessionData = new SessionData(true);
        sessionGame = new SessionGame(sessionData);
    }

    private static class GameControllerHolder {
        private static final GameController INSTANCE = new GameController();
    }

    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public SessionData getSessionData() {
        return sessionData;
    }
    public SessionGame getSessionGame() {
        return sessionGame;
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public void startGame() {
        // Notify UI we need a Pokemon selection
        if (gameStateListener != null) {
//            gameStateListener.onNeedPokemonSelection();
            gameStateListener.onGameStart();
        }
    }

    public void onPokemonSelected(String pokemonName) {
        sessionData.giveStartingPokemon(pokemonName);
        startBattle();
    }

    private void startBattle() {
        sessionGame.generateBattle();
        if (gameStateListener != null) {
            gameStateListener.onBattleStart();
        }
    }

    public void onMoveSelected(String moveName) {
        SessionGame.currentBattle.playTurn(moveName);
    }
}

