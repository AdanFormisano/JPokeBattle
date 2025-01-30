package com.example.jpokebattle.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainUI extends Application {

    private Parent createContent() {
        return new StackPane(new Text("Hello World"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load title image with smoothing disabled
        Image title = new Image("file:src/main/resources/assets/title.png",
                600, // requestedWidth - match your desired width
                0,   // requestedHeight - 0 to maintain aspect ratio
                true, // preserveRatio
                false); // smooth - set to false for pixel art
        ImageView titleView = new ImageView(title);

        // Load Bulbasaur with similar settings
        Image bulbasaur = new Image("file:src/main/resources/assets/bulbasaur.png",
                96, // Use original width
                0, // Use original height
                true, // preserveRatio
                false); // smooth
        ImageView bulbasaurView = new ImageView(bulbasaur);

        Image playButton = new Image("file:src/main/resources/assets/Button_play.png",
                124, // Use original width
                0, // Use original height
                true, // preserveRatio
                false); // smooth
        ImageView playButtonView = new ImageView(playButton);

        Image exitButton = new Image("file:src/main/resources/assets/Button_exit.png",
                124, // Use original width
                0, // Use original height
                true, // preserveRatio
                false); // smooth
        ImageView exitButtonView = new ImageView(exitButton);

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(new Color(0.922, 0.976, 1, 1));

        HBox hbox = new HBox(10); // Added spacing between elements
        hbox.getChildren().addAll(titleView, bulbasaurView);

        VBox vbox = new VBox(10); // Added spacing between elements
        vbox.getChildren().addAll(hbox, playButtonView, exitButtonView);

        root.getChildren().addAll(vbox);

        stage.setTitle("JPokeBattle");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}