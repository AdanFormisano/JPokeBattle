package com.example.jpokebattle.poke;

public class BaseStats {
    private final int baseHP;
    private final int attack;
    private final int defense;
    private final int specialAttack;
    private final int specialDefense;
    private final int speed;
    private final String ability;
    private final String[] type = new String[2];

    public BaseStats(int baseHP, int attack, int defense, int specialAttack, int specialDefense, int speed, String ability, String[] type) {
        this.baseHP = baseHP;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.ability = ability;
        this.type[0] = type[0];
        this.type[1] = type[1];
    }

    // Getters
    public int getBaseHP() { return this.baseHP; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public String getAbility() { return this.ability; }
    public String[] getType() { return this.type; }

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
                ", type=" + type[0] +
                ", type=" + type[1] +
                '}';
    }
}
