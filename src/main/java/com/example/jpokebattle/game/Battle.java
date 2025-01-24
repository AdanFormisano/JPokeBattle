package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Move;
import com.example.jpokebattle.poke.Pokemon;

import java.util.Scanner;

public class Battle {
    private final Player player;
    private final Trainer trainer;
    private final Pokemon[] playerPokemons;
    private final Pokemon[] enemyPokemons;
    private Pokemon currentPlayerPokemon;
    private Pokemon currentEnemyPokemon;
    private final Scanner scanner;

    public Battle(Player player, Trainer trainer, Pokemon[] playerPokemons, Pokemon[] enemyPokemons) {
        this.player = player;
        this.trainer = trainer;
        this.playerPokemons = playerPokemons;
        this.enemyPokemons = enemyPokemons;
        this.currentPlayerPokemon = playerPokemons[0];
        this.currentEnemyPokemon = enemyPokemons[0];
        this.scanner = new Scanner(System.in);

        BattleLoop();
    }

    private void BattleLoop() {
        while (playerPokemons.length > 0 && enemyPokemons.length > 0) {
            // Moves are selected
            selectMove(true);
            // Priority checked
            // Move speed checked
            // Selected which move goes first
                // Check if it hits
                    // Calculate damage
                    // Apply damage
                    // Check if pokemon has fainted
                        // Check if there are any pokemon left
                            // Switch pokemon
                        // End battle
            // Execute other pokemon's move
                // Same as above
        }
    }

    private Move selectMove(boolean player) {
        if (player) {
            int choice = getMoveChoice();

            Move chosenMove = currentPlayerPokemon.getMoveSet()[choice];
            chosenMove.decreasePP();
            System.out.println(currentPlayerPokemon.getName() + " used " + chosenMove.getName());
            return chosenMove;
        } else {
            return null;
        }
    }

    private int getMoveChoice() {
        // Print available moves
        System.out.println("Please select a move:");
        for (int i = 0; i < currentPlayerPokemon.getMoveSet().length; i++) {
            System.out.printf("%d. %s (PP: %d/%d)%n",
                    i + 1,
                    currentPlayerPokemon.getMoveSet()[i].getName(),
                    currentPlayerPokemon.getMoveSet()[i].getPP(),
                    currentPlayerPokemon.getMoveSet()[i].getMaxPP());
        }

        // Get user input
        while (true) {
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= currentPlayerPokemon.getMoveSet().length) {
                    return choice - 1;
                }
                System.out.println("Please enter a valid number between 1 and " + currentPlayerPokemon.getMoveSet().length);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
