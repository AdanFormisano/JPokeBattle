package com.example.jpokebattle.poke;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EffortValue {
    private final IntegerProperty HP = new SimpleIntegerProperty(0);
    private final IntegerProperty attack = new SimpleIntegerProperty(0);
    private final IntegerProperty defense = new SimpleIntegerProperty(0);
    private final IntegerProperty specialAttack = new SimpleIntegerProperty(0);
    private final IntegerProperty specialDefense = new SimpleIntegerProperty(0);
    private final IntegerProperty speed = new SimpleIntegerProperty(0);

    public IntegerProperty HPProperty() { return HP; }
    public IntegerProperty attackProperty() { return attack; }
    public IntegerProperty defenseProperty() { return defense; }
    public IntegerProperty specialAttackProperty() { return specialAttack; }
    public IntegerProperty specialDefenseProperty() { return specialDefense; }
    public IntegerProperty speedProperty() { return speed; }

    public int getHP() { return HP.get(); }
    public int getAttack() { return attack.get(); }
    public int getDefense() { return defense.get(); }
    public int getSpecialAttack() { return specialAttack.get(); }
    public int getSpecialDefense() { return specialDefense.get(); }
    public int getSpeed() { return speed.get(); }

    public void increaseHP(int increase) { HP.set(HP.get() + increase); }
    public void increaseAttack(int increase) { attack.set(attack.get() + increase); }
    public void increaseDefense(int increase) { defense.set(defense.get() + increase); }
    public void increaseSpecialAttack(int increase) { specialAttack.set(specialAttack.get() + increase); }
    public void increaseSpecialDefense(int increase) { specialDefense.set(specialDefense.get() + increase); }
    public void increaseSpeed(int increase) { speed.set(speed.get() + increase); }
    public void add(int HP, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        increaseHP(HP);
        increaseAttack(attack);
        increaseDefense(defense);
        increaseSpecialAttack(specialAttack);
        increaseSpecialDefense(specialDefense);
        increaseSpeed(speed);
    }
    public void add(EffortValue ev) {
        add(ev.getHP(), ev.getAttack(), ev.getDefense(), ev.getSpecialAttack(), ev.getSpecialDefense(), ev.getSpeed());
    }

    public String toString() {
        return String.format("HP: %d, Attack: %d, Defense: %d, Special Attack: %d, Special Defense: %d, Speed: %d", HP.getValue(), attack.getValue(), defense.getValue(), specialAttack.getValue(), specialDefense.getValue(), speed.getValue());
    }
}
