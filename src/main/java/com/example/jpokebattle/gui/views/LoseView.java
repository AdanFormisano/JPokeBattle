package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.BattleOutcome;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.poke.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class LoseView extends HBox {
    List<Pokemon> playerPokemon;
    List<BattleOutcome> battleOutcomes;
    Pokemon opponentPokemon;
    int level;

    public LoseView(List<Pokemon> playerPokemon, List<BattleOutcome> battleOutcomes, Pokemon opponentPokemon, int level) {
        this.playerPokemon = playerPokemon;
        this.battleOutcomes = battleOutcomes;
        this.opponentPokemon = opponentPokemon;
        this.level = level;

        for (Pokemon pokemon : playerPokemon) {
            System.out.println(pokemon.getName());
        }

        setupUI();
    }

    private void setupUI() {
        VBox playerPokemonContainer = new VBox();
        VBox battleWinsContainer = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox opponentPokemonContainer = new VBox();

        scrollPane.setContent(battleWinsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(200);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        playerPokemonContainer.setPrefWidth(200);
        playerPokemonContainer.setAlignment(Pos.CENTER);

        battleWinsContainer.setPrefWidth(200);
        battleWinsContainer.setAlignment(Pos.CENTER);

        opponentPokemonContainer.setPrefWidth(400);
        opponentPokemonContainer.setAlignment(Pos.CENTER);

        Text playerPokemonLabel = new Text("Your Pokemon:");
        Text battleWinsLabel = new Text("Battle Wins:");
        Text lostLabel = new Text("YOU LOST!");
        Text opponentPokemonLabel = new Text("Opponent Pokemon:");

        playerPokemonContainer.getChildren().add(playerPokemonLabel);
        battleWinsContainer.getChildren().add(battleWinsLabel);
        opponentPokemonContainer.getChildren().addAll(lostLabel, opponentPokemonLabel);

//        setStyle("-fx-border-color: transparent gray gray transparent; " +
//                "-fx-border-width: 1;");

        setPlayerPokemonContiner(playerPokemonContainer);
        setOpponentPokemonContainer(opponentPokemonContainer);
        setBattleOutcomesContainer(battleWinsContainer);

        getChildren().addAll(playerPokemonContainer, opponentPokemonContainer, scrollPane);
    }

    private HBox pokemonEntry(Pokemon pokemon) {
        HBox pokemonContainer = new HBox();

        Text pokemonText = new Text(pokemon.getName());
        ImageView pokemonImage = new ImageView(pokemon.getSpriteFront());
        pokemonImage.setFitWidth(64);
        pokemonImage.setFitHeight(64);
        pokemonContainer.setAlignment(Pos.CENTER_LEFT);
        pokemonContainer.getChildren().addAll(pokemonImage, pokemonText);

        return pokemonContainer;
    }

    private void setPlayerPokemonContiner(VBox playerPokemonContainer) {
        for (Pokemon pokemon : playerPokemon) {
            var pokemonContainer = pokemonEntry(pokemon);
            playerPokemonContainer.getChildren().add(pokemonContainer);
        }
    }

    private void setOpponentPokemonContainer(VBox opponentPokemonContainer) {
        Text opponentPokemonText = new Text(opponentPokemon.getName());
        ImageView opponentPokemonImage = new ImageView(opponentPokemon.getSpriteFront());
        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setOnAction(e -> SceneController.getInstance().onLeaderboardShow());
        opponentPokemonContainer.getChildren().addAll(opponentPokemonText, opponentPokemonImage, leaderboardButton);
    }

    private void setBattleOutcomesContainer(VBox battleWinsContainer) {
        VBox outcomeContainer = new VBox();
        for (BattleOutcome battleOutcome : battleOutcomes) {
            VBox pokemonForLevelContainer = new VBox();
            Text outcomeLevel = new Text(String.valueOf(battleOutcome.getLevel()));

            for (Pokemon pokemon : battleOutcome.getEnemyPokemons()) {
                var pokemonContainer = pokemonEntry(pokemon);
                pokemonForLevelContainer.getChildren().add(pokemonContainer);
            }

            outcomeContainer.getChildren().addAll(outcomeLevel, pokemonForLevelContainer);
        }
        battleWinsContainer.getChildren().add(outcomeContainer);
    }
}
