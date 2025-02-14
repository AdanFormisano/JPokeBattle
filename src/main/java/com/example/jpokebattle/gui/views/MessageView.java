package com.example.jpokebattle.gui.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class MessageView extends VBox {
    private Runnable onFinished;

    public MessageView() {
        setPrefWidth(200);
        Label message3 = new Label("Click to continue...");
        getChildren().add(message3);
    }

    public void setOnFinished(Runnable onFinished) {
        this.onFinished = onFinished;
    }

    void finished() {
        if (onFinished != null) {
            onFinished.run();
        }
    }
}
