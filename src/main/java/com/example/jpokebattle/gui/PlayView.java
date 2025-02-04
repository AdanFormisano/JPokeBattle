package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayView extends HBox {
    private SceneController sceneController;
    private SessionData sessionData;

    public PlayView(SceneController sceneController, SessionData sessionData) {
        this.sceneController = sceneController;
        this.sessionData = sessionData;
        setupUI();
    }

    private void setupUI() {
//        Button backButton = new Button("Back to Menu");
//        backButton.setOnAction(e -> sceneController.showMenu());
        ScrollPane scrollPane = new ScrollPane();

        VBox statsContainer = new VBox();
        VBox playingViews = new VBox();

        statsContainer.setPrefWidth(200);

        scrollPane.setContent(statsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        VBox.setVgrow(statsContainer, Priority.ALWAYS);
        statsContainer.setPadding(new Insets(10));
        statsContainer.setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        StatsView statsPlayer = new StatsView(sceneController, sessionData);
        StatsView statsOpponent = new StatsView(sceneController, sessionData);
        Separator separator = new Separator();
        VBox.setVgrow(statsPlayer, Priority.ALWAYS);
        VBox.setVgrow(statsOpponent, Priority.ALWAYS);

        separator.setPadding(new Insets(10, 0, 10, 0));

        statsContainer.getChildren().addAll(statsPlayer, separator, statsOpponent);

        getChildren().addAll(scrollPane, playingViews);
    }

}
