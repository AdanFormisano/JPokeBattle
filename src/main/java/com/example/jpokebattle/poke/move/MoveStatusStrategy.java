package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.poke.StatType;

public class MoveStatusStrategy implements MoveStrategy {
    private final StatType statType;
    private final int newStageValue;

    public MoveStatusStrategy(StatType statType, int newStageValue) {
        this.statType = statType;
        this.newStageValue = newStageValue;
    }

    @Override
    public void execute(PokeInBattle attacker, PokeInBattle target) {
        target.statStage.changeStage(statType, newStageValue);
        target.pokemon.getStats().calculateAllStats();
    }
}

