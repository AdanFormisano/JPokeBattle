package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.gui.data.DynamicViewStatus;
import com.example.jpokebattle.gui.data.FaintedViewData;
import javafx.scene.layout.VBox;

public class DynamicView extends VBox {
    SceneController sceneController;
    DynamicViewModel dvModel;
    GameController gc = GameController.getInstance();

    MovesView movesView;
    PokeListView pokeListView;

    public DynamicView(SceneController sceneController, DynamicViewModel dynamicViewModel) {
        this.sceneController = sceneController;
        this.dvModel = dynamicViewModel;

        setPrefWidth(520);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");
        setupUI();
    }

    private void setupUI() {
        MovesView movesView = new MovesView(sceneController);
        PokeListView pokeListView = new PokeListView();

        if (dvModel.getUIState().getStatus() == DynamicViewStatus.BATTLE) {
            getChildren().add(movesView);
        }

        dvModel.currentViewUIStateProperty().addListener((obs, oldState, newState) -> {
            getChildren().clear();
            switch (newState.getStatus()) {
                case BATTLE:
                    getChildren().add(movesView);
                    break;
                case POKEMON_SELECTION:
                    getChildren().add(pokeListView);
                    break;
                case POKEMON_FAINTED:
                    FaintedViewData faintedData = (FaintedViewData) newState.getData();
                    getChildren().add(new PokeFaintedView(faintedData));
                    System.out.println("PokemonFaintedView added");
                    break;
                case BATTLE_WIN:
                    getChildren().add(new WinView(gc.currentLevel, sceneController.canNextLevelProperty()));
                    break;
                case LEVEL_UP:
//                    getChildren().add(new LevelUpView(sceneController));
                    break;
            }
        });
    }

    public void showView(VBox scene) {
        getChildren().clear();
        getChildren().add(scene);
    }
}
