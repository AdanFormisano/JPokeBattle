package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.BattleOutcome;
import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.game.GameStateListener;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.poke.Pokemon;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    public SceneController sceneController;
    private GameController gameController = GameController.getInstance();
    private DynamicViewModel dvModel = new DynamicViewModel();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gameController.startGame();
        setupUI();

        primaryStage.show();
    }


    private void setupUI() {
        sceneController = new SceneController(primaryStage, dvModel);
        primaryStage.setResizable(false);
        primaryStage.setTitle("JPokeBattle");
        primaryStage.getIcons().add(new Image("file:src/main/resources/assets/bulbasaur.png"));
    }
}