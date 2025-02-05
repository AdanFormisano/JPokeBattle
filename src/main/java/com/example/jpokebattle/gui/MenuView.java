package com.example.jpokebattle.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuView extends VBox {
    private SceneController sceneController;

    public MenuView(SceneController sceneController) {
        this.sceneController = sceneController;
        init();
    }

    private void init() {
        setSpacing(10);
        setPadding(new Insets(10));

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> sceneController.showPokemonChoice());

        Text menuText = new Text("Menu View");

        getChildren().addAll(menuText, playButton);
    }
}
