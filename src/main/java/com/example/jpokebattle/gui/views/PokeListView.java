package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PokeListView extends VBox {
    GameController gc = GameController.getInstance();

    public PokeListView() {
        setupUI();
    }

    private void setupUI() {
        setPrefWidth(200);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        Text pokeListTitle = new Text("Your Pokemon");
        getChildren().add(pokeListTitle);

        for (int i = 0; i < gc.getSessionData().playerPokemons.size(); i++) {
            HBox pokeContainer = new HBox();
            Text pokeName = new Text(gc.getSessionData().playerPokemons.get(i).getName());
            Text pokeLevel = new Text("Level: " + gc.getSessionData().playerPokemons.get(i).getStats().getLevel());
            Text pokeHP = new Text("HP: " + gc.getSessionData().playerPokemons.get(i).getStats().getCurrentHP() + "/" + gc.getSessionData().playerPokemons.get(i).getStats().getMaxHP());
            pokeContainer.getChildren().addAll(pokeLevel, pokeHP);
            getChildren().addAll(pokeName, pokeContainer);
        }
    }
}
