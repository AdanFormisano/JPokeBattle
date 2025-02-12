package com.example.jpokebattle.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PokeFaintedView extends VBox {

    public PokeFaintedView(ObjectProperty<InfoFainted> faintedInfoProperty) {
        Label message = new Label();
        message.textProperty().bind(Bindings.createStringBinding(() -> {
            InfoFainted info = faintedInfoProperty.get();
            if (info == null || info.getPokemon() == null) {
                return "No data available";
            }
            return info.getPokemon().getName()
                    + (info.isPlayer() ? " (Player)" : " (Opponent)")
                    + " has fainted!";
        }, faintedInfoProperty));

        setPrefWidth(200);
        getChildren().add(message);
    }
}
