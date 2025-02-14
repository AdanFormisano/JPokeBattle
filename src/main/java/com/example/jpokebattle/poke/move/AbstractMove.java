package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.poke.Pokemon;
import com.example.jpokebattle.poke.Type;

public abstract class AbstractMove {
    String name;
    Type type;
    MoveCategory category;
    int maxPP;
    int currentPP;
    double accuracy;
    int priority;

    public abstract void execute(PokeInBattle attacker, PokeInBattle target);

    public AbstractMove(String name, Type type, MoveCategory category, int maxPP, double accuracy, int priority) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.accuracy = accuracy;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public MoveCategory getCategory() {
        return this.category;
    }

    public int getMaxPP() {
        return this.maxPP;
    }

    public int getCurrentPP() {
        return this.currentPP;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public int getPriority() {
        return this.priority;
    }
}
