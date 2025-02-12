    package com.example.jpokebattle.gui;

    import com.example.jpokebattle.game.GameController;
    import com.example.jpokebattle.poke.Pokemon;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.ObjectProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.beans.property.SimpleObjectProperty;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    public class SceneController {
        private Stage stage;
        private Scene menuScene;
        private Scene playScene;
        private final ObjectProperty<DynamicViewStatus> dynamicViewStatus = new SimpleObjectProperty<>(DynamicViewStatus.BATTLE);
        private final ObjectProperty<FaintedInfo> faintedInfo = new SimpleObjectProperty<>();

        private GameController gc = GameController.getInstance();

        public SceneController(Stage stage) {
            this.stage = stage;

            menuScene = new Scene(new MenuView(this), 900, 600);
    //        playScene = new Scene(new PlayView(this, sessionData), 900, 600);
        }

        public ObjectProperty<DynamicViewStatus> dynamicViewStatusProperty() { return dynamicViewStatus; }
        public DynamicViewStatus getDynamicViewStatus() { return dynamicViewStatus.get(); }
        public void setDynamicViewStatus(DynamicViewStatus status) { dynamicViewStatus.set(status); }

        public ObjectProperty<FaintedInfo> faintedInfoProperty() { return faintedInfo; }

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

        public void showWinScreen() {
            stage.setScene(new Scene(new WinView(this), 900, 600));
        }

        public void showLoseScreen() {
    //        stage.setScene(new Scene(new LoseView(this), 900, 600));
        }

        public void showPokemonFainted(boolean isPlayer, Pokemon pokemon) {
            faintedInfo.set(new FaintedInfo(pokemon, isPlayer));
            dynamicViewStatus.set(DynamicViewStatus.POKEMON_FAINTED);
            System.out.println("Pokemon fainted set");
        }

        public void choseMove(String moveName) {
            gc.onMoveSelected(moveName);
        }
    }
