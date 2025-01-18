package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.data.DataNature;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.loader.NatureLoader;

import java.util.Random;

public class Pokemon {
    private final int id;
    private final String name;
    private final String ability;
    private final String[] type = new String[2];
    private int level;
    private int currentHp;
    private int maxHP;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private final String nature;
    private final IndividualValue iv = new IndividualValue();
    private AvailableMove[] available_moves = {
            new AvailableMove("Tackle", "Normal", "Physical", 35, 95, 0, 35),
    };
    private static final Random random = new Random();

    public Pokemon(int id, String name, String[] type, String ability, int level, int maxHp, int attack, int defense, int speed, int specialAttack, int specialDefense, AvailableMove[] available_moves) {
        this.id = id;
        this.name = name;
        this.type[0] = type[0];
        this.type[1] = type[1];
        this.ability = ability;
        this.level = level;
        this.currentHp = maxHp; // When a Pokemon is created, it has full HP
        this.maxHP = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        nature = generateNature();

        if (available_moves.length > 4) {
            System.out.println("A Pokemon can only have 4 moves. The first 4 moves will be used.");
        } else {
            this.available_moves = available_moves;
        }
    }

    public Pokemon (DataPokemon dataPokemon) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        type[0] = dataPokemon.getType()[0];
        type[1] = dataPokemon.getType()[1];
        ability = dataPokemon.getAbility();
        level = 1;
        currentHp = dataPokemon.getHp();
        maxHP = dataPokemon.getHp();
        attack = dataPokemon.getAttack();
        defense = dataPokemon.getDefense();
        specialAttack = dataPokemon.getSpecialAttack();
        specialDefense = dataPokemon.getSpecialDefense();
        speed = dataPokemon.getSpeed();
        nature = generateNature();
    }


    // Getters
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String[] getType() { return this.type; }
    public int getLevel() { return this.level; }
    public int getCurrentHp() { return this.currentHp; }
    public int getMaxHP() { return this.maxHP; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public AvailableMove[] getAvailableMoves() { return this.available_moves; }
    public IndividualValue getIV() { return this.iv; }
    public String getNature() { return this.nature; }

    // Setters
    public void increaseLevel() { this.level++; }
    public void getDamage(int damage) { currentHp = Math.max(0, currentHp - damage); }
    public void heal(int heal) { currentHp = Math.min(maxHP, currentHp + heal); }
    public void increaseMaxHp(int increase) { maxHP += increase; }
    public void increaseAttack(int increase) { attack += increase; }
    public void increaseDefense(int increase) { defense += increase; }
    public void increaseSpecialAttack(int increase) { specialAttack += increase; }
    public void increaseSpecialDefense(int increase) { specialDefense += increase; }
    public void increaseSpeed(int increase) { speed += increase; }



    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", hp=" + currentHp +
                ", maxHP=" + maxHP +
                ", type='" + type[0] + '\'' +
                ", type='" + type[1] + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                '}';
    }

    private String generateNature() {
        int natureIndex = random.nextInt(26);

        NatureLoader natureLoader = new NatureLoader("src/main/resources/com/example/jpokebattle/data/nature.json");

        System.out.println(natureIndex);
        return natureLoader.getNatureById(natureIndex).getName();
    }
}
