package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.DynamicViewUIState;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.*;
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
        pokeListView = new PokeListView(sceneController);

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
            case SUB_SELECTION:
                SubSelectionView subSelectionView = new SubSelectionView(sceneController);
                subSelectionView.setOnFinished(this::processNextState);
                getChildren().add(subSelectionView);
                break;
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
            case REMAINING_MOVES:
                RemainingMovesViewData remainingMovesData = (RemainingMovesViewData) nextUIState.getData();
                RemainingMovesView remainingMovesView = new RemainingMovesView(remainingMovesData, sceneController);
                remainingMovesView.setOnFinished(this::processNextState);
                getChildren().add(remainingMovesView);
                break;
            case EVOLUTION:
                EvolvedViewData evolvedData = (EvolvedViewData) nextUIState.getData();
                EvolvedView evolvedView = new EvolvedView(evolvedData);
                evolvedView.setOnFinished(this::processNextState);
                getChildren().add(evolvedView);
                break;
            case POKEMON_OFFER:
                PokeOfferViewData offerData = (PokeOfferViewData) nextUIState.getData();
                PokemonOfferView offerView = new PokemonOfferView(offerData, sceneController);
                offerView.setOnFinished(this::processNextState);
                getChildren().add(offerView);
                break;
            }
    }

    public void showView(VBox scene) {
        getChildren().clear();
        getChildren().add(scene);
    }
}
