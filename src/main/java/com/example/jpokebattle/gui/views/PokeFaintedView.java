package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.data.FaintedViewData;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PokeFaintedView extends VBox {

    public PokeFaintedView(FaintedViewData faintedViewData) {
        setPrefWidth(200);
        Label message = new Label(faintedViewData.getFaintedPokemon() + " has fainted!");

        if (!faintedViewData.isPlayer()) {
            getChildren().add(message);
        } else {
            Label message2 = new Label(faintedViewData.getPlayerPokemon() +
                    " has gained " + faintedViewData.getExpGained() + " experience points!");

            getChildren().addAll(message, message2);
        }
    }
}
