package com.example.jpokebattle.poke;

import java.util.ArrayList;
import java.util.List;

public class BaseStats {
    private int baseHP;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private String ability;
    private List<Type> type = new ArrayList<>();

    public BaseStats(int baseHP, int attack, int defense, int specialAttack, int specialDefense, int speed, String ability, List<Type> type) {
        this.baseHP = baseHP;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.ability = ability;
        this.type.addAll(type);
    }

    // Getters
    public int getBaseHP() { return this.baseHP; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public String getAbility() { return this.ability; }
    public List<Type> getType() { return this.type; }

    // Setters
    public void setBaseHP(int baseHP) { this.baseHP = baseHP; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setAbility(String ability) { this.ability = ability; }
    public void setType(List<Type> type) { this.type = type; }

    @Override
    public String toString() {
        return "BaseStats{" +
                "baseHP=" + baseHP +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                ", ability='" + ability + '\'' +
                ", type=" + type.get(0) +
                ", type=" + type.get(1) +
                '}';
    }
}
