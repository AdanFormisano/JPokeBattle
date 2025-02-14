package com.example.jpokebattle.gui.data;

import com.example.jpokebattle.poke.Pokemon;

public record EvolvedViewData(Pokemon pokemon, String pokeNameFrom) implements IDynamicViewData {
}
