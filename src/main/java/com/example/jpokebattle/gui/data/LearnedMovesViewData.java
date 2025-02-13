package com.example.jpokebattle.gui.data;

import java.util.List;

public record LearnedMovesViewData(String pokemon, List<String> moves) implements DynamicViewData {
}
