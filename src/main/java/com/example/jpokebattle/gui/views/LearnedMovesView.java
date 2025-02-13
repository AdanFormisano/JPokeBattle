package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.gui.data.LearnedMovesViewData;
import javafx.scene.control.Label;

public class LearnedMovesView extends MessageView {
    public LearnedMovesView(LearnedMovesViewData learnedMovesData) {
        StringBuilder sb = new StringBuilder();
        sb.append("Your ").append(learnedMovesData.pokemon()).append(" now knows the following moves:\n");
        for (String move : learnedMovesData.moves()) {
            sb.append(move).append("\n");
        }

        getChildren().addFirst(new Label(sb.toString()));

        this.setOnMouseClicked(event -> finished());
    }
}
