package com.example.jpokebattle.poke;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    private final String name;
    private final String type;
    private final String category;
    private final int power;
    private final float accuracy;
    private final int priority;
    private final int pp;

    public Move(@JsonProperty("name") String name,
                @JsonProperty("type") String type,
                @JsonProperty("category") String category,
                @JsonProperty("power") int power,
                @JsonProperty("accuracy") float accuracy,
                @JsonProperty("priority") int priority,
                @JsonProperty("pp") int pp) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.priority = priority;
        this.pp = pp;
    }

    // Getters
    public String getName() { return this.name; }
    public String getType() { return this.type; }
    public String getCategory() { return this.category; }
    public int getPower() { return this.power; }
    public float getAccuracy() { return this.accuracy; }
    public int getPriority() { return this.priority; }
    public int getPp() { return this.pp; }

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
