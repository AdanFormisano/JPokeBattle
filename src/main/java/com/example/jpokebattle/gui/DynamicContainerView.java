package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.game.SessionData;
import javafx.scene.layout.VBox;

public class DynamicContainerView extends VBox {
    SceneController sceneController;
    SessionData sessionData;
    GameController gc = GameController.getInstance();

    MovesView movesView;
    PokeListView pokeListView;

    public DynamicContainerView(SceneController sceneController) {
        this.sceneController = sceneController;
        setPrefWidth(520);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");
        setupUI();
    }

    private void setupUI() {
        MovesView movesView = new MovesView(sceneController);
        PokeListView pokeListView = new PokeListView();

        getChildren().addAll(movesView);

        sceneController.dynamicViewStatusProperty().addListener((obs, oldVal, newVal) -> {
            getChildren().clear();
            switch (newVal) {
                case BATTLE:
                    getChildren().add(movesView);
                    break;
                case POKEMON_SELECTION:
                    getChildren().add(pokeListView);
                    break;
                case POKEMON_FAINTED:
                    System.out.println("PokemonFaintedView creating...");
                    getChildren().add(new PokeFaintedView(sceneController.faintedInfoProperty()));
                    System.out.println("PokemonFaintedView added");
                    break;
            }
        });
    }

    public void showView(VBox scene) {
        getChildren().clear();
        getChildren().add(scene);
    }
}
