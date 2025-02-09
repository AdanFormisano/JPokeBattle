package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.EffortValue;
import com.example.jpokebattle.poke.Move;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.service.data.DataTypeChart;
import com.example.jpokebattle.service.loader.PokeLoader;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;

public class Battle {
    private final Player player;
    private final Trainer trainer;
    private final List<Pokemon> playerPokemons;
    private final List<Pokemon> enemyPokemons;
    private Pokemon currentPlayerPokemon;
    private Pokemon currentEnemyPokemon;
    private final RandomGenerator randGen = RandomGenerator.getDefault();
    private final PokeLoader pl;

    public Battle(PokeLoader pl, Player player, Trainer trainer, List<Pokemon> playerPokemons, List<Pokemon> enemyPokemons) {
        this.pl = pl;
        this.player = player;
        this.trainer = trainer;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
        currentPlayerPokemon = playerPokemons.getFirst();
        currentEnemyPokemon = enemyPokemons.getFirst();

        BattleIntro();
    }

    public Pokemon getCurrentEnemyPokemon() {
        return currentEnemyPokemon;
    }

    private void BattleIntro() {
        System.out.printf("Trainer sent %s!%n", currentEnemyPokemon.getName());
    }

    private static class PlayingMoves {
        Move move1;
        Move move2;
        Pokemon pokemon1;
        Pokemon pokemon2;

        public PlayingMoves(Move move1, Move move2, Pokemon pokemon1, Pokemon pokemon2) {
            this.move1 = move1;
            this.move2 = move2;
            this.pokemon1 = pokemon1;
            this.pokemon2 = pokemon2;
        }
    }

    private static class ChosenMove {
        Pokemon pokemon;
        Move move;
        boolean isPlayer;
        boolean doesHit = true;

        public ChosenMove(Pokemon currentPlayerPokemon, Move move, boolean isPlayer) {
            this.pokemon = currentPlayerPokemon;
            this.move = move;
            this.isPlayer = isPlayer;
        }
    }

