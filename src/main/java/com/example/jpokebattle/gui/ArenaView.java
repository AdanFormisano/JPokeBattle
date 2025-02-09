package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import com.example.jpokebattle.service.session.SessionGame;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ArenaView extends VBox {
    PlayerBox playerBox;
    OpponentBox opponentBox;
    SessionData sessionData;
    SessionGame sessionGame;

    public ArenaView(SceneController sceneController) {
        this.sessionData = sceneController.sessionData;
        this.sessionGame = sceneController.sessionGame;
        setMaxHeight(USE_COMPUTED_SIZE);
        setMinHeight(USE_COMPUTED_SIZE);
        setPrefHeight(300);
        setMaxWidth(USE_COMPUTED_SIZE);
        setPrefWidth(700);
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
            HBox pokeView = new HBox();
            pokeView.setPrefWidth(700);
            pokeView.setAlignment(Pos.BOTTOM_LEFT);
            pokeView.getChildren().add(playerPokemon);

            getChildren().addAll(playerText, pokeView);
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

            ImageView opponentPokemon = new ImageView(SessionGame.currentBattle.getCurrentEnemyPokemon().getSpriteFront());
            HBox pokeView = new HBox();
            pokeView.setPrefWidth(700);
            pokeView.setAlignment(Pos.BOTTOM_RIGHT);
            pokeView.getChildren().add(opponentPokemon);

            getChildren().addAll(opponentText, pokeView);
        }
    }
}
