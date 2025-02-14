package com.example.jpokebattle;

import com.example.jpokebattle.gui.MainApp;
import com.example.jpokebattle.service.session.NoGuiSession;

import java.io.IOException;

public class JPokeBattle {
    public static void main(String[] args) throws IOException {

        // Create a new nogui session
        if (args.length > 0 && args[0].equals("nogui")) {
            NoGuiSession gameSession = new NoGuiSession();
            gameSession.startSession();
            gameSession.playSession();
            gameSession.endSession();
            return;
        } else {
            MainApp.launch(MainApp.class, args);
        }
    }
}
