package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.ExpPattern;

public class Stats {
    private final Nature nature;
    private final BaseStats baseStats;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;
    private double maxHP;
    private double currentHP;
    private final IndividualValue IV = new IndividualValue();
    private final EffortValue EV = new EffortValue();
    private int level = 1;
    private final ExpPattern expPattern;
    private double currentExp = 0;
    private final int expYield;

    public Stats(BaseStats baseStats, Nature nature, String expPattern, int expYield) {
        this.baseStats = baseStats;
        this.nature = nature;
        this.expPattern = ExpPattern.valueOf(expPattern);
        this.expYield = expYield;

        calculateAllStats();

        System.out.printf("IVs: %s\n", IV);
        System.out.printf("Max HP: %.0f\n", maxHP);
    }

    // Getters
    public double getAttack() { return attack; }
    public double getDefense() { return defense; }
    public double getSpecialAttack() { return specialAttack; }
    public double getSpecialDefense() { return specialDefense; }
    public double getSpeed() { return speed; }
    public double getMaxHP() { return maxHP; }
    public double getCurrentHP() { return currentHP; }
    public int getLevel() { return level; }
    public double getCurrentExp() { return currentExp; }
    public double getExpToNextLevel() { return expPattern.getRequiredExp(level + 1) - currentExp;}
    public int getExpYield() { return expYield; }

    // Setters
    public void increaseLevel() { this.level++; }
    public void increaseCurrentHP(PositiveInt increase) { currentHP += increase.doubleValue(); }
    public void increaseAttack(PositiveInt increase) { attack += increase.doubleValue(); }
    public void increaseDefense(PositiveInt increase) { defense += increase.doubleValue(); }
    public void increaseSpecialAttack(PositiveInt increase) { specialAttack += increase.doubleValue(); }
    public void increaseSpecialDefense(PositiveInt increase) { specialDefense += increase.doubleValue(); }
    public void increaseSpeed(PositiveInt increase) { speed += increase.doubleValue(); }
    public double gainExp(int enemyLvl, int baseExpYield, boolean isWild) {
        double expGained = (double) (baseExpYield * enemyLvl) / 7 * (isWild ? 1 : 1.5);
        currentExp += expGained;
        return expGained;
    }

    public void decreaseCurrentHP(double decrease) {
        currentHP -= decrease;
    }

    private void calculateAllStats() {
        attack = calculateAttack();
        defense = calculateDefense();
        specialAttack = calculateSpecialAttack();
        specialDefense = calculateSpecialDefense();
        speed = calculateSpeed();
        maxHP = calculateMaxHP();
        currentHP = maxHP;
    }

    private double calculateAttack() {
        // Check if nature increases attack
        int attack = baseStats.getAttack();

        if (nature.getIncreasedStat().equals("Attack")) {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level, 1.1);
        } else if (nature.getDecreasedStat().equals("Attack")) {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level, 0.9);
        } else {
            return calculateStat(attack, IV.getAttack(), EV.getAttack(), level, 1);
        }
    }

    private double calculateDefense() {
        // Check if nature increases defense
        int defense = baseStats.getAttack();

        if (nature.getIncreasedStat().equals("Defense")) {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level, 1.1);
        } else if (nature.getDecreasedStat().equals("Defense")) {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level, 0.9);
        } else {
            return calculateStat(defense, IV.getDefense(), EV.getDefense(), level, 1);
        }
    }

    private double calculateSpecialAttack() {
        // Check if nature increases special attack
        int specialAttack = baseStats.getSpecialAttack();

        if (nature.getIncreasedStat().equals("Special Attack")) {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level, 1.1);
        } else if (nature.getDecreasedStat().equals("Special Attack")) {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level, 0.9);
        } else {
            return calculateStat(specialAttack, IV.getSpecialAttack(), EV.getSpecialAttack(), level, 1);
        }
    }

    private double calculateSpecialDefense() {
        // Check if nature increases special defense
        int specialDefense = baseStats.getSpecialDefense();

        if (nature.getIncreasedStat().equals("Special Defense")) {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level, 1.1);
        } else if (nature.getDecreasedStat().equals("Special Defense")) {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level, 0.9);
        } else {
            return calculateStat(specialDefense, IV.getSpecialDefense(), EV.getSpecialDefense(), level, 1);
        }
    }

    private double calculateSpeed() {
        // Check if nature increases speed
        int speed = baseStats.getSpeed();

        if (nature.getIncreasedStat().equals("Speed")) {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level, 1.1);
        } else if (nature.getDecreasedStat().equals("Speed")) {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level, 0.9);
        } else {
            return calculateStat(speed, IV.getSpeed(), EV.getSpeed(), level, 1);
        }
    }

    private double calculateMaxHP() {
        int hp = baseStats.getBaseHP();
        return Math.floor((double) ((2 * hp + IV.getHp() + EV.getHP()) * level) / 100 + level + 10);
    }

    private double calculateStat(int baseStat, int IV, int EV, int level, double natureMultiplier) {
        return Math.floor(Math.floor((double) ((2 * baseStat + IV + EV) * level) / 100 + 5) * natureMultiplier);
    }
}
