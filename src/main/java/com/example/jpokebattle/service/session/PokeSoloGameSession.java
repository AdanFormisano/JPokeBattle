package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Battle;

import java.util.Arrays;

public class PokeSoloGameSession implements PokeGameSession {
    SessionData sessionData = new SessionData();

    @Override
    public void startGame() {
        // Init Session data
        System.out.println("Starting game");
    }

    @Override
    public void endGame() {
        System.out.println("Ending game");
    }

    @Override
    public void playGame() {
        System.out.println("Playing game");

        Battle battle = new Battle(sessionData.pl, sessionData.player, sessionData.trainer, sessionData.playerPokemons, sessionData.enemyPokemons);
    }
}
