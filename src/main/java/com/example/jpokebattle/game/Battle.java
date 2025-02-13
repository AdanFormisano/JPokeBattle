package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.EffortValue;
import com.example.jpokebattle.poke.Move;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.data.DataTypeChart;
import com.example.jpokebattle.service.loader.PokeLoader;

import java.util.*;
import java.util.random.RandomGenerator;

public class Battle {
    private final Player player;
    private final Trainer trainer;
    private final List<Pokemon> playerPokemons;
    private final List<Pokemon> enemyPokemons;
    private Pokemon currentPlayerPokemon;
    private Pokemon currentEnemyPokemon;
    private StatStage currentPlayerPokemonStatStage = new StatStage();
    private StatStage currentEnemyPokemonStatStage = new StatStage();
    private int currentLvl = GameController.getInstance().currentLevel;
    private BattleOutcome outcome;

    private BattleEventListener listener;
    private final RandomGenerator randGen = RandomGenerator.getDefault();
    private final PokeLoader pl;

    public Battle(BattleEventListener listener, PokeLoader pl, Player player, Trainer trainer, List<Pokemon> playerPokemons, List<Pokemon> enemyPokemons, BattleOutcome outcome) {
        this.listener = listener;
        this.pl = pl;
        this.player = player;
        this.trainer = trainer;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
        currentPlayerPokemon = playerPokemons.getFirst();
        currentEnemyPokemon = enemyPokemons.getFirst();
        this.outcome = outcome;

        BattleIntro();
    }

    public void addListener(BattleEventListener listener) {
        this.listener = listener;
    }

    public Pokemon getCurrentPlayerPokemon() {
        return currentPlayerPokemon;
    }

    public Pokemon getCurrentEnemyPokemon() {
        return currentEnemyPokemon;
    }

    private void BattleIntro() {
        System.out.printf("Trainer sent %s!%n", currentEnemyPokemon.getName());
    }

    private static class ChosenMove {
        Pokemon pokemon;
        StatStage statStage;
        Move move;
        boolean isPlayer;
        boolean doesHit = true;

        public ChosenMove(Pokemon pokemon, Move move, boolean isPlayer, StatStage statStage) {
            this.pokemon = pokemon;
            this.statStage = statStage;
            this.move = move;
            this.isPlayer = isPlayer;
        }
    }

    public void playTurn(String playerMoveName) {
        List<ChosenMove> chosenMoves = new ArrayList<>(Arrays.asList(
                new ChosenMove(currentPlayerPokemon, currentPlayerPokemon.getMove(playerMoveName), true, currentPlayerPokemonStatStage),
                new ChosenMove(currentEnemyPokemon, currentEnemyPokemon.getMoveList().getFirst(), false, currentEnemyPokemonStatStage)
        ));

        orderMoves(chosenMoves);
        checkAccuracy(chosenMoves);
        applyDamage(chosenMoves);
        checkFaintedPokemon();
    }

    private void orderMoves(List<ChosenMove> chosenMoves) {
        if (chosenMoves.get(0).move.getPriority() < chosenMoves.get(1).move.getPriority()) {
            Collections.swap(chosenMoves, 0, 1);
        } else if (chosenMoves.get(0).move.getPriority() == chosenMoves.get(1).move.getPriority()) {
            // If the moves have the same priority, check the speed of the pokemon
            if (chosenMoves.get(0).pokemon.getStats().getSpeed() < chosenMoves.get(1).pokemon.getStats().getSpeed()) {
                Collections.swap(chosenMoves, 0, 1);
            }
        }
    }

    private void checkAccuracy(List<ChosenMove> chosenMoves) {
        for (ChosenMove chosenMove : chosenMoves) {
            if (chosenMove.move.getAccuracy() < 100) {
                double random = Math.random();
                if (random > chosenMove.move.getAccuracy() / 100) {
                    System.out.println(chosenMove.pokemon.getName() + " missed the attack!");
                    System.out.printf("Random: %f, Accuracy: %f%n", random, chosenMove.move.getAccuracy() / 100);
                    chosenMove.doesHit = false;
                }
            }
        }
    }

