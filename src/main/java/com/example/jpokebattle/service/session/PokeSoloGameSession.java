package com.example.jpokebattle.service.session;

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
    }
}
