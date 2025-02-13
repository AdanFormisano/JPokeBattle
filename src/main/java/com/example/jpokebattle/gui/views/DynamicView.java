package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.DynamicViewUIState;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Queue;

public class DynamicView extends VBox {
    SceneController sceneController;
    DynamicViewModel dvModel;
    GameController gc = GameController.getInstance();
    private Queue<DynamicViewUIState> stateQueue = new LinkedList<>();
    private boolean isTransitioning = false;
    private MovesView movesView;
    private PokeListView pokeListView;

    public DynamicView(SceneController sceneController, DynamicViewModel dynamicViewModel) {
        this.sceneController = sceneController;
        this.dvModel = dynamicViewModel;

        setPrefWidth(520);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");
        setupUI();
    }

    private void setupUI() {
        movesView = new MovesView(sceneController);
        pokeListView = new PokeListView();

        dvModel.toggleMovesPokeList.addListener((obs, oldVal, newVal) -> {
            if (!isTransitioning){
                getChildren().clear();
                if (newVal) {
                    getChildren().add(movesView);
                } else {
                    getChildren().add(pokeListView);
                }
            }
        });

        dvModel.currentViewUIStateProperty().addListener((obs, oldState, newState) -> {
            stateQueue.offer(newState);
            if (!isTransitioning) {
                processNextState();
            }
        });
    }

    private void processNextState() {
        DynamicViewUIState nextUIState = stateQueue.poll();
        if (nextUIState == null) {
            isTransitioning = false;
            return;
        }
        isTransitioning = true;
        getChildren().clear();

        switch (nextUIState.getStatus()) {
//                case BATTLE:
//                    getChildren().add(movesView);
//                    break;
//                case POKEMON_SELECTION:
//                    getChildren().add(pokeListView);
//                    break;
            case POKEMON_FAINTED:
                FaintedViewData faintedData = (FaintedViewData) nextUIState.getData();
                PokeFaintedView faintedView = new PokeFaintedView(faintedData);
                faintedView.setOnFinished(this::processNextState);
                getChildren().add(faintedView);
                break;
            case BATTLE_WIN:
                getChildren().add(new WinView(gc.currentLevel, sceneController.canNextLevelProperty()));
                break;
            case LEVEL_UP:
                LevelUpViewData levelUpData = (LevelUpViewData) nextUIState.getData();
                LevelUpView levelUpView = new LevelUpView(levelUpData);
                levelUpView.setOnFinished(this::processNextState);
                getChildren().add(levelUpView);
                movesView.updateMoves();
                break;
            case LEARNED_MOVES:
                LearnedMovesViewData learnedMovesData = (LearnedMovesViewData) nextUIState.getData();
                LearnedMovesView learnedMovesView = new LearnedMovesView(learnedMovesData);
                learnedMovesView.setOnFinished(this::processNextState);
                getChildren().add(learnedMovesView);
                movesView.updateMoves();
                break;
            }
    }

    public void showView(VBox scene) {
        getChildren().clear();
        getChildren().add(scene);
    }
}
