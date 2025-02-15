package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.EffortValue;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.poke.move.Move;
import com.example.jpokebattle.service.loader.PokeLoader;

import java.util.*;
import java.util.random.RandomGenerator;

public class Battle {
    private final Player player;
    private final Trainer trainer;
    private final List<Pokemon> playerPokemons;
    private final List<Pokemon> enemyPokemons;
    private PokeInBattle currentPlayerPokemon;
    private PokeInBattle currentEnemyPokemon;
    private int currentLvl = GameController.getInstance().currentLevel;
    private BattleOutcome outcome;

    private IBattleEventListener listener;
    private final RandomGenerator randGen = RandomGenerator.getDefault();
    private final PokeLoader pl;

    public Battle(IBattleEventListener listener, PokeLoader pl, Player player, Trainer trainer, List<Pokemon> playerPokemons, List<Pokemon> enemyPokemons, BattleOutcome outcome) {
        this.listener = listener;
        this.pl = pl;
        this.player = player;
        this.trainer = trainer;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
        currentPlayerPokemon = new PokeInBattle(playerPokemons.getFirst());
        currentEnemyPokemon = new PokeInBattle(enemyPokemons.getFirst());
        this.outcome = outcome;

        BattleIntro();
    }

    public void addListener(IBattleEventListener listener) {
        this.listener = listener;
    }

    public void setCurrentPlayerPokemon(Pokemon pokemon) {
        currentPlayerPokemon = new PokeInBattle(pokemon);
    }
    public void setCurrentEnemyPokemon(Pokemon pokemon) {
        currentEnemyPokemon = new PokeInBattle(pokemon);
    }
    public Pokemon getCurrentPlayerPokemon() {
        return currentPlayerPokemon.pokemon;
    }
    public Pokemon getCurrentEnemyPokemon() {
        return currentEnemyPokemon.pokemon;
    }

    private void BattleIntro() {
        System.out.printf("Trainer sent %s!%n", currentEnemyPokemon.pokemon.getName());
    }

    private static class ChosenMove {
        PokeInBattle pokeInBattle;
        StatStage statStage;
        Move move;
        boolean isPlayer;
        boolean doesHit = true;

        public ChosenMove(PokeInBattle pokeInBattle, Move move, boolean isPlayer, StatStage statStage) {
            this.pokeInBattle = pokeInBattle;
            this.statStage = statStage;
            this.move = move;
            this.isPlayer = isPlayer;
        }
    }

    public void playTurn(String playerMoveName) {
        List<ChosenMove> chosenMoves = new ArrayList<>(Arrays.asList(
                new ChosenMove(currentPlayerPokemon, currentPlayerPokemon.pokemon.getMove(playerMoveName), true, currentPlayerPokemon.statStage),
                new ChosenMove(currentEnemyPokemon, currentEnemyPokemon.pokemon.getMoveList().getFirst(), false, currentEnemyPokemon.statStage)
        ));

        orderMoves(chosenMoves);
        checkAccuracy(chosenMoves);
        executeMoves(chosenMoves);
        checkFaintedPokemon();
    }

    private void orderMoves(List<ChosenMove> chosenMoves) {
        if (chosenMoves.get(0).move.getPriority() < chosenMoves.get(1).move.getPriority()) {
            Collections.swap(chosenMoves, 0, 1);
        } else if (chosenMoves.get(0).move.getPriority() == chosenMoves.get(1).move.getPriority()) {
            // If the moves have the same priority, check the speed of the pokemon
            if (chosenMoves.get(0).pokeInBattle.pokemon.getStats().getSpeed() < chosenMoves.get(1).pokeInBattle.pokemon.getStats().getSpeed()) {
                Collections.swap(chosenMoves, 0, 1);
            }
        }
    }

    private void checkAccuracy(List<ChosenMove> chosenMoves) {
        for (ChosenMove chosenMove : chosenMoves) {
            if (chosenMove.move.getAccuracy() < 100) {
                double random = Math.random();
                if (random > chosenMove.move.getAccuracy() / 100) {
                    System.out.println(chosenMove.pokeInBattle.pokemon.getName() + " missed the attack!");
                    System.out.printf("Random: %f, Accuracy: %f%n", random, chosenMove.move.getAccuracy() / 100);
                    chosenMove.doesHit = false;
                }
            }
        }
    }

    private void executeMoves(List<ChosenMove> chosenMoves) {
        for (ChosenMove chosenMove : chosenMoves) {
            if (chosenMove.doesHit && chosenMove.pokeInBattle.pokemon.getStats().getCurrentHP() > 0) {
                chosenMove.move.execute(chosenMove.pokeInBattle, chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokeInBattle);
            }
        }
    }

