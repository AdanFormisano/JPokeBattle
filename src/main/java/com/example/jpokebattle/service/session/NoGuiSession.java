package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Player;
import com.example.jpokebattle.game.GameData;

import java.io.IOException;
import java.util.Scanner;

public class NoGuiSession implements PokeGameSession {
    GameData gameData;
    Scanner scanner = new Scanner(System.in);

    public NoGuiSession() throws IOException {
        gameData = new GameData(false);
    }

    @Override
    public void startSession() {
        // Init Session data
        System.out.println("   __     ______   ______     __  __     ______     ______     ______     ______   ______   __         ______    \n" +
                "  /\\ \\   /\\  == \\ /\\  __ \\   /\\ \\/ /    /\\  ___\\   /\\  == \\   /\\  __ \\   /\\__  _\\ /\\__  _\\ /\\ \\       /\\  ___\\   \n" +
                " _\\_\\ \\  \\ \\  _-/ \\ \\ \\/\\ \\  \\ \\  _\"-.  \\ \\  __\\   \\ \\  __<   \\ \\  __ \\  \\/_/\\ \\/ \\/_/\\ \\/ \\ \\ \\____  \\ \\  __\\   \n" +
                "/\\_____\\  \\ \\_\\    \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\    \\ \\_\\    \\ \\_\\  \\ \\_____\\  \\ \\_____\\ \n" +
                "\\/_____/   \\/_/     \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/\\/_/     \\/_/     \\/_/   \\/_____/   \\/_____/ \n" +
                "                                                                                                                 \n" +
                "\n");

        System.out.println("Welcome to JPokeBattle! Let's start the game!");
        System.out.println("Please enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        gameData.setPlayer(player);
        System.out.println("Hello, " + gameData.player.getName() + "! Remember you Gotta catch'em all!");

        // Assign pokemon to player
    }

    @Override
    public void endSession() {

    }

    @Override
    public void playSession() {
//        SessionGame sessionGame = new SessionGame(sessionData);
    }
}
