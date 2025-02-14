package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.data.LeaderboardEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class GameController implements IBattleEventListener {
    GameData gameData;
    private IGameStateListener gameStateListener;
    public int currentLevel = 0;
    public List<BattleOutcome> battleOutcomes = new ArrayList<>();
    public static Battle currentBattle;
    private double chanceForPokeOffer = 0.7;
    private RandomGenerator randGen = RandomGenerator.getDefault();

    private GameController() throws IOException {
        gameData = new GameData(true);
    }

    public void onPokemonSwitch(String pokemon) {
        pokemonSwitch(gameData.getPokemonFromName(pokemon));

    }

    private static class GameControllerHolder {
        private static final GameController INSTANCE;

        static {
            try {
                INSTANCE = new GameController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void addLeaderboardEntry(List<Pokemon> playerPokemons, int level) {
        List<LeaderboardEntry.LeaderboardPokeEntry> pokemonEntries = new ArrayList<>();
        for (var pokemon : playerPokemons) {
            pokemonEntries.add(new LeaderboardEntry.LeaderboardPokeEntry(pokemon.getName(), pokemon.getStats().getLevel()));
        }

        LeaderboardEntry entry = new LeaderboardEntry(pokemonEntries, level);
        int position = gameData.leaderboardLoader.addEntry(entry);
    }

    public void getTopTenLeaderboardEntries() {
        gameData.leaderboardLoader.getTopEntries(10);
    }

    public Pokemon getCurrentBattlePokemon() {
        return currentBattle.getCurrentPlayerPokemon();
    }

    public void setGameStateListener(IGameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public void pokemonSwitch(Pokemon pokemon) {
        currentBattle.setCurrentPlayerPokemon(pokemon);
        gameData.currentPlayerPokemon = pokemon;
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

    public void onOfferAccepted() {
        Pokemon offeredPokemon = currentBattle.getCurrentEnemyPokemon();
        offeredPokemon.fullHP();
        offeredPokemon.isFainted = false;
        gameData.playerPokemons.add(currentBattle.getCurrentEnemyPokemon());
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
    public void onPokemonFainted(Pokemon faintedPokemon, String playerPokemon, int exp) {
        var pokeOfferCheck = randGen.nextDouble();
        System.out.println("Offer: " + pokeOfferCheck + " < " + chanceForPokeOffer);

        if (gameStateListener != null) {
            gameStateListener.onPokemonFainted(faintedPokemon.getName(), playerPokemon, exp);
            if (pokeOfferCheck < chanceForPokeOffer) {
                gameStateListener.onPokeOffer(faintedPokemon);
                chanceForPokeOffer *= 0.75;
            }
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

    @Override
    public void onBattleContinue() {
        // When battle continues, SC has already showed the fainted pokemon
        // Ask for pokemon switch
        if (gameStateListener != null) {
            gameStateListener.onPokemonSwitch(this::pokemonSwitch);
        }
    }
}

