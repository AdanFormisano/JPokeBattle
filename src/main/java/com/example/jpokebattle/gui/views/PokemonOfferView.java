package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.gui.data.PokeOfferViewData;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PokemonOfferView extends AbstractMessageView {

    public PokemonOfferView(PokeOfferViewData pokeOfferViewData, SceneController sceneController) {
        super(true);
        Label message = new Label(pokeOfferViewData.pokemon().getName() + "[Lv. " + pokeOfferViewData.pokemon().getStats().getLevel() + "] wants to join your team!\nDo you want to add it to your team?");
        Button acceptButton = new Button(pokeOfferViewData.pokemon().getName() + " joins!");
        Button declineButton = new Button("Nope");

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(acceptButton, declineButton);

        getChildren().addFirst(buttonContainer);
        getChildren().addFirst(message);

        acceptButton.setOnAction(event -> {
            sceneController.onOfferAccepted();
            finished();
        });

        declineButton.setOnAction(event -> {
            finished();
        });
    }
}
