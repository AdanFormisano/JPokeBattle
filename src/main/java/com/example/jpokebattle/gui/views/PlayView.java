package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.data.DynamicViewModel;
import com.example.jpokebattle.gui.data.DynamicViewStatus;
import com.example.jpokebattle.gui.SceneController;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PlayView extends HBox {
    private SceneController sceneController;
    private DynamicViewModel dvModel;

    public StatsView statsPlayer;
    public StatsView statsOpponent;
    public ArenaView arenaView;
    public DynamicView dynamicContainerView;
    public OptionsView optionsView;

    public PlayView(SceneController sceneController, DynamicViewModel dynamicViewModel) {
        this.sceneController = sceneController;
        this.dvModel = dynamicViewModel;

        setupUI();
    }

    private void setupUI() {
        ScrollPane scrollPane = new ScrollPane();
        VBox statsContainer = new VBox();
        statsContainer.setPrefWidth(200);

        scrollPane.setContent(statsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);


        // Set up stats container and view
        VBox.setVgrow(statsContainer, Priority.ALWAYS);
        statsContainer.setPadding(new Insets(10));
        statsContainer.setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        statsPlayer = new StatsView(sceneController, true);
        statsOpponent = new StatsView(sceneController, false);
        Separator separator = new Separator();
        VBox.setVgrow(statsPlayer, Priority.ALWAYS);
        VBox.setVgrow(statsOpponent, Priority.ALWAYS);
        separator.setPadding(new Insets(10, 0, 10, 0));

        statsContainer.getChildren().addAll(statsOpponent, separator, statsPlayer);

        // Set up playing view
        VBox playingViews = new VBox();
//        playingViews.setPrefWidth(700);
        playingViews.setPrefHeight(600);
        HBox.setHgrow(playingViews, Priority.ALWAYS);
        arenaView = new ArenaView(sceneController);
        arenaView.setStyle("-fx-border-color: transparent gray gray transparent; " +
                "-fx-border-width: 1;");

        HBox dynamicAndOptionsContainer = new HBox();
        dynamicContainerView = new DynamicView(sceneController, dvModel);
        optionsView = new OptionsView(sceneController, dvModel);

        dynamicContainerView.showView(new MovesView(sceneController));

        VBox.setVgrow(arenaView, Priority.ALWAYS);
        VBox.setVgrow(dynamicAndOptionsContainer, Priority.ALWAYS);

        dynamicAndOptionsContainer.getChildren().addAll(dynamicContainerView, optionsView);
        playingViews.getChildren().addAll(arenaView, dynamicAndOptionsContainer);

        getChildren().addAll(scrollPane, playingViews);
    }

}
