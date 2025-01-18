package com.example.jpokebattle.poke;

public class MoveSet {
    private String name;
    private String type;
    private String category;
    private int power;
    private float accuracy;
    private int priority;
    private int pp;

    public MoveSet(String name, String type, String category, int power, float accuracy, int priority, int pp) {
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
    public int getPP() { return this.pp; }

    // Setter
    public void decreasePP() { this.pp--; }

    @Override
    public String toString() {
        return "AvailableMove{" +
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
