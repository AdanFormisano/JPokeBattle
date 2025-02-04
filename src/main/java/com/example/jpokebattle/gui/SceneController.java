package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene menuScene;
    private Scene playScene;

    public SceneController(Stage stage, SessionData sessionData) {
        this.stage = stage;
        menuScene = new Scene(new MenuView(this), 900, 600);
        playScene = new Scene(new PlayView(this, sessionData), 900, 600);
    }

    public void showMenu() {
        stage.setScene(menuScene);
    }

    public void showBattle() {
        stage.setScene(playScene);
    }
}
