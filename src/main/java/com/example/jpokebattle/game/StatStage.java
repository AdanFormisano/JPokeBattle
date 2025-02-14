package com.example.jpokebattle.game;

import java.util.EnumMap;
import java.util.Map;

public class StatStage {
    private final Map<StatType, Integer> stages = new EnumMap<>(StatType.class);

    public StatStage() {
        for (StatType stat : StatType.values()) {
            stages.put(stat, 0);
        }
    }

    public void setStage(StatType stat, int stage) {
        // You might want to clamp the stage between -6 and 6 here
        stages.put(stat, Math.max(-6, Math.min(6, stage)));
    }

    public double getMultiplier(StatType stat) {
        int stage = stages.get(stat);
        return stat.getMultiplier(stage);
    }
}
