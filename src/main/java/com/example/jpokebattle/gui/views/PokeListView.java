package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.gui.SceneController;
import com.example.jpokebattle.poke.Pokemon;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PokeListView extends VBox {
    GameController gc = GameController.getInstance();
    SceneController sceneController;

    public PokeListView(SceneController sceneController) {
        this.sceneController = sceneController;
        setupUI();
    }

    private void setupUI() {
        setPrefWidth(200);
        setStyle("-fx-border-color: transparent gray transparent transparent; " +
                "-fx-border-width: 1;");

        Text pokeListTitle = new Text("Your Pokemon");
        getChildren().add(pokeListTitle);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(200);
        scrollPane.setFitToWidth(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        VBox pokeListContainer = new VBox();

        for (int i = 0; i < gc.getGameData().playerPokemons.size(); i++) {
            Pokemon pokemon = gc.getGameData().playerPokemons.get(i);

            VBox pokeContainer = new VBox();
            HBox pokeInfo = new HBox();
            Text pokeName = new Text(pokemon.getName());

            HBox pokeLevelContainer = new HBox();
            Text levelText = new Text("Level: ");
            Text levelValue = new Text();
            levelValue.textProperty().bind(pokemon.getStats().levelProperty().asString());
            pokeLevelContainer.getChildren().addAll(levelText, levelValue);

            HBox pokeHPContainer = new HBox();
            Text hpText = new Text("HP: ");
            Text hpValue = new Text();
            hpValue.textProperty().bind(pokemon.getStats().currentHPProperty().asString("%.1f").concat("/").concat(pokemon.getStats().maxHPProperty().asString()));
            pokeHPContainer.getChildren().addAll(hpText, hpValue);

            ImageView pokeImage = new ImageView(pokemon.getSpriteFront());
            pokeImage.setPreserveRatio(true);
            pokeImage.setFitHeight(50);

            if (pokemon.isFainted || pokemon == gc.getCurrentBattlePokemon()) {
                pokeContainer.setDisable(true);
                pokeContainer.setStyle("-fx-opacity: 0.5;");
            } else {
                pokeContainer.setOnMouseEntered(e -> {
                    pokeContainer.setStyle("-fx-background-color: #fcdfdf;");
                });

                pokeContainer.setOnMouseExited(e -> {
                    pokeContainer.setStyle("-fx-background-color: transparent;");
                });

                pokeContainer.setOnMouseClicked(e -> {
                    sceneController.subSelectionCallback.accept(pokemon);
                    sceneController.onBattleStart();
                });
            }

            pokeInfo.getChildren().addAll(pokeImage, pokeLevelContainer, pokeHPContainer);
            pokeContainer.getChildren().addAll(pokeName, pokeInfo);
            pokeListContainer.getChildren().add(pokeContainer);
        }
        scrollPane.setContent(pokeListContainer);
        getChildren().add(scrollPane);
    }
}
