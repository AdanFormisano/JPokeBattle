package com.example.jpokebattle.game;

import com.example.jpokebattle.poke.Pokemon;

import java.util.List;
import java.util.function.Consumer;

public interface IGameStateListener {
    void onGameStart();
    void onNeedPokemonSelection();
    void onBattleStart();
    void onPokemonFainted(String pokemon);
    void onPokemonFainted(String faintedPokemon, String playerPokemon, int exp);
    void onBattleEnd(BattleOutcome outcome);
    void onLevelUp(Pokemon pokemon);
    void onRemainingMoves(String pokemon, List<String> movesToLearn, List<String> knownMoves);
    void onLearnedMoves(String pokemon, List<String> moves);
    void onPokemonEvolved(Pokemon pokemonEvolved, String pokeFromName);
    void onPokeOffer(Pokemon pokemon);
    void onPokemonSwitch(Consumer<Pokemon> callback);
}
