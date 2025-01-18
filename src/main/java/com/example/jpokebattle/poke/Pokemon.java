package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.DataPokemon;

public class Pokemon {
    private final int id;
    private final String name;
    private final BaseStats baseStats;
    private final Stats stats;
    private final Nature nature = new Nature();
    private MoveSet[] available_moves = { new MoveSet("Tackle", "Normal", "Physical", 35, 95, 0, 35) };

//    public Pokemon(int id, String name, String[] type, String ability, int hp, int attack, int defense, int speed, int specialAttack, int specialDefense, AvailableMove[] available_moves) {
//        this.id = id;
//        this.name = name;
//        baseStats = new BaseStats(hp, attack, defense, specialAttack, specialDefense, speed, ability, type);
//
//        if (available_moves.length > 4) {
//            System.out.println("A Pokemon can only have 4 moves. The first 4 moves will be used.");
//        } else {
//            this.available_moves = available_moves;
//        }
//    }

    public Pokemon (DataPokemon dataPokemon) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        baseStats = new BaseStats(dataPokemon.getHp(), dataPokemon.getAttack(), dataPokemon.getDefense(), dataPokemon.getSpecialAttack(), dataPokemon.getSpecialDefense(), dataPokemon.getSpeed(), dataPokemon.getAbility(), dataPokemon.getType());
        stats = new Stats(baseStats, nature);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String[] getType() { return baseStats.getType(); }
    public BaseStats getBaseStats() { return baseStats; }
    public MoveSet[] getAvailableMoves() { return this.available_moves; }
    public Nature getNature() { return this.nature; }
    public Stats getStats() { return stats; }

    // Game methods
    public void takeDamage(double damageTaken) { stats.decreaseCurrentHP(damageTaken); }
    public void heal(int healAmount) { stats.increaseMaxHp(new PositiveInt(healAmount)); }
}
