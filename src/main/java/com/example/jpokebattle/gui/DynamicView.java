package com.example.jpokebattle.gui;

import com.example.jpokebattle.poke.Move;
import com.example.jpokebattle.service.session.SessionData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class DynamicView extends VBox {
    SceneController sceneController;
    SessionData sessionData;

    public DynamicView(SceneController sceneController, SessionData sessionData) {
        this.sceneController = sceneController;
        this.sessionData = sessionData;
        setupUI();
    }

    private void setupUI() {
        setPrefWidth(520);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        MovesView movesView = new MovesView(sessionData);
        PokeListView pokeListView = new PokeListView(sessionData);
        getChildren().addAll(movesView);

        sceneController.showingPokemonListProperty().addListener((obs, oldVal, newVal) -> {
            getChildren().clear();
            if (newVal) {
                getChildren().add(pokeListView);
            } else {
                getChildren().add(movesView);
            }
        });
    }

    private class MoveView extends VBox {
        Move move;

        public MoveView(Move move) {
            this.move = move;
            setupUI();
        }

        private void setupUI() {
            setPrefWidth(200);
            setStyle("-fx-border-color: transparent gray transparent transparent; " +
                    "-fx-border-width: 1;");

            Text moveName = new Text(move.getName());
            Text moveType = new Text(move.getType().toString());
            Text moveCategory = new Text(move.getCategory());
            Text movePower = new Text("Power: " + move.getPower());
            Text moveAccuracy = new Text("Accuracy: " + move.getAccuracy());
            Text movePP = new Text("PP: " + move.getPP());

            HBox moveContainer = new HBox();
            VBox leftContainer = new VBox();
            leftContainer.getChildren().addAll(moveName, moveType, moveCategory);
            VBox rightContainer = new VBox();
            rightContainer.getChildren().addAll(movePower, moveAccuracy, movePP);

            Text moveDescription = new Text("Lorem Ipsum");

            moveContainer.getChildren().addAll(leftContainer, rightContainer);

            this.setOnMouseEntered(e -> {
                // Change background color
                setStyle("-fx-background-color: lightgray; -fx-border-color: transparent gray transparent transparent; -fx-border-width: 1;");
            });

            this.setOnMouseExited(e -> {
                // Change background color
                setStyle("-fx-border-color: transparent gray transparent transparent; -fx-border-width: 1;");
            });

            getChildren().addAll(moveContainer, moveDescription);
        }
    }

    private class MovesView extends VBox {
        SessionData sessionData;
        List<Move> playerMoves;

        public MovesView(SessionData sessionData) {
            this.sessionData = sessionData;
            playerMoves = sessionData.currentPlayerPokemon.getMoveList();
            setupUI();
        }

        private void setupUI() {
            setPrefWidth(200);
            setStyle("-fx-border-color: transparent gray transparent transparent; " +
                    "-fx-border-width: 1;");

            HBox firstRow = new HBox();
            HBox secondRow = new HBox();

            for (Move move : playerMoves) {
                if (playerMoves.indexOf(move) < 2) {
                    MoveView moveView = new MoveView(move);
                    firstRow.getChildren().add(moveView);
                } else {
                    MoveView moveView = new MoveView(move);
                    secondRow.getChildren().add(moveView);
                }
            }

            getChildren().addAll(firstRow, secondRow);
        }
    }

    private class PokeListView extends VBox {
        SessionData sessionData;

        public PokeListView(SessionData sessionData) {
            this.sessionData = sessionData;
            setupUI();
        }

        private void setupUI() {
            setPrefWidth(200);
            setStyle("-fx-border-color: transparent gray transparent transparent; " +
                    "-fx-border-width: 1;");

            Text pokeListTitle = new Text("Your Pokemon");
            getChildren().add(pokeListTitle);

            for (int i = 0; i < sessionData.playerPokemons.size(); i++) {
                HBox pokeContainer = new HBox();
                Text pokeName = new Text(sessionData.playerPokemons.get(i).getName());
                Text pokeLevel = new Text("Level: " + sessionData.playerPokemons.get(i).getStats().getLevel());
                Text pokeHP = new Text("HP: " + sessionData.playerPokemons.get(i).getStats().getCurrentHP() + "/" + sessionData.playerPokemons.get(i).getStats().getMaxHP());
                pokeContainer.getChildren().addAll(pokeLevel, pokeHP);
                getChildren().addAll(pokeName, pokeContainer);
            }

        }
    }
}
