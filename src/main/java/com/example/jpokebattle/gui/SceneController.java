package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene menuScene;
    private Scene playScene;
    private BooleanProperty showingPokemonList = new SimpleBooleanProperty(false);

    private GameController gc = GameController.getInstance();

    public SceneController(Stage stage) {
        this.stage = stage;

        menuScene = new Scene(new MenuView(this), 900, 600);
//        playScene = new Scene(new PlayView(this, sessionData), 900, 600);
    }

    public void showMenu() {
        stage.setScene(menuScene);
    }

    public void showBattle() {
        if (playScene == null) {
            playScene = new Scene(new PlayView(this), 900, 600);
        }

        stage.setScene(playScene);
    }

    public void showPokemonChoice() {
        PokemonChoiceView choiceView = new PokemonChoiceView(pokemon -> gc.onPokemonSelected(pokemon));
        stage.setScene(new Scene(choiceView, 900, 600));
    }

    public BooleanProperty showingPokemonListProperty() {
        return showingPokemonList;
    }

    public void togglePokemonList() {
        showingPokemonList.set(!showingPokemonList.get());
    }

    public void choseMove(String moveName) {
        gc.onMoveSelected(moveName);
    }
}
