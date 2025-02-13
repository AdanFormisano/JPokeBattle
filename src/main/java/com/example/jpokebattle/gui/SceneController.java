    package com.example.jpokebattle.gui;

    import com.example.jpokebattle.game.BattleOutcome;
    import com.example.jpokebattle.game.GameController;
    import com.example.jpokebattle.gui.data.*;
    import com.example.jpokebattle.gui.views.LoseView;
    import com.example.jpokebattle.gui.views.MenuView;
    import com.example.jpokebattle.gui.views.PlayView;
    import com.example.jpokebattle.gui.views.PokemonChoiceView;
    import com.example.jpokebattle.poke.Pokemon;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.util.Collections;
    import java.util.List;

    public class SceneController {
        private Stage stage;
        private Scene menuScene;
        private Scene playScene;
        private DynamicViewModel dvModel;
        private final BooleanProperty canNextLevel = new SimpleBooleanProperty(false);

        private final GameController gc = GameController.getInstance();

        public SceneController(Stage stage, DynamicViewModel dynamicViewModel) {
            this.stage = stage;
            this.dvModel = dynamicViewModel;

            menuScene = new Scene(new MenuView(this), 900, 600);
        }

        public DynamicViewModel getDynamicViewModel() { return dvModel; }

        public BooleanProperty canNextLevelProperty() { return canNextLevel; }
        public boolean canNextLevel() { return canNextLevel.get(); }
        public void setCanNextLevel(boolean canNextLevel) { this.canNextLevel.set(canNextLevel); }

        public void showMenu() {
            stage.setScene(menuScene);
        }

        public void showBattle() {
            if (playScene != null) {
                ((PlayView) playScene.getRoot()).getChildren().clear();
            }
            playScene = new Scene(new PlayView(this, dvModel), 900, 600);
            stage.setScene(playScene);
        }

        public void showPokemonChoice() {
            PokemonChoiceView choiceView = new PokemonChoiceView(gc::onPokemonSelected);
            stage.setScene(new Scene(choiceView, 900, 600));
        }

        public void showWinScreen() {
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.BATTLE_WIN, null));
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

        public void showPokemonFainted(String pokemon) {
            FaintedViewData data = new FaintedViewData(pokemon);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_FAINTED, data));
        }

        public void showPokemonFainted(String faintedPokemon, String playerPokemon, double exp) {
            FaintedViewData data = new FaintedViewData(faintedPokemon, playerPokemon, exp);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_FAINTED, data));
        }

        public void showLevelUp(Pokemon pokemon, List<String> moves) {
//            dynamicViewStatus.set(DynamicViewStatus.LEVEL_UP);
        }

        public void choseMove(String moveName) {
            gc.onMoveSelected(moveName);
        }

        public void loadNextLevel() {
            gc.generateBattle();
            canNextLevel.set(false);
            showBattle();
        }
    }
