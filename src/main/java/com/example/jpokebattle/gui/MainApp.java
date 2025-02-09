package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.BattleEvent;
import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.game.GameStateListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application implements GameStateListener {
    private Stage primaryStage;
    public SceneController sceneController;
    private GameController gameController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupGame();
        setupUI();

        gameController.startGame();

        primaryStage.show();
    }

    private void setupGame() {
        gameController = GameController.getInstance();
        gameController.setGameStateListener(this);
    }

    private void setupUI() {
        sceneController = new SceneController(primaryStage,gameController);
        primaryStage.setResizable(false);
        primaryStage.setTitle("JPokeBattle");
        primaryStage.getIcons().add(new Image("file:src/main/resources/assets/bulbasaur.png"));
    }

    @Override
    public void onGameStart() {
        sceneController.showMenu();
    }

    @Override
    public void onNeedPokemonSelection() {
        sceneController.showPokemonChoice();
    }

    @Override
    public void onBattleStart() {
        sceneController.showBattle();
    }

    @Override
    public void onBattleEnd(boolean playerWon) {
        if (playerWon) {
            System.out.println("Player won!");
            // sceneController.showWinScreen();
        } else {
            System.out.println("Player lost!");
            // sceneController.showLoseScreen();
        }
    }
}