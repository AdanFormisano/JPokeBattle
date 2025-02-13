package com.example.jpokebattle.gui.data;

public class FaintedViewData implements DynamicViewData {
    private final String faintedPokemon;
    private final String playerPokemon;
    private final int expGained;
    private final boolean isPlayer;

    // Used when the player's pokemon faints
    public FaintedViewData(String faintedPokemon) {
        this.faintedPokemon = faintedPokemon;
        this.playerPokemon = null;
        this.expGained = 0;
        this.isPlayer = true;
    }

    // Used when the enemy's pokemon faints
    public FaintedViewData(String faintedPokemon, String playerPokemon, int expGained) {
        this.faintedPokemon = faintedPokemon;
        this.playerPokemon = playerPokemon;
        this.expGained = expGained;
        this.isPlayer = false;
    }

    public int getExpGained() { return expGained; }
    public String getFaintedPokemon() { return faintedPokemon; }
    public String getPlayerPokemon() { return playerPokemon; }
    public boolean isPlayer() { return isPlayer; }
}
