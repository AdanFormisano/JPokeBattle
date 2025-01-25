package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.data.DataType;

import java.util.ArrayList;
import java.util.List;

public class BaseStats {
    private final int baseHP;
    private final int attack;
    private final int defense;
    private final int specialAttack;
    private final int specialDefense;
    private final int speed;
    private final String ability;
    private final List<DataType> type = new ArrayList<>();

    public BaseStats(int baseHP, int attack, int defense, int specialAttack, int specialDefense, int speed, String ability, List<DataType> type) {
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
    public List<DataType> getType() { return this.type; }

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
