package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.poke.move.AbstractMove;
import com.example.jpokebattle.poke.move.Move;
import com.example.jpokebattle.poke.move.MoveDamage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class MovesView extends VBox {
    SceneController sceneController;
    GameController gc = GameController.getInstance();
    List<AbstractMove> playerMoves;

    public MovesView(SceneController sceneController) {
        this.sceneController = sceneController;
        playerMoves = gc.getSessionData().currentPlayerPokemon.getMoveList();
        setupUI();
    }

    private void setupUI() {
        getChildren().clear();
        setPrefWidth(200);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        HBox firstRow = new HBox();
        HBox secondRow = new HBox();

        for (AbstractMove move : playerMoves) {
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
        AbstractMove move;

        public MoveView(AbstractMove move) {
            this.move = move;
            setupUI();
        }

        private void setupUI() {
            setPrefWidth(200);
            setStyle("-fx-border-color: transparent gray transparent transparent; " +
                    "-fx-border-width: 1;");

            HBox moveContainer = new HBox();
            VBox leftContainer = new VBox();
            VBox rightContainer = new VBox();

            Text moveName = new Text(move.getName());
            Text moveType = new Text(move.getType().toString());
            Text moveCategory = new Text(move.getCategory().toString());
            Text moveAccuracy = new Text("Accuracy: " + move.getAccuracy());
            Text movePP = new Text("PP: " + move.getCurrentPP());

            if (move instanceof MoveDamage moveDamage) {
                Text movePower = new Text("Power: " + moveDamage.getPower());
                rightContainer.getChildren().add(movePower);
            }

            leftContainer.getChildren().addAll(moveName, moveType, moveCategory);
            rightContainer.getChildren().addAll(moveAccuracy, movePP);

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

    public void updateMoves() {
        playerMoves = gc.getSessionData().currentPlayerPokemon.getMoveList();
        setupUI();
    }
}
