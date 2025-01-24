package com.example.jpokebattle.service.session;

import com.example.jpokebattle.game.Player;

import java.util.Scanner;

public class NoGuiSession implements PokeGameSession {
    SessionData sessionData = new SessionData();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void startGame() {
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
        sessionData.setPlayer(player);
        System.out.println("Hello, " + sessionData.player.getName() + "! Remember you Gotta catch'em all!");
    }

    @Override
    public void endGame() {

    }

    @Override
    public void playGame() {

    }
}
