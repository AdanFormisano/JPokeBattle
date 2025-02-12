package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.poke.Move;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class MovesView extends VBox {
    SceneController sceneController;
    GameController gc = GameController.getInstance();
    List<Move> playerMoves;

    public MovesView(SceneController sceneController) {
        this.sceneController = sceneController;
        playerMoves = gc.getSessionData().currentPlayerPokemon.getMoveList();
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

            this.setOnMouseClicked(e -> {
                // Change background color
                setStyle("-fx-background-color: #efc595; -fx-border-color: transparent gray transparent transparent; -fx-border-width: 1;");

                sceneController.choseMove(move.getName());
            });

            getChildren().addAll(moveContainer, moveDescription);
        }
    }
}