    public void playTurn(String playerMoveName) {
        List<ChosenMove> chosenMoves = List.of(
                new ChosenMove(currentPlayerPokemon, currentPlayerPokemon.getMove(playerMoveName), true),
                new ChosenMove(currentEnemyPokemon, currentEnemyPokemon.getMoveList().getFirst(), false));

        orderMoves(chosenMoves);
        checkAccuracy(chosenMoves);
        applyDamage(chosenMoves);
        checkFaintedPokemon();
    }

//
//    private void BattleLoop() {
//        // For testing purposes, the battle will end when there are no more pokemon
//        while (!playerPokemons.isEmpty() && !enemyPokemons.isEmpty()) {
//
//            // Moves selected
//            PlayingMoves playingMoves = new PlayingMoves(selectMove(true), selectMove(false), currentPlayerPokemon, currentEnemyPokemon);
//            // Order of moves checked
//            checkMovesOrder(playingMoves);
//            // Check if it hits
//            AccuracyCheck doesHit = checkAccuracy(playingMoves);
//            // Calculate & Apply damage
//            applyDamage(playingMoves, doesHit);
//            // Check if any pokemon fainted
//            checkFaintedPokemon();
//        }
//
//        if (enemyPokemons.isEmpty()) {
//            System.out.println("You have defeated the enemy!");
//        } else {
//            System.out.println("You have been defeated!");
//        }
//    }
//
//    private Move selectMove(boolean player) {
//        if (player) {
//            int choice = getMoveChoice();
//
//            Move chosenMove = currentPlayerPokemon.getMoveList().get(choice);
//            chosenMove.decreasePP();
//            System.out.println(currentPlayerPokemon.getName() + " has chosen " + chosenMove.getName());
//            return chosenMove;
//        } else {
//           // For now, the enemy will always use the first move
//            Move chosenMove = currentEnemyPokemon.getMoveList().getFirst();
//            chosenMove.decreasePP();
//            System.out.println(currentEnemyPokemon.getName() + " has chosen " + chosenMove.getName());
//            return chosenMove;
//        }
//    }
//
//    private int getMoveChoice() {
//        // Print available moves
//        System.out.println("Please select a move:");
//        for (int i = 0; i < currentPlayerPokemon.getMoveList().size(); i++) {
//            System.out.printf("%d. %s (PP: %d/%d)%n",
//                    i + 1,
//                    currentPlayerPokemon.getMoveList().get(i).getName(),
//                    currentPlayerPokemon.getMoveList().get(i).getPP(),
//                    currentPlayerPokemon.getMoveList().get(i).getMaxPP());
//        }
//
//        // Get user input
//        while (true) {
//            try {
//                String input = scanner.nextLine();
//                int choice = Integer.parseInt(input);
//
//                if (choice >= 1 && choice <= currentPlayerPokemon.getMoveList().size()) {
//                    return choice - 1;
//                }
//                System.out.println("Please enter a valid number between 1 and " + currentPlayerPokemon.getMoveList().size());
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }
//    }

    private void orderMoves (List<ChosenMove> chosenMoves) {
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
            if (chosenMove.doesHit) {
                double damage = calculateDamage(chosenMove.move, chosenMove.pokemon, chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokemon);
                chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokemon.takeDamage(damage);
                System.out.printf("%s dealt %f damage to %s!%n", chosenMove.pokemon.getName(), damage, chosenMove.isPlayer ? chosenMoves.get(1).pokemon.getName() : chosenMoves.get(0).pokemon.getName());
            }
        }
    }

    // Follow this formula (GEN 3) : https://bulbapedia.bulbagarden.net/wiki/Damage
    private double calculateDamage(Move move, Pokemon attacker, Pokemon defender) {
        double damage = 0;
        var level = attacker.getStats().getLevel();
        var A = move.getCategory().equals("Physical") ? attacker.getStats().getAttack() : attacker.getStats().getSpecialAttack();
        var D = move.getCategory().equals("Physical") ? defender.getStats().getDefense() : defender.getStats().getSpecialDefense();
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

        damage = (((double) (((level * 2) /5) + 2) * power * (A/D)) / 50) * stab * type1 * type2 * random;
        System.out.printf("Damage: %f, level: %d, A: %f, D: %f, power: %d, stab: %f, type1: %f, type2: %f, random: %f%n", damage, level, A, D, power, stab, type1, type2, random);

        return damage;
    }

    private void checkFaintedPokemon() {
        if (currentPlayerPokemon.getStats().getCurrentHP() <= 0) {
            try {
                System.out.println(currentPlayerPokemon.getName() + " fainted!");
                playerPokemons.remove(currentPlayerPokemon);
                currentPlayerPokemon = playerPokemons.getFirst();
            } catch (NoSuchElementException e) {
                System.out.println("You have no more Pokemon left!");
                currentPlayerPokemon = null;
            }
        }

        if (currentEnemyPokemon.getStats().getCurrentHP() <= 0) {
            try {
                System.out.println(currentEnemyPokemon.getName() + " fainted!");

                giveExp(currentPlayerPokemon,currentEnemyPokemon);
                giveEV(currentPlayerPokemon,currentEnemyPokemon);

                enemyPokemons.remove(currentEnemyPokemon);
                currentEnemyPokemon = enemyPokemons.getFirst();
            } catch (NoSuchElementException e) {
                System.out.println("Trainer has no more Pokemon left!");
                currentEnemyPokemon = null;
            }
        }
    }

    private void giveExp(Pokemon gainingPokemon, Pokemon faintedPokemon) {
        int baseExpYield = faintedPokemon.getStats().getExpYield();
        int enemyLvl = faintedPokemon.getStats().getLevel();
        boolean isWild = true; // For now, all enemy pokemon are wild
        var gainedExp = gainingPokemon.getStats().gainExp(enemyLvl, baseExpYield, isWild);

        if (gainedExp > gainingPokemon.getStats().getExpToNextLevel()) {
            gainingPokemon.getStats().increaseLevel();
            System.out.println(gainingPokemon.getName() + " has leveled up!");
            System.out.printf("%s is now level: %d%n", gainingPokemon.getName(), gainingPokemon.getStats().getLevel());
        }
        System.out.printf("%s gained %f EXP! [%f/%d]%n", gainingPokemon.getName(), gainedExp, gainingPokemon.getStats().getCurrentExp(), gainingPokemon.getStats().getTotalExp());
    }

    private void giveEV(Pokemon gainingPokemon, Pokemon faintedPokemon) {
        EffortValue gainedEV = pl.getPokemonByName(faintedPokemon.getName()).getEffortValue();
        gainingPokemon.getStats().gainEV(gainedEV);
        System.out.printf("%s gained %s EVs!%n", gainingPokemon.getName(), gainedEV);   // TODO: Maybe format the print as "HP: +1, Attack: +2, Defense: +0, Special Attack: +1, Special Defense: +1, Speed: +0"
        System.out.printf("Current EVs: %s%n", gainingPokemon.getStats().getEV());
    }
}
