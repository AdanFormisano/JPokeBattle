package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

public class PokeInBattle {
    public Pokemon pokemon;
    public StatStage statStage;

    public PokeInBattle(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.statStage = new StatStage();
    }
}
