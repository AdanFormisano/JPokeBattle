package com.example.jpokebattle.service.data;

import com.example.jpokebattle.poke.StatType;
import com.example.jpokebattle.poke.Type;
import com.example.jpokebattle.poke.move.MoveCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataMove {
    private final String name;
    private final Type type;
    private final MoveCategory category;
    private final int power;
    private final float accuracy;
    private final int priority;
    private final int pp;
    private final StatType statType;
    private final Integer stageLevel;

    public DataMove(@JsonProperty("name") String name,
                    @JsonProperty("type") Type type,
                    @JsonProperty("category") String category,
                    @JsonProperty("power") int power,
                    @JsonProperty("accuracy") float accuracy,
                    @JsonProperty("priority") int priority,
                    @JsonProperty("pp") int pp,
                    @JsonProperty(value = "effect") Effect effect) {
        this.name = name;
        this.type = type;
        this.category = MoveCategory.fromString(category);
        this.power = power;
        this.accuracy = accuracy;
        this.priority = priority;
        this.pp = pp;
        this.statType = effect != null ? effect.statType : null;
        this.stageLevel = effect != null ? effect.stageLevel : null;
    }

    public static class Effect {
        @JsonProperty("statType")
        private StatType statType;

        @JsonProperty("stageLevel")
        private Integer stageLevel;
    }

    // Getters
    public String getName() { return this.name; }
    public Type getType() { return this.type; }
    public MoveCategory getCategory() { return this.category; }
    public int getPower() { return this.power; }
    public float getAccuracy() { return this.accuracy; }
    public int getPriority() { return this.priority; }
    public int getPp() { return this.pp; }
    public StatType getStatType() { return this.statType; }
    public int getStageLevel() { return this.stageLevel; }

    @Override
    public String toString() {
        return "Move{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", power=" + power +
                ", accuracy=" + accuracy +
                ", priority=" + priority +
                ", pp=" + pp +
                '}';
    }
}
