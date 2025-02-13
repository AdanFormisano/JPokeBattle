package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.SceneController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuView extends VBox {
    private final SceneController sceneController;

    public MenuView(SceneController sceneController) {
        this.sceneController = sceneController;
        init();
    }

    private void init() {
        setSpacing(10);
        setPadding(new Insets(10));

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> sceneController.onNeedPokemonSelection());

        Text menuText = new Text("Menu View");

        getChildren().addAll(menuText, playButton);
    }
}
