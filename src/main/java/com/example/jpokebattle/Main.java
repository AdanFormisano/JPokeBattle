package com.example.jpokebattle;

import com.example.jpokebattle.service.session.PokeSoloGameSession;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        // Create a new game session
        PokeSoloGameSession gameSession = new PokeSoloGameSession();

        // Start the game
        gameSession.startGame();
        gameSession.playGame();
        gameSession.endGame();
    }
}
