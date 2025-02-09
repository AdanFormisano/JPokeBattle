package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Battle;
import com.example.jpokebattle.game.Trainer;
import com.example.jpokebattle.poke.Pokemon;

import java.util.ArrayList;
import java.util.List;

// Will contain the game loop logic
public class SessionGame {
    SessionData sessionData;
    public static Battle currentBattle;
    int currentLevel = 0;

    public SessionGame(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    private void generateLevel() {
        currentLevel++;

        // Generate a new Trainer
        sessionData.trainer = new Trainer("Trainer", currentLevel);

        // Randomly generate the trainer's pokemon
        sessionData.enemyPokemons = new ArrayList<>(List.of(
                new Pokemon(sessionData.pl.getRandomPokemon(), currentLevel, sessionData.isGUI)
        ));
    }

    public void generateBattle() {
        generateLevel();
        currentBattle = new Battle(sessionData.pl, sessionData.player, sessionData.trainer, sessionData.playerPokemons, sessionData.enemyPokemons);
    }
}
