package com.example.jpokebattle.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    SceneController sceneController;

    public OptionsView(SceneController sceneController) {
        this.sceneController = sceneController;
        setupUI();
    }

    private void setupUI() {
        Button toggleBattlePokemon = new Button("Pokemon");
        VBox toggleContainer = new VBox();
        toggleContainer.getChildren().add(toggleBattlePokemon);
        toggleBattlePokemon.setOnAction(e -> {
            toggleContainer.getChildren().clear();
            if (sceneController.getDynamicViewStatus() == DynamicViewStatus.BATTLE) {
                toggleBattlePokemon.setText("Pokemon");
                sceneController.setDynamicViewStatus(DynamicViewStatus.POKEMON_SELECTION);
            } else {
                toggleBattlePokemon.setText("Battle!");
                sceneController.setDynamicViewStatus(DynamicViewStatus.BATTLE);
            }
        });

        Button toNextLevel = new Button("Next Level");
        toNextLevel.disableProperty().bind(sceneController.canNextLevelProperty().not());
        toNextLevel.setOnAction(e -> sceneController.loadNextLevel());

        Button backButton = new Button("Menu");
        backButton.setOnAction(e -> sceneController.showMenu());

        getChildren().addAll(toggleBattlePokemon, toNextLevel, backButton);
    }
}