    private void applyDamage(List<ChosenMove> chosenMoves) {
        for (ChosenMove chosenMove : chosenMoves) {
            int target = chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0;
            if (chosenMove.doesHit && chosenMoves.get(target).pokemon.getStats().getCurrentHP() > 0) {
                double damage = calculateDamage(chosenMove.move, chosenMove.pokemon, chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokemon, chosenMove.statStage, chosenMoves.get(target).statStage);
                chosenMoves.get(target).pokemon.takeDamage(damage);
                System.out.printf("%s dealt %f damage to %s!%n", chosenMove.pokemon.getName(), damage, chosenMove.isPlayer ? chosenMoves.get(1).pokemon.getName() : chosenMoves.get(0).pokemon.getName());
            }
        }
    }

    // Follow this formula (GEN 3) : https://bulbapedia.bulbagarden.net/wiki/Damage
    private double calculateDamage(Move move, Pokemon attacker, Pokemon defender, StatStage attackerStatStage, StatStage defenderStatStage) {
        double damage = 0;
        var level = attacker.getStats().getLevel();
        var A = move.getCategory().equals("Physical") ?
                attacker.getStats().getAttack() * attackerStatStage.getMultiplier(StatStageType.ATTACK) :
                attacker.getStats().getSpecialAttack() * attackerStatStage.getMultiplier(StatStageType.SPECIAL_ATTACK);
        var D = move.getCategory().equals("Physical") ?
                defender.getStats().getDefense() * defenderStatStage.getMultiplier(StatStageType.DEFENSE) :
                defender.getStats().getSpecialDefense() * defenderStatStage.getMultiplier(StatStageType.SPECIAL_DEFENSE);
        var power = move.getPower();
        // Burn check
        // Screen check
        // Targets check (double battle)
        // Weather check
        // Flash Fire check
        // Stockpile check
        // Critical check
        // DoubleDmg check
        // Charge check
        // Helping Hand check
        var stab = attacker.getType().contains(move.getType()) ? 1.5 : 1;
        var type1 = DataTypeChart.getMultiplier(move.getType(), defender.getType().getFirst());
        var type2 = defender.getType().size() > 1 ? DataTypeChart.getMultiplier(move.getType(), defender.getType().getLast()) : 1;
        float random = (float) randGen.nextInt(85, 101) / 100;

        damage = (((double) (((level * 2) / 5) + 2) * power * (A / D)) / 50 + 2) * stab * type1 * type2 * random;
        System.out.printf("Damage: %f, level: %d, A: %f, D: %f, power: %d, stab: %f, type1: %f, type2: %f, random: %f%n", damage, level, A, D, power, stab, type1, type2, random);

        return damage;
    }

    private void checkFaintedPokemon() {
        if (currentPlayerPokemon.getStats().getCurrentHP() <= 0) {
            System.out.println(currentPlayerPokemon.getName() + " fainted!");

            notifyPokemonFainted(currentPlayerPokemon.getName());

            if (playerPokemons.size() == 1) {
                outcome.setPlayerWon(false);
                outcome.setCurrentPlayerPokemon(currentPlayerPokemon);
                outcome.setCurrentOpponentPokemon(currentEnemyPokemon);
                playerPokemons.remove(currentPlayerPokemon);
                notifyBattleEnd(outcome);
                System.out.println("You have no more Pokemon left!");
                return;
            } else {
                playerPokemons.remove(currentPlayerPokemon);
                currentPlayerPokemon = playerPokemons.getFirst();   // TODO: Implement a way to switch to another pokemon
            }
        }

        if (currentEnemyPokemon.getStats().getCurrentHP() <= 0) {
            System.out.println(currentEnemyPokemon.getName() + " fainted!");

            var exp = giveExp(currentPlayerPokemon, currentEnemyPokemon);
            giveEV(currentPlayerPokemon, currentEnemyPokemon);
            notifyPokemonFainted(currentEnemyPokemon.getName(), currentPlayerPokemon.getName(), exp);
            listener.onLevelUp(currentPlayerPokemon);

            if (enemyPokemons.size() == 1) {
                outcome.setPlayerWon(true);
                outcome.setCurrentPlayerPokemon(currentPlayerPokemon);
                outcome.setCurrentOpponentPokemon(currentEnemyPokemon);
                notifyBattleEnd(outcome);
                System.out.println("Trainer has no more Pokemon left!");
            } else {
                enemyPokemons.remove(currentEnemyPokemon);
                currentEnemyPokemon = enemyPokemons.getFirst();
            }
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

    private void notifyPokemonFainted(String pokemonFainted, String playerPokemon, int exp) {
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
