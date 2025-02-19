    package com.example.jpokebattle.gui;

    import com.example.jpokebattle.game.BattleOutcome;
    import com.example.jpokebattle.game.GameController;
    import com.example.jpokebattle.game.IGameStateListener;
    import com.example.jpokebattle.gui.data.*;
    import com.example.jpokebattle.gui.views.*;
    import com.example.jpokebattle.poke.Pokemon;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.util.Collections;
    import java.util.List;
    import java.util.function.Consumer;

    public class SceneController implements IGameStateListener {
        private static SceneController instance; // Istanza Singleton
        private Stage stage;
        private Scene menuScene;
        private Scene playScene;
        private DynamicViewModel dvModel;
        private GameController gc = GameController.getInstance();
        private final BooleanProperty canNextLevel = new SimpleBooleanProperty(false);
        public Consumer<Pokemon> subSelectionCallback;

        private SceneController(Stage stage, DynamicViewModel dynamicViewModel) {
            this.stage = stage;
            this.dvModel = dynamicViewModel;
            gc.setGameStateListener(this);

            menuScene = new Scene(new MenuView(this), 900, 600);
            stage.setScene(menuScene);
        }

        public static SceneController getInstance(Stage stage, DynamicViewModel dynamicViewModel) {
            if (instance == null) {
                instance = new SceneController(stage, dynamicViewModel);
            }
            return instance;
        }

        public static SceneController getInstance() {
            if (instance == null) {
                throw new IllegalStateException("SceneController non è stato inizializzato. Chiama getInstance(Stage, DynamicViewModel) prima.");
            }
            return instance;
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

        public void onPokemonSwitch(Pokemon pokemon) {
            gc.pokemonSwitch(pokemon);
            onBattleStart();
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
            LearnedMovesViewData data = new LearnedMovesViewData(pokemon, moves);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.LEARNED_MOVES, data));
        }

        @Override
        public void onPokemonEvolved(Pokemon pokemonEvolved, String pokeFromName) {
            EvolvedViewData data = new EvolvedViewData(pokemonEvolved, pokeFromName);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.EVOLUTION, data));
            PlayView pv = (PlayView) playScene.getRoot();
            pv.arenaView.setupUI();
        }

        @Override
        public void onPokeOffer(Pokemon pokemon) {
            PokeOfferViewData data = new PokeOfferViewData(pokemon);
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.POKEMON_OFFER, data));
        }

        @Override
        public void onPokemonSwitch(Consumer<Pokemon> callback) {
            subSelectionCallback = callback;
            dvModel.setUIState(new DynamicViewUIState(DynamicViewStatus.SUB_SELECTION, null));
        }

        public void onLeaderboardShow() {
            stage.setScene(new Scene(new LeaderboardView(), 900, 600));
        }
    }
