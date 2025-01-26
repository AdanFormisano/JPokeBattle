package com.example.jpokebattle.poke;

public class EffortValue {
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    public EffortValue() {
        this.hp = 0;
        this.attack = 0;
        this.defense = 0;
        this.specialAttack = 0;
        this.specialDefense = 0;
        this.speed = 0;
    }

    public void increaseHP(int increase) { hp += increase; }
    public void increaseAttack(int increase) { attack += increase; }
    public void increaseDefense(int increase) { defense += increase; }
    public void increaseSpecialAttack(int increase) { specialAttack += increase; }
    public void increaseSpecialDefense(int increase) { specialDefense += increase; }
    public void increaseSpeed(int increase) { speed += increase; }

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
