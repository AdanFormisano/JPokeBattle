package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController implements BattleEventListener {
    private SessionData sessionData;
    private GameStateListener gameStateListener;

    public int currentLevel = 0;
    public List<BattleOutcome> battleOutcomes = new ArrayList<>();
    public static Battle currentBattle;

    private GameController() {
        sessionData = new SessionData(true);
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

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    private void generateLevel() {
        currentLevel++;

        // Generate a new Trainer
        sessionData.trainer = new Trainer("Trainer", currentLevel);

        // Randomly generate the trainer's pokemon
        sessionData.enemyPokemons = new ArrayList<>(List.of(
                new Pokemon(sessionData.pl.getRandomPokemon(), currentLevel, sessionData.isGUI)
        ));
    }

    public void generateBattle() {
        generateLevel();
//        sessionData.enemyPokemons.add(new Pokemon(sessionData.pl.getRandomPokemon(), currentLevel, sessionData.isGUI));
        BattleOutcome outcome = new BattleOutcome(currentLevel, List.copyOf(sessionData.playerPokemons), List.copyOf(sessionData.enemyPokemons));
        battleOutcomes.add(outcome);
        currentBattle = new Battle(this, sessionData.pl, sessionData.player, sessionData.trainer,
                sessionData.playerPokemons, sessionData.enemyPokemons, outcome);
    }

    public void startGame() {
        if (gameStateListener != null) {
            gameStateListener.onGameStart();
        }
    }

    public void onPokemonSelected(String pokemonName) {
        sessionData.giveStartingPokemon(pokemonName);
        startBattle();
    }

    private void startBattle() {
        generateBattle();
        if (gameStateListener != null) {
            gameStateListener.onBattleStart();
        }
    }

    public void onMoveSelected(String moveName) {
        currentBattle.playTurn(moveName);
    }

    @Override
    public void onBattleEnd(BattleOutcome outcome) {
        if (gameStateListener != null) {
            gameStateListener.onBattleEnd(outcome);
        }
    }

    @Override
    public void onPokemonFainted(boolean isPlayer, Pokemon pokemon) {
        if (gameStateListener != null) {
            gameStateListener.onPokemonFainted(isPlayer, pokemon);
        }
    }


}

