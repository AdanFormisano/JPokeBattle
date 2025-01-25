package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.data.DataType;

import java.util.List;

public class Pokemon {
    private final int id;
    private final String name;
    private final BaseStats baseStats;
    private final Stats stats;
    private final Nature nature = new Nature();
    private Move[] moveSet = { new Move("Tackle", "NORMAL", "Physical", 35, 95, 0, 35) };   // TODO: Implement move assigner
    public boolean isFainted = false;

    public Pokemon (DataPokemon dataPokemon) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        baseStats = new BaseStats(dataPokemon.getHp(), dataPokemon.getAttack(), dataPokemon.getDefense(), dataPokemon.getSpecialAttack(), dataPokemon.getSpecialDefense(), dataPokemon.getSpeed(), dataPokemon.getAbility(), dataPokemon.getType());
        stats = new Stats(baseStats, nature);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<DataType> getType() { return baseStats.getType(); }
    public BaseStats getBaseStats() { return baseStats; }
    public Move[] getMoveSet() { return this.moveSet; }
    public Nature getNature() { return this.nature; }
    public Stats getStats() { return stats; }

    // Game methods
    public void takeDamage(double damageTaken) { stats.decreaseCurrentHP(damageTaken); }
    public void heal(int healAmount) { stats.increaseMaxHp(new PositiveInt(healAmount)); }
}
