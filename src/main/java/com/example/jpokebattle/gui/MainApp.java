package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.BattleOutcome;
import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.game.GameStateListener;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.poke.Pokemon;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application implements GameStateListener {
    private Stage primaryStage;
    public SceneController sceneController;
    private GameController gameController;
    private DynamicViewModel dvModel = new DynamicViewModel();

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
        sceneController = new SceneController(primaryStage, dvModel);
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
    public void onPokemonFainted(String pokemon) {
        sceneController.showPokemonFainted(pokemon);
    }

    @Override
    public void onPokemonFainted(String faintedPokemon, String playerPokemon, double exp) {
        sceneController.showPokemonFainted(faintedPokemon, playerPokemon, exp);
    }

    @Override
    public void onBattleEnd(BattleOutcome outcome) {
        if (outcome.getPlayerWon()) {
            sceneController.showWinScreen();
        } else {
            System.out.println("Player lost!");
             sceneController.showLoseScreen();
        }
    }

    @Override
    public void onLevelUp(Pokemon pokemon, List<String> moves) {
//        sceneController.showLevelUp(pokemon, moves);
    }
}