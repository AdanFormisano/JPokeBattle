package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;

import java.util.random.RandomGenerator;

public class MoveDamageStrategy implements MoveStrategy {
    private final int power;
    private static final RandomGenerator randGen = RandomGenerator.getDefault();

    public MoveDamageStrategy(int power) {
        this.power = power;
    }

    @Override
    public void execute(PokeInBattle attacker, PokeInBattle target) {
        int damage = calculateDamage(attacker, target);
        target.pokemon.takeDamage(damage);
        if (target.pokemon.getStats().getCurrentHP() <= 0) {
            target.pokemon.isFainted = true;
        }
    }

    private int calculateDamage(PokeInBattle attacker, PokeInBattle defender) {
        var level = attacker.pokemon.getStats().getLevel();
        var A = attacker.pokemon.getStats().getAttack();
        var D = defender.pokemon.getStats().getDefense();
        float random = (float) randGen.nextInt(85, 101) / 100;
        return (int) ((int) ((((double) (level * 2) / 5 + 2) * power * (A / D)) / 50 + 2) * random);
    }

    public int getPower() { return power; }
}
