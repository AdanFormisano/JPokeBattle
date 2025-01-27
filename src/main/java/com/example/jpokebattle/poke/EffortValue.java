package com.example.jpokebattle.poke;

public class EffortValue {
    private int hp = 0;
    private int attack = 0;
    private int defense = 0;
    private int specialAttack = 0;
    private int specialDefense = 0;
    private int speed = 0;

    public EffortValue() {}

    public EffortValue(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public void increaseHP(int increase) { hp += increase; }
    public void increaseAttack(int increase) { attack += increase; }
    public void increaseDefense(int increase) { defense += increase; }
    public void increaseSpecialAttack(int increase) { specialAttack += increase; }
    public void increaseSpecialDefense(int increase) { specialDefense += increase; }
    public void increaseSpeed(int increase) { speed += increase; }
    public void add(EffortValue ev) {
        hp += ev.hp;
        attack += ev.attack;
        defense += ev.defense;
        specialAttack += ev.specialAttack;
        specialDefense += ev.specialDefense;
        speed += ev.speed;
    }

    public int getHP() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpecialAttack() { return specialAttack; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }

    public String toString() {
        return String.format("HP: %d, Attack: %d, Defense: %d, Special Attack: %d, Special Defense: %d, Speed: %d", hp, attack, defense, specialAttack, specialDefense, speed);
    }
}
