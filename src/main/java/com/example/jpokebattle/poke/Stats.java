package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.ExpPattern;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stats {
    private final Nature nature;
    private final BaseStats baseStats;
    private final IndividualValue IV = new IndividualValue();
    private final EffortValue EV = new EffortValue();
    private final ExpPattern expPattern;
    private final int expYield;

    private final IntegerProperty level = new SimpleIntegerProperty();
    private final DoubleProperty exp = new SimpleDoubleProperty();
    private final DoubleProperty currentExp = new SimpleDoubleProperty();
    private final DoubleProperty currentHP = new SimpleDoubleProperty();
    private final DoubleProperty maxHP = new SimpleDoubleProperty();
    private final DoubleProperty attack = new SimpleDoubleProperty();
    private final DoubleProperty defense = new SimpleDoubleProperty();
    private final DoubleProperty specialAttack = new SimpleDoubleProperty();
    private final DoubleProperty specialDefense = new SimpleDoubleProperty();
    private final DoubleProperty speed = new SimpleDoubleProperty();

    public Stats(BaseStats baseStats, Nature nature, String expPattern, int expYield) {
        this.baseStats = baseStats;
        this.nature = nature;
        this.expPattern = ExpPattern.valueOf(expPattern);
        this.expYield = expYield;

        calculateAllStats();

        System.out.printf("IVs: %s\n", IV);
        System.out.printf("Max HP: %.0f\n", maxHP.get());
    }

    public IntegerProperty levelProperty() { return level; }
    public DoubleProperty expProperty() { return exp; }
    public DoubleProperty currentExpProperty() { return currentExp; }
    public DoubleProperty currentHPProperty() { return currentHP; }
    public DoubleProperty maxHPProperty() { return maxHP; }
    public DoubleProperty attackProperty() { return attack; }
    public DoubleProperty defenseProperty() { return defense; }
    public DoubleProperty specialAttackProperty() { return specialAttack; }
    public DoubleProperty specialDefenseProperty() { return specialDefense; }
    public DoubleProperty speedProperty() { return speed; }

    // Getters
    public double getAttack() { return attack.get(); }
    public double getDefense() { return defense.get(); }
    public double getSpecialAttack() { return specialAttack.get(); }
    public double getSpecialDefense() { return specialDefense.get(); }
    public double getSpeed() { return speed.get(); }
    public double getMaxHP() { return maxHP.get(); }
    public double getCurrentHP() { return currentHP.get(); }
    public EffortValue getEV() { return EV; }
    public int getLevel() { return level.get(); }
    public double getCurrentExp() { return currentExp.get(); }
    public double getExpToNextLevel() { return expPattern.getRequiredExp(level.get() + 1) - currentExp.get();}
    public int getTotalExp() { return expPattern.getRequiredExp(level.get() + 1); }
    public int getExpYield() { return expYield; }

    // Setters
    public void increaseLevel() { level.set(level.get() + 1); }
    public void increaseCurrentHP(PositiveInt increase) { currentHP.set(Math.min(currentHP.get() + increase.doubleValue(), maxHP.get())); }
    public void increaseAttack(PositiveInt increase) { attack.set(attack.get() + increase.doubleValue()); }
    public void increaseDefense(PositiveInt increase) { defense.set(defense.get() + increase.doubleValue()); }
    public void increaseSpecialAttack(PositiveInt increase) { specialAttack.set(specialAttack.get() + increase.doubleValue()); }
    public void increaseSpecialDefense(PositiveInt increase) { specialDefense.set(specialDefense.get() + increase.doubleValue()); }
    public void increaseSpeed(PositiveInt increase) { speed.set(speed.get() + increase.doubleValue()); }
    public double gainExp(int enemyLvl, int baseExpYield, boolean isWild) {
        double expGained = (double) (baseExpYield * enemyLvl) / 7 * (isWild ? 1 : 1.5);
        currentExp.set(currentExp.get() + expGained);
        return expGained;
    }
    public void gainEV(EffortValue ev) {
        EV.add(ev);
    }

    public void decreaseCurrentHP(double decrease) { currentHP.set(Math.max(currentHP.get() - decrease, 0)); }

    private void calculateAllStats() {
        attack.set(calculateAttack());
        defense.set(calculateDefense());
        specialAttack.set(calculateSpecialAttack());
        specialDefense.set(calculateSpecialDefense());
        speed.set(calculateSpeed());
        maxHP.set(calculateMaxHP());
        currentHP.set(maxHP.getValue());
    }

    private double calculateAttack() {
        // Check if nature increases attack
        int attack = baseStats.getAttack();

        if (nature.getIncreasedStat().equals("Attack")) {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level.getValue(), 1.1);
        } else if (nature.getDecreasedStat().equals("Attack")) {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level.getValue(), 0.9);
        } else {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level.getValue(), 1);
        }
    }

    private double calculateDefense() {
        // Check if nature increases defense
        int defense = baseStats.getAttack();

        if (nature.getIncreasedStat().equals("Defense")) {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level.getValue(), 1.1);
        } else if (nature.getDecreasedStat().equals("Defense")) {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level.getValue(), 0.9);
        } else {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level.getValue(), 1);
        }
    }

    private double calculateSpecialAttack() {
        // Check if nature increases special attack
        int specialAttack = baseStats.getSpecialAttack();

        if (nature.getIncreasedStat().equals("Special Attack")) {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level.getValue(), 1.1);
        } else if (nature.getDecreasedStat().equals("Special Attack")) {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level.getValue(), 0.9);
        } else {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level.getValue(), 1);
        }
    }

    private double calculateSpecialDefense() {
        // Check if nature increases special defense
        int specialDefense = baseStats.getSpecialDefense();

        if (nature.getIncreasedStat().equals("Special Defense")) {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level.getValue(), 1.1);
        } else if (nature.getDecreasedStat().equals("Special Defense")) {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level.getValue(), 0.9);
        } else {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level.getValue(), 1);
        }
    }

    private double calculateSpeed() {
        // Check if nature increases speed
        int speed = baseStats.getSpeed();

        if (nature.getIncreasedStat().equals("Speed")) {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level.getValue(), 1.1);
        } else if (nature.getDecreasedStat().equals("Speed")) {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level.getValue(), 0.9);
        } else {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level.getValue(), 1);
        }
    }

    private double calculateMaxHP() {
        int hp = baseStats.getBaseHP();
        return Math.floor((double) ((2 * hp + IV.getHp() + EV.getHP()) * level.getValue()) / 100 + level.getValue() + 10);
    }

    private double calculateStat(int baseStat, int IV, int EV, int level, double natureMultiplier) {
        return Math.floor(Math.floor((double) ((2 * baseStat + IV + EV) * level) / 100 + 5) * natureMultiplier);
    }
}
