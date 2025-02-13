package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.LevelUpViewData;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LevelUpView extends MessageView {
    public LevelUpView(LevelUpViewData levelUpData) {
        Label label = new Label("Your " + levelUpData.pokemonName() + " has leveled up to level " + levelUpData.level() + "!");

        getChildren().addFirst(label);

        this.setOnMouseClicked(event -> finished());
    }
}
