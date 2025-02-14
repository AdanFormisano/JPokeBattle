package com.example.jpokebattle.gui.data;

public record FaintedViewData(String faintedPokemon, String playerPokemon, int expGained, boolean isPlayer)
        implements IDynamicViewData {

    // Used when the player's pokemon faints:
    public FaintedViewData(String faintedPokemon) {
        this(faintedPokemon, null, 0, true);
    }

    // Used when the enemy's pokemon faints:
    public FaintedViewData(String faintedPokemon, String playerPokemon, int expGained) {
        this(faintedPokemon, playerPokemon, expGained, false);
    }
}