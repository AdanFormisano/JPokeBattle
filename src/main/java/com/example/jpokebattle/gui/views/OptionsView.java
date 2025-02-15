package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.DynamicViewUIState;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.gui.data.DynamicViewStatus;
import com.example.jpokebattle.gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    SceneController sceneController = SceneController.getInstance();
    DynamicViewModel dvModel;

    public OptionsView(DynamicViewModel dvModel) {
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

        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setPrefWidth(200);

        getChildren().addAll(toggleBattlePokemon, toNextLevel);
    }
}
