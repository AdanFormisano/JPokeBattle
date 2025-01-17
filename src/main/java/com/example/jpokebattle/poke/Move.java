package com.example.jpokebattle.poke;

public class Move {
    public int id;
    public String name;
    public String type;
    public String category;
    public int power;
    public float accuracy;
    public int priority;
    public int pp;

    public Move(int id, String name, String type, String category, int power, double accuracy, int priority, int pp) {
        this.id = 0;
        this.name = "";
        this.type = "";
        this.category = "";
        this.power = 0;
        this.accuracy = 0.0f;
        this.priority = 0;
        this.pp = 0;
    }
}
