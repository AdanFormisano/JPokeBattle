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
            if (dvModel.getUIState().getStatus() == DynamicViewStatus.BATTLE) {
                toggleBattlePokemon.setText("Pokemon");
                dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_SELECTION, null));
            } else {
                toggleBattlePokemon.setText("Battle!");
                dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.BATTLE, null));
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
