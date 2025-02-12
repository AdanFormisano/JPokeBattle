package com.example.jpokebattle.gui;

import com.example.jpokebattle.poke.Pokemon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InfoFainted {
    private final ObjectProperty<Pokemon> faintedPokemon = new SimpleObjectProperty<>();
    private final BooleanProperty isPlayer = new SimpleBooleanProperty();

    public InfoFainted(Pokemon pokemon, boolean isPlayer) {
        faintedPokemon.set(pokemon);
        this.isPlayer.set(isPlayer);
    }

    public ObjectProperty<Pokemon> faintedPokemonProperty() { return faintedPokemon; }
    public Pokemon getFaintedPokemon() { return faintedPokemon.get(); }
    public BooleanProperty isPlayerProperty() { return isPlayer; }
    public Pokemon getPokemon() { return faintedPokemon.get(); }
    public boolean isPlayer() { return isPlayer.get(); }

}
