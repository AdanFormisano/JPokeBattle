package com.example.jpokebattle;

import com.example.jpokebattle.service.session.NoGuiSession;

public class Main {
    public static void main(String[] args) {

        // Create a new game session
//        PokeSoloGameSession gameSession = new PokeSoloGameSession();
        NoGuiSession gameSession = new NoGuiSession();

        // Start the game
        gameSession.startSession();
        gameSession.playSession();
        gameSession.endSession();
    }
}
