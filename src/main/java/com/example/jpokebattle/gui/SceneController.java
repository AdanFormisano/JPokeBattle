    package com.example.jpokebattle.gui;

    import com.example.jpokebattle.game.BattleOutcome;
    import com.example.jpokebattle.game.GameController;
    import com.example.jpokebattle.game.IGameStateListener;
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

    public class SceneController implements IGameStateListener {
        private Stage stage;
        private Scene menuScene;
        private Scene playScene;
        private DynamicViewModel dvModel;
        private GameController gc = GameController.getInstance();
        private final BooleanProperty canNextLevel = new SimpleBooleanProperty(false);

        public SceneController(Stage stage, DynamicViewModel dynamicViewModel) {
            this.stage = stage;
            this.dvModel = dynamicViewModel;
            gc.setGameStateListener(this);

            menuScene = new Scene(new MenuView(this), 900, 600);
            stage.setScene(menuScene);
        }

        public DynamicViewModel getDynamicViewModel() { return dvModel; }

        public BooleanProperty canNextLevelProperty() { return canNextLevel; }
        public boolean canNextLevel() { return canNextLevel.get(); }
        public void setCanNextLevel(boolean canNextLevel) { this.canNextLevel.set(canNextLevel); }

        public void choseMove(String moveName) {
            gc.onMoveSelected(moveName);
        }

        public void loadNextLevel() {
            gc.generateBattle();
            canNextLevel.set(false);
            onBattleStart();
        }

        public void onForgetMove(String toLearn, String toForget) {
            gc.onForgetMove(toLearn, toForget);
        }

        public void onOfferAccepted() {
            gc.onOfferAccepted();
        }

        @Override
        public void onGameStart() {
            stage.setScene(menuScene);
        }

        @Override
        public void onNeedPokemonSelection() {
            PokemonChoiceView choiceView = new PokemonChoiceView(gc::onPokemonSelected);
            stage.setScene(new Scene(choiceView, 900, 600));
        }

        @Override
        public void onBattleStart() {
            if (playScene != null) {
                ((PlayView) playScene.getRoot()).getChildren().clear();
            }
            playScene = new Scene(new PlayView(this, dvModel), 900, 600);
            stage.setScene(playScene);
        }

        @Override
        public void onPokemonFainted(String pokemon) {
            FaintedViewData data = new FaintedViewData(pokemon);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_FAINTED, data));
        }

        @Override
        public void onPokemonFainted(String faintedPokemon, String playerPokemon, int exp) {
            FaintedViewData data = new FaintedViewData(faintedPokemon, playerPokemon, exp);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_FAINTED, data));
        }

        @Override
        public void onBattleEnd(BattleOutcome outcome) {
            if (outcome.getPlayerWon()) {
                dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.BATTLE_WIN, null));
            } else {
                System.out.println("Player lost!");
                List<BattleOutcome> allButLast = gc.battleOutcomes.subList(0, gc.battleOutcomes.size() - 1);
                Collections.reverse(allButLast);

                stage.setScene(new Scene(new LoseView(gc.battleOutcomes.getLast().getPlayerPokemons(), allButLast,
                        gc.battleOutcomes.getLast().getCurrentOpponentPokemon(), gc.currentLevel), 900, 600));
            }
        }

        @Override
        public void onLevelUp(Pokemon pokemon) {
            LevelUpViewData data = new LevelUpViewData(pokemon.getName(), pokemon.getStats().getLevel());
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.LEVEL_UP, data));
        }

        @Override
        public void onRemainingMoves(String pokemon, List<String> movesToLearn, List<String> knownMoves) {
            RemainingMovesViewData data = new RemainingMovesViewData(pokemon, movesToLearn, knownMoves);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.REMAINING_MOVES, data));
        }

        @Override
        public void onLearnedMoves(String pokemon, List<String> moves) {
            // Show new moves learned message
            LearnedMovesViewData data = new LearnedMovesViewData(pokemon, moves);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.LEARNED_MOVES, data));
        }

        @Override
        public void onPokemonEvolved(Pokemon pokemonEvolved, String pokeFromName) {
            // Show pokemon evolved message
            EvolvedViewData data = new EvolvedViewData(pokemonEvolved, pokeFromName);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.EVOLUTION, data));
            PlayView pv = (PlayView) playScene.getRoot();
            pv.arenaView.setupUI();
        }

        @Override
        public void onPokeOffer(Pokemon pokemon) {
            // Show pokemon offer message
            PokeOfferViewData data = new PokeOfferViewData(pokemon);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_OFFER, data));
        }
    }
