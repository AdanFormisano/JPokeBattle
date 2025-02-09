package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    SceneController sceneController;
    SessionData sessionData;

    public OptionsView(SceneController sceneController, SessionData sessionData) {
        this.sceneController = sceneController;
        setupUI();
    }

    private void setupUI() {
        Button toggleBattlePokemon = new Button("Pokemon");
        VBox toggleContainer = new VBox();
        toggleContainer.getChildren().add(toggleBattlePokemon);
        toggleBattlePokemon.setOnAction(e -> {
            toggleContainer.getChildren().clear();
            if (sceneController.showingPokemonListProperty().get()) {
                toggleBattlePokemon.setText("Pokemon");
                sceneController.togglePokemonList();
            } else {
                toggleBattlePokemon.setText("Battle!");
                sceneController.togglePokemonList();
            }
        });

        Button toNextLevel = new Button("Next Level");
        toNextLevel.setDisable(true);   // TODO: Implement next level functionality

        Button backButton = new Button("Menu");
        backButton.setOnAction(e -> sceneController.showMenu());

        getChildren().addAll(toggleBattlePokemon, toNextLevel, backButton);
    }
}