    private boolean checkEndCondition(List<Pokemon> list) {
        for (Pokemon pokemon : list) {
            if (!pokemon.isFainted) {
                return false;
            }
        }
        return true;
    }

    private void checkFaintedPokemon() {
        if (currentPlayerPokemon.pokemon.isFainted) {
            System.out.println(currentPlayerPokemon.pokemon.getName() + " fainted!");

            // Notify pkm fainted
            notifyPokemonFainted(currentPlayerPokemon.pokemon.getName());



            if (checkEndCondition(GameController.getInstance().gameData.getAlivePokemons(playerPokemons))) {
                outcome.setPlayerWon(false);
                outcome.setCurrentPlayerPokemon(currentPlayerPokemon.pokemon);
                outcome.setCurrentOpponentPokemon(currentEnemyPokemon.pokemon);
//                playerPokemons.remove(currentPlayerPokemon.pokemon);
                notifyBattleEnd(outcome);
                System.out.println("You have no more Pokemon left!");
                return;
            } else {
                // Notify continue battle => choose another pokemon
                notifyBattleContinue();
//                playerPokemons.remove(currentPlayerPokemon.pokemon);
                currentPlayerPokemon.pokemon = playerPokemons.getFirst();   // TODO: Implement a way to switch to another pokemon
            }
        }

        if (currentEnemyPokemon.pokemon.isFainted) {
            System.out.println(currentEnemyPokemon.pokemon.getName() + " fainted!");

            var exp = giveExp(currentPlayerPokemon.pokemon, currentEnemyPokemon.pokemon);
            giveEV(currentPlayerPokemon.pokemon, currentEnemyPokemon.pokemon);
            notifyPokemonFainted(currentEnemyPokemon.pokemon, currentPlayerPokemon.pokemon.getName(), exp);
            listener.onLevelUp(currentPlayerPokemon.pokemon);

            if (checkEndCondition(GameController.getInstance().gameData.getAlivePokemons(enemyPokemons))) {
                outcome.setPlayerWon(true);
                outcome.setCurrentPlayerPokemon(currentPlayerPokemon.pokemon);
                outcome.setCurrentOpponentPokemon(currentEnemyPokemon.pokemon);
                notifyBattleEnd(outcome);
                System.out.println("Trainer has no more Pokemon left!");
            } else {
                enemyPokemons.remove(currentEnemyPokemon.pokemon);
                currentEnemyPokemon.pokemon = enemyPokemons.getFirst();
            }
        }
    }

    private void notifyBattleContinue() {
        if (listener != null) {
            listener.onBattleContinue();
        }
    }

    private void notifyBattleEnd(BattleOutcome outcome) {
        if (listener != null) {
            listener.onBattleEnd(outcome);
        }
    }

    private void notifyPokemonFainted(String pokemon) {
        if (listener != null) {
            listener.onPokemonFainted(pokemon);
        }
    }

    private void notifyPokemonFainted(Pokemon pokemonFainted, String playerPokemon, int exp) {
        if (listener != null) {
            listener.onPokemonFainted(pokemonFainted, playerPokemon, exp);
        }
    }

    private int giveExp(Pokemon gainingPokemon, Pokemon faintedPokemon) {
        int baseExpYield = faintedPokemon.getStats().getExpYield();
        int enemyLvl = faintedPokemon.getStats().getLevel();
        boolean isWild = true; // For now, all enemy pokemon are wild
        var gainedExp = gainingPokemon.getStats().gainExp(enemyLvl, baseExpYield, isWild);

        if (gainedExp > gainingPokemon.getStats().getExpToNextLevel()) {
            gainingPokemon.getStats().increaseLevel();
            System.out.println(gainingPokemon.getName() + " has leveled up!");
            System.out.printf("%s is now level: %d%n", gainingPokemon.getName(), gainingPokemon.getStats().getLevel());
        }
        System.out.printf("%s gained %d EXP! [%d/%d]%n", gainingPokemon.getName(), gainedExp, gainingPokemon.getStats().getCurrentExp(), gainingPokemon.getStats().getTotalExpNeeded());
        return gainedExp;
    }

    private void giveEV(Pokemon gainingPokemon, Pokemon faintedPokemon) {
        EffortValue gainedEV = pl.getPokemonByName(faintedPokemon.getName()).getEffortValue();
        gainingPokemon.getStats().gainEV(gainedEV);
        gainingPokemon.getStats().calculateAllStats();
        System.out.printf("%s gained %s EVs!%n", gainingPokemon.getName(), gainedEV);   // TODO: Maybe format the print as "HP: +1, Attack: +2, Defense: +0, Special Attack: +1, Special Defense: +1, Speed: +0"
        System.out.printf("Current EVs: %s%n", gainingPokemon.getStats().getEV());
    }
}
