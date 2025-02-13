package com.example.jpokebattle.gui.data;

import com.example.jpokebattle.poke.Pokemon;

public class LevelUpViewData implements DynamicViewData{
    String pokemonName;
    int level;

    public LevelUpViewData(String pokemonName, int level) {
        this.pokemonName = pokemonName;
        this.level = level;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getLevel() {
        return level;
    }
}
