package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.data.EvolvedViewData;
import javafx.scene.control.Label;

public class EvolvedView extends AbstractMessageView {
    public EvolvedView(EvolvedViewData evolvedData) {
        Label label = new Label("Your " + evolvedData.pokeNameFrom() + " has evolved into " + evolvedData.pokemon().getName() + "!");
        this.getChildren().addFirst(label);

        this.setOnMouseClicked(e -> finished());
    }
}
