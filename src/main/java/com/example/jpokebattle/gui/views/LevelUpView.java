package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.data.LevelUpViewData;
import javafx.scene.control.Label;

public class LevelUpView extends AbstractMessageView {
    public LevelUpView(LevelUpViewData levelUpData) {
        super(false);
        Label label = new Label("Your " + levelUpData.pokemonName() + " has leveled up to level " + levelUpData.level() + "!");

        getChildren().addFirst(label);

        this.setOnMouseClicked(event -> finished());
    }
}
