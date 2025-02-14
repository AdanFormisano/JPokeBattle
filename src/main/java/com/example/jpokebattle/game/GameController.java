package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.data.DataPokemon;

import java.util.ArrayList;
import java.util.List;

public class GameController implements IBattleEventListener {
    private GameData gameData;
    private IGameStateListener gameStateListener;

    public int currentLevel = 0;
    public List<BattleOutcome> battleOutcomes = new ArrayList<>();
    public static Battle currentBattle;

    private GameController() {
        gameData = new GameData(true);
    }

    private static class GameControllerHolder {
        private static final GameController INSTANCE = new GameController();
    }

    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public GameData getSessionData() {
        return gameData;
    }

    public void setGameStateListener(IGameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    private void checkEvolution(Pokemon pokemon) {
        if (pokemon.getStats().getLevel() >= pokemon.getEvoLevel()) {
            String pokeFromName = pokemon.getName();
            DataPokemon evolution = gameData.pl.getPokemonByName(pokemon.getEvoTo());
            if (evolution != null) {
                pokemon.evolve(evolution);
                gameStateListener.onPokemonEvolved(pokemon, pokeFromName);
            }
        }
    }

    private void generateLevel() {
        currentLevel++;

        // Generate a new Trainer
        gameData.trainer = new Trainer("Trainer", currentLevel);

        // Randomly generate the trainer's pokemon
        gameData.enemyPokemons = new ArrayList<>(List.of(
                new Pokemon(gameData.pl.getRandomPokemon(), currentLevel, gameData.isGUI)
        ));
    }

    public void generateBattle() {
        generateLevel();
//        sessionData.enemyPokemons.add(new Pokemon(sessionData.pl.getRandomPokemon(), currentLevel, sessionData.isGUI));
        BattleOutcome outcome = new BattleOutcome(currentLevel, List.copyOf(gameData.playerPokemons), List.copyOf(gameData.enemyPokemons));
        battleOutcomes.add(outcome);
        currentBattle = new Battle(this, gameData.pl, gameData.player, gameData.trainer,
                gameData.playerPokemons, gameData.enemyPokemons, outcome);
    }

    public void startGame() {
        if (gameStateListener != null) {
            gameStateListener.onGameStart();
        }
    }

    public void onPokemonSelected(String pokemonName) {
        gameData.giveStartingPokemon(pokemonName);
        generateBattle();
        if (gameStateListener != null) {
            gameStateListener.onBattleStart();
        }
    }

    public void onMoveSelected(String moveName) {
        currentBattle.playTurn(moveName);
    }

    public void onForgetMove(String toLearn, String toForget) {
        gameData.currentPlayerPokemon.learnAndForgetMove(toLearn, toForget);
    }

    @Override
    public void onBattleEnd(BattleOutcome outcome) {
        if (gameStateListener != null) {
            gameStateListener.onBattleEnd(outcome);
        }
    }

    @Override
    public void onPokemonFainted(String pokemon) {
        if (gameStateListener != null) {
            gameStateListener.onPokemonFainted(pokemon);
        }
    }

    @Override
    public void onPokemonFainted(String faintedPokemon, String playerPokemon, int exp) {
        if (gameStateListener != null) {
            gameStateListener.onPokemonFainted(faintedPokemon, playerPokemon, exp);
        }
    }

    @Override
    public void onLevelUp(Pokemon pokemon) {
        pokemon.getStats().calculateAllStats();
        var moves = pokemon.checkNewMoves();
        List<String> knownMoves = new ArrayList<>();

        for (var move : pokemon.getMoveList()) {
            knownMoves.add(move.getName());
        }

        gameStateListener.onLevelUp(pokemon);
        if (!moves.isEmpty()) {
            var remainingMoves = pokemon.learnMoves(moves);
            if (!remainingMoves.isEmpty()) {
                gameStateListener.onRemainingMoves(pokemon.getName(), remainingMoves, knownMoves);
            } else {
                gameStateListener.onLearnedMoves(pokemon.getName(), moves);
            }
        }

        checkEvolution(pokemon);
    }
}

