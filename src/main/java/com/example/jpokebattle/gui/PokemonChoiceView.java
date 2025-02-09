package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.service.session.SessionData;
import com.example.jpokebattle.service.session.SessionGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class PokemonChoiceView extends VBox {
    private SessionData sessionData;
    private SessionGame sessionGame;
    private Consumer<String> onPokemonSelected;

    public PokemonChoiceView(Consumer<String> onPokemonSelected, GameController gameController) {
        this.onPokemonSelected = onPokemonSelected;
        this.sessionData = gameController.getSessionData();
        this.sessionGame = gameController.getSessionGame();
        setupUI();
    }

    public void setupUI() {
        // Set the preferred size for the main container
        setPrefHeight(600);
        setPrefWidth(900);

        // Center the HBox contents horizontally
        setAlignment(Pos.CENTER);

        // Add spacing between Pokémon
//        setSpacing(50);

        HBox pokemonContainer = new HBox();
        PokemonView bulbasaurView = new PokemonView("Bulbasaur");
        PokemonView charmanderView = new PokemonView("Charmander");
        PokemonView squirtleView = new PokemonView("Squirtle");
        pokemonContainer.getChildren().addAll(bulbasaurView, charmanderView, squirtleView);
        pokemonContainer.setAlignment(Pos.CENTER);


        Text chooseText = new Text("Choose your starting Pokémon!");

        getChildren().addAll(chooseText, pokemonContainer);
    }

    private class PokemonView extends VBox {
        public PokemonView(String name) {
            // Center the contents of each PokemonView vertically and horizontally
            setAlignment(Pos.CENTER);

            // Add some padding around each Pokémon
            setPadding(new Insets(20));

            // Show the pokemon image
            ImageView pokeView = new ImageView(sessionData.pl.getPokemonImage(name, true));

            Button chooseButton = new Button("Choose");
            setMargin(chooseButton, new Insets(30, 0, 0, 0));
            chooseButton.setOnAction(e -> {
                onPokemonSelected.accept(name);
            });

            // Show the pokemon name with some spacing from the image
            Text nameText = new Text(name);
            Text lvlText = new Text("Lvl 1");
            setMargin(nameText, new Insets(10, 0, 0, 0));

            getChildren().addAll(pokeView, nameText, lvlText, chooseButton);
        }
    }
}
