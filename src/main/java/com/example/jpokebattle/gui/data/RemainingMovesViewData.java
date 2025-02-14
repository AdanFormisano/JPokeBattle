package com.example.jpokebattle.gui.data;

import java.util.List;

public record RemainingMovesViewData(String pokemonName, List<String> toLearnMoves, List<String> knownMoves) implements IDynamicViewData {

}
