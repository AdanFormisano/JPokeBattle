package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.data.FaintedViewData;
import javafx.scene.control.Label;

public class PokeFaintedView extends AbstractMessageView {

    public PokeFaintedView(FaintedViewData faintedViewData) {
        super(false);

        if (faintedViewData.isPlayer()) {
            Label message = new Label(faintedViewData.faintedPokemon() + "(player) has fainted!");
            getChildren().add(message);

            this.setOnMouseClicked(event -> {
                finished();
            });
        } else {
            Label message = new Label(faintedViewData.faintedPokemon() + "(opponent) has fainted!");
            Label message2 = new Label(faintedViewData.playerPokemon() +
                    " has gained " + faintedViewData.expGained() + " experience points!");

            getChildren().addFirst(message2);
            getChildren().addFirst(message);

            this.setOnMouseClicked(event -> finished());
        }

    }
}
