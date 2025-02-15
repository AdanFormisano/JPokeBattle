package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.poke.Type;

public class Move {
    private final String name;
    private final Type type;
    private final MoveCategory category;
    private final int maxPP;
    private int currentPP;
    private final double accuracy;
    private final int priority;
    private final MoveStrategy strategy;

    public Move(String name, Type type, MoveCategory category, int maxPP, double accuracy, int priority, MoveStrategy strategy) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.accuracy = accuracy;
        this.priority = priority;
        this.strategy = strategy;
    }

    public void execute(PokeInBattle attacker, PokeInBattle target) {
        strategy.execute(attacker, target);
        currentPP -= 1;
    }

    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPriority() { return priority; }
    public int getMaxPP() { return maxPP; }
    public int getCurrentPP() { return currentPP; }
    public MoveCategory getCategory() { return category; }
    public double getAccuracy() { return accuracy; }

    public MoveStrategy getStrategy() { return strategy; }
}

