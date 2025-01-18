package com.example.jpokebattle.poke;

import java.util.Random;

public class IndividualValue {
    private final int hp;
    private final int attack;
    private final int defense;
    private final int specialAttack;
    private final int specialDefense;
    private final int speed;

    private static final Random random = new Random();

    public IndividualValue() {
        // Generate random value for each IV between 0 and 31
        hp = random.nextInt(32);
        attack = random.nextInt(32);
        defense = random.nextInt(32);
        specialAttack = random.nextInt(32);
        specialDefense = random.nextInt(32);
        speed = random.nextInt(32);
    }

    // Getters
    public int getHp() { return this.hp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public String toString() {
        return "IVs{" +
                "hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                '}';
    }
}
