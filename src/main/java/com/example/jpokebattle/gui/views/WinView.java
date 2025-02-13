package com.example.jpokebattle.gui.views;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WinView extends VBox {

    public WinView(int level, BooleanProperty canNextLevelProperty) {
        Label message = new Label("You passed level " + level + "!");
        Label message2 = new Label("You can now proceed to the next level.");

        canNextLevelProperty.set(true);

        setPrefWidth(200);
        getChildren().addAll(message, message2);
    }
}
