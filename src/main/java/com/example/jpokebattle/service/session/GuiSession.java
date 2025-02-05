package com.example.jpokebattle.service.session;

import com.example.jpokebattle.gui.SceneController;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiSession implements PokeGameSession{
    private Stage primaryStage;
    private SceneController sceneController;
    public SessionData sessionData = new SessionData(true);
    public SessionGame sessionGame = new SessionGame(sessionData);

    public GuiSession(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void startSession() {
        sceneController = new SceneController(primaryStage, sessionData);
        sceneController.showMenu();
        primaryStage.setResizable(false);
        primaryStage.setTitle("JPokeBattle");
        primaryStage.getIcons().add(new Image("file:src/main/resources/assets/bulbasaur.png"));
        primaryStage.show();
    }

    @Override
    public void endSession() {

    }

    @Override
    public void playSession() {

    }
}