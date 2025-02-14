package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.game.StatStage;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.poke.StatType;
import com.example.jpokebattle.poke.Type;
import com.example.jpokebattle.service.data.DataTypeChart;

import java.util.Random;
import java.util.random.RandomGenerator;

public class MoveDamage extends AbstractMove implements IDamageEffect{
    private final int power;
    private static final RandomGenerator randGen = RandomGenerator.getDefault();

    public MoveDamage(String name, Type type, MoveCategory category, int maxPP, double accuracy, int priority, int power) {
        super(name, type, category, maxPP, accuracy, priority);
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void execute(PokeInBattle attacker, PokeInBattle target) {
        dealDamage(attacker, target);
        if (target.pokemon.getStats().getCurrentHP() <= 0) {
            target.pokemon.isFainted = true;
        }
    }

    @Override
    public void dealDamage(PokeInBattle attacker, PokeInBattle target) {
        var damage = calculateDamage(attacker, target);
        target.pokemon.takeDamage(damage);
    }

    private double calculateDamage(PokeInBattle attacker, PokeInBattle defender) {
        var level = attacker.pokemon.getStats().getLevel();
        var A = category == MoveCategory.PHYSICAL ?
                attacker.pokemon.getStats().getAttack() * attacker.statStage.getMultiplier(StatType.ATTACK) :
                attacker.pokemon.getStats().getSpecialAttack() * attacker.statStage.getMultiplier(StatType.SPECIAL_ATTACK);
        var D = category == MoveCategory.PHYSICAL ?
                defender.pokemon.getStats().getDefense() * defender.statStage.getMultiplier(StatType.DEFENSE) :
                defender.pokemon.getStats().getSpecialDefense() * defender.statStage.getMultiplier(StatType.SPECIAL_DEFENSE);

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
        var stab = attacker.pokemon.getType().contains(type) ? 1.5 : 1;
        var type1 = DataTypeChart.getMultiplier(type, defender.pokemon.getType().getFirst());
        var type2 = defender.pokemon.getType().size() > 1 ? DataTypeChart.getMultiplier(type, defender.pokemon.getType().getLast()) : 1;
        float random = (float) randGen.nextInt(85, 101) / 100;

        double damage = (((double) (((level * 2) / 5) + 2) * power * (A / D)) / 50 + 2) * stab * type1 * type2 * random;
        System.out.printf("Damage: %f, level: %d, A: %f, D: %f, power: %d, stab: %f, type1: %f, type2: %f, random: %f%n", damage, level, A, D, power, stab, type1, type2, random);

        return damage;
    }
}
