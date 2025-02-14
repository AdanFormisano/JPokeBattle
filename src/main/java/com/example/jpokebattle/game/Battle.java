package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.EffortValue;
import com.example.jpokebattle.poke.StatType;
import com.example.jpokebattle.poke.move.AbstractMove;
import com.example.jpokebattle.poke.move.Move;
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
        AbstractMove move;
        boolean isPlayer;
        boolean doesHit = true;

        public ChosenMove(PokeInBattle pokeInBattle, AbstractMove move, boolean isPlayer, StatStage statStage) {
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
//        applyDamage(chosenMoves);
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

//    private void applyDamage(List<ChosenMove> chosenMoves) {
//        for (ChosenMove chosenMove : chosenMoves) {
//            int target = chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0;
//            if (chosenMove.doesHit && chosenMoves.get(target).pokeInBattle.getStats().getCurrentHP() > 0) {
//                double damage = calculateDamage(chosenMove.move, chosenMove.pokeInBattle, chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokeInBattle, chosenMove.statStage, chosenMoves.get(target).statStage);
//                chosenMoves.get(target).pokeInBattle.takeDamage(damage);
//                System.out.printf("%s dealt %f damage to %s!%n", chosenMove.pokeInBattle.getName(), damage, chosenMove.isPlayer ? chosenMoves.get(1).pokeInBattle.getName() : chosenMoves.get(0).pokeInBattle.getName());
//            }
//        }
//    }

    private void executeMoves(List<ChosenMove> chosenMoves) {
        for (ChosenMove chosenMove : chosenMoves) {
            if (chosenMove.doesHit && chosenMove.pokeInBattle.pokemon.getStats().getCurrentHP() > 0) {
                chosenMove.move.execute(chosenMove.pokeInBattle, chosenMoves.get(chosenMoves.indexOf(chosenMove) == 0 ? 1 : 0).pokeInBattle);
            }
        }
    }

    // Follow this formula (GEN 3) : https://bulbapedia.bulbagarden.net/wiki/Damage
    private double calculateDamage(Move move, Pokemon attacker, Pokemon defender, StatStage attackerStatStage, StatStage defenderStatStage) {
        double damage = 0;
        var level = attacker.getStats().getLevel();
        var A = move.getCategory().equals("Physical") ?
                attacker.getStats().getAttack() * attackerStatStage.getMultiplier(StatType.ATTACK) :
                attacker.getStats().getSpecialAttack() * attackerStatStage.getMultiplier(StatType.SPECIAL_ATTACK);
        var D = move.getCategory().equals("Physical") ?
                defender.getStats().getDefense() * defenderStatStage.getMultiplier(StatType.DEFENSE) :
                defender.getStats().getSpecialDefense() * defenderStatStage.getMultiplier(StatType.SPECIAL_DEFENSE);
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
        if (currentPlayerPokemon.pokemon.getStats().getCurrentHP() <= 0) {
            System.out.println(currentPlayerPokemon.pokemon.getName() + " fainted!");

            notifyPokemonFainted(currentPlayerPokemon.pokemon.getName());

            if (playerPokemons.size() == 1) {
                outcome.setPlayerWon(false);
                outcome.setCurrentPlayerPokemon(currentPlayerPokemon.pokemon);
                outcome.setCurrentOpponentPokemon(currentEnemyPokemon.pokemon);
                playerPokemons.remove(currentPlayerPokemon.pokemon);
                notifyBattleEnd(outcome);
                System.out.println("You have no more Pokemon left!");
                return;
            } else {
                playerPokemons.remove(currentPlayerPokemon.pokemon);
                currentPlayerPokemon.pokemon = playerPokemons.getFirst();   // TODO: Implement a way to switch to another pokemon
            }
        }

        if (currentEnemyPokemon.pokemon.getStats().getCurrentHP() <= 0) {
            System.out.println(currentEnemyPokemon.pokemon.getName() + " fainted!");

            var exp = giveExp(currentPlayerPokemon.pokemon, currentEnemyPokemon.pokemon);
            giveEV(currentPlayerPokemon.pokemon, currentEnemyPokemon.pokemon);
            notifyPokemonFainted(currentEnemyPokemon.pokemon.getName(), currentPlayerPokemon.pokemon.getName(), exp);
            listener.onLevelUp(currentPlayerPokemon.pokemon);

            if (enemyPokemons.size() == 1) {
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
