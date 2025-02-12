package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WinView extends VBox {
    SceneController sceneController;
    GameController gc = GameController.getInstance();

    public WinView(SceneController sceneController) {
        this.sceneController = sceneController;
        setupUI();
    }

    private void setupUI() {
        HBox opponentDefeated = new HBox();
        HBox winText = new HBox();

//        Text opponentDefeatedText = new Text("You have defeated " + gc.battleOutcomes.get(gc.currentLevel - 1).player + "!");
    }
}
