package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.DynamicViewUIState;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.gui.data.DynamicViewStatus;
import com.example.jpokebattle.gui.SceneController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    SceneController sceneController;
    DynamicViewModel dvModel;

    public OptionsView(SceneController sceneController, DynamicViewModel dvModel) {
        this.sceneController = sceneController;
        this.dvModel = dvModel;
        setupUI();
    }

    private void setupUI() {
        Button toggleBattlePokemon = new Button("Pokemon");
        VBox toggleContainer = new VBox();
        toggleContainer.getChildren().add(toggleBattlePokemon);
        toggleBattlePokemon.setOnAction(e -> {
            toggleContainer.getChildren().clear();
            if (dvModel.getToggleMovesPokeList()) {
                toggleBattlePokemon.setText("Pokemon");
                dvModel.toggleMovesPokeList.set(false);
            } else {
                toggleBattlePokemon.setText("Battle!");
                dvModel.toggleMovesPokeList.set(true);
            }
        });

        Button toNextLevel = new Button("Next Level");
        toNextLevel.disableProperty().bind(sceneController.canNextLevelProperty().not());
        toNextLevel.setOnAction(e -> sceneController.loadNextLevel());

        Button backButton = new Button("Menu");
        backButton.setOnAction(e -> sceneController.onGameStart());

        getChildren().addAll(toggleBattlePokemon, toNextLevel, backButton);
    }
}
