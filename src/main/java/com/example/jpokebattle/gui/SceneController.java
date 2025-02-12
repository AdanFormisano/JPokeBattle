    package com.example.jpokebattle.gui;

    import com.example.jpokebattle.game.BattleOutcome;
    import com.example.jpokebattle.game.GameController;
    import com.example.jpokebattle.poke.Pokemon;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.ObjectProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.beans.property.SimpleObjectProperty;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.util.Collections;
    import java.util.List;

    public class SceneController {
        private Stage stage;
        private Scene menuScene;
        private Scene playScene;
        private final ObjectProperty<DynamicViewStatus> dynamicViewStatus = new SimpleObjectProperty<>(DynamicViewStatus.BATTLE);
        private final ObjectProperty<FaintedInfo> faintedInfo = new SimpleObjectProperty<>();

        private final GameController gc = GameController.getInstance();

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
            PokemonChoiceView choiceView = new PokemonChoiceView(gc::onPokemonSelected);
            stage.setScene(new Scene(choiceView, 900, 600));
        }

        public void showWinScreen() {
            stage.setScene(new Scene(new WinView(this), 900, 600));
        }

        public void showLoseScreen() {
            for (Pokemon pokemon : gc.battleOutcomes.getLast().getPlayerPokemons()) {
                System.out.println(pokemon.getName());
            }

            List<BattleOutcome> allButLast = gc.battleOutcomes.subList(0, gc.battleOutcomes.size() - 1);
            Collections.reverse(allButLast);

            stage.setScene(new Scene(new LoseView(gc.battleOutcomes.getLast().getPlayerPokemons(), allButLast,
                    gc.battleOutcomes.getLast().getCurrentOpponentPokemon(), gc.currentLevel), 900, 600));
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
