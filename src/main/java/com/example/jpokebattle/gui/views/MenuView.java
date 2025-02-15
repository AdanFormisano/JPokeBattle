package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.SceneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        ImageView title = new ImageView(new Image("file:src/main/resources/assets/Title.png", 600, 100, false, false));
        title.setPreserveRatio(true);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> sceneController.onNeedPokemonSelection());

        setAlignment(Pos.CENTER);
        getChildren().addAll(title, playButton);
    }
}
