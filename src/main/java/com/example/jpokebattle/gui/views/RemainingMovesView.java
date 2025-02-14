package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.RemainingMovesViewData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class RemainingMovesView extends AbstractMessageView {
    private final List<String> knownMoves;
    private int remainingMovesCount;
    private SceneController sceneController;

    public RemainingMovesView(RemainingMovesViewData remainingMovesData, SceneController sceneController) {
        super(false);
        this.knownMoves = remainingMovesData.knownMoves();
        this.sceneController = sceneController;

        String pokemonName = remainingMovesData.pokemonName();
        List<String> toLearnMoves = remainingMovesData.toLearnMoves();
        remainingMovesCount = toLearnMoves.size();

        for (String move : toLearnMoves) {
            VBox moveContainer = createMoveChoice(move, pokemonName);
            getChildren().add(moveContainer);
        }

        setOnMouseClicked(event -> {
            if (remainingMovesCount == 0) {
                finished();
            }
        });
    }

    private VBox createMoveChoice(String moveToLearn, String pokemonName) {
        VBox moveContainer = new VBox(10);
//        moveContainer.setPadding(new Insets(10));

        Label moveLabel = new Label(pokemonName + " wants to learn " + moveToLearn +
                ", but already knows four moves.\nChoose a move to forget, or cancel to stop learning the move.");

        KnownMovesView knownMovesView = new KnownMovesView(() -> removeMoveContainer(moveContainer), moveToLearn);

        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            removeMoveContainer(moveContainer);
            e.consume();
        });

        moveContainer.getChildren().addAll(moveLabel, knownMovesView, cancel);
        return moveContainer;
    }

    private void removeMoveContainer(VBox moveContainer) {
        getChildren().remove(moveContainer);
        remainingMovesCount--;
        if (remainingMovesCount == 0) {
            // Optionally show a message that there are no more moves to learn.
            if (getChildren().stream().noneMatch(node -> node instanceof Label &&
                    ((Label) node).getText().equals("You have no more moves to learn."))) {
                getChildren().addFirst(new Label("You have no more moves to learn."));
            }
        }
    }

    private class KnownMovesView extends VBox {
        public KnownMovesView(Runnable onKnownMoveSelected, String toLearn) {
            setSpacing(5);
            HBox row1 = new HBox(10);
            HBox row2 = new HBox(10);

            for (String toForget : knownMoves) {
                HBox knownMovesContainer = new HBox();
                knownMovesContainer.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
                Label moveLabel = new Label(toForget);
                moveLabel.setPadding(new Insets(5));
                knownMovesContainer.getChildren().add(moveLabel);

                moveLabel.setOnMouseEntered(event -> moveLabel.setStyle("-fx-background-color: lightgray;"));
                moveLabel.setOnMouseExited(event -> moveLabel.setStyle("-fx-background-color: transparent;"));

                moveLabel.setOnMouseClicked(event -> {
                    sceneController.onForgetMove(toLearn, toForget);
                    onKnownMoveSelected.run();
                    event.consume();
                });

                if (row1.getChildren().size() < 2) {
                    row1.getChildren().add(knownMovesContainer);
                } else {
                    row2.getChildren().add(knownMovesContainer);
                }
            }
            getChildren().addAll(row1, row2);
        }
    }
}
