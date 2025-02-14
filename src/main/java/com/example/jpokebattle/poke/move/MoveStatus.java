package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.poke.StatType;
import com.example.jpokebattle.poke.Type;

public class MoveStatus extends AbstractMove implements IStatusEffect{
    private final StatType statType;
    private final int newStageValue;

    public MoveStatus(String name, Type type, MoveCategory category, int maxPP, double accuracy, int priority, StatType statType, int newStageValue) {
        super(name, type, category, maxPP, accuracy, priority);
        this.statType = statType;
        this.newStageValue = newStageValue;
    }

    @Override
    public void applyEffect(PokeInBattle target) {
        target.statStage.changeStage(statType, newStageValue);
        target.pokemon.getStats().calculateAllStats();
    }

    @Override
    public void execute(PokeInBattle attacker, PokeInBattle target) {
        applyEffect(target);
    }
}
