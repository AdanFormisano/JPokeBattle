package com.example.jpokebattle.gui;

import com.example.jpokebattle.poke.Pokemon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InfoWin {
    private final ObjectProperty<Pokemon> faintedPokemon = new SimpleObjectProperty<>();
    private final ObjectProperty<Pokemon> playerPokemon = new SimpleObjectProperty<>();
    private final IntegerProperty exp = new SimpleIntegerProperty();

    public InfoWin(Pokemon faintedPokemon, Pokemon playerPokemon, int exp) {
        this.faintedPokemon.set(faintedPokemon);
        this.playerPokemon.set(playerPokemon);
        this.exp.set(exp);
    }

    public ObjectProperty<Pokemon> faintedPokemonProperty() { return faintedPokemon; }
    public Pokemon getFaintedPokemon() { return faintedPokemon.get(); }
    public ObjectProperty<Pokemon> playerPokemonProperty() { return playerPokemon; }
    public Pokemon getPlayerPokemon() { return playerPokemon.get(); }
    public IntegerProperty expProperty() { return exp; }
    public int getExp() { return exp.get(); }
}
