package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ArenaView extends VBox {
    PlayerBox playerBox;
    OpponentBox opponentBox;
    SessionData sessionData;

    public ArenaView(SceneController sceneController, SessionData sessionData) {
        this.sessionData = sessionData;
        setMaxHeight(USE_COMPUTED_SIZE);
        setMinHeight(USE_COMPUTED_SIZE);
        setPrefHeight(410);
        setMaxWidth(USE_COMPUTED_SIZE);
        setupUI();
    }

    private void setupUI() {
        playerBox = new PlayerBox();
        opponentBox = new OpponentBox();

        getChildren().addAll(opponentBox, playerBox);
    }

    private class PlayerBox extends HBox {
        public PlayerBox() {
            setupUI();
        }

        private void setupUI() {
            Text playerText = new Text("Player");
            prefHeightProperty().bind(ArenaView.this.heightProperty().multiply(0.5));
            setMaxHeight(USE_COMPUTED_SIZE);
            setMinHeight(USE_COMPUTED_SIZE);

            ImageView playerPokemon = new ImageView(sessionData.currentPlayerPokemon.getSpriteBack());

            getChildren().addAll(playerText, playerPokemon);
        }
    }

    private class OpponentBox extends HBox {
        public OpponentBox() {
            setupUI();
        }

        private void setupUI() {
            Text opponentText = new Text("Opponent");
            prefHeightProperty().bind(ArenaView.this.heightProperty().multiply(0.5));
            setMaxHeight(USE_COMPUTED_SIZE);
            setMinHeight(USE_COMPUTED_SIZE);

//            ImageView opponentPokemon = new ImageView(sessionData.curr.getSpriteBack());

            getChildren().addAll(opponentText);
        }
    }
}
