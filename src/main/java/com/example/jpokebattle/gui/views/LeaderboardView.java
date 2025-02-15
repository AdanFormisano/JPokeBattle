package com.example.jpokebattle.gui.views;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.service.data.LeaderboardEntry;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class LeaderboardView extends VBox {
    GameController gc = GameController.getInstance();

    public LeaderboardView() {
        setPrefWidth(520);

        List<LeaderboardEntry> topTen = gc.getGameData().leaderboardLoader.getTopEntries(10);

        VBox entriesContainer = new VBox(10); // 10px spacing between entries
        int rank = 1;

        for (LeaderboardEntry entry : topTen) {
            VBox leaderboardEntry = new VBox(2);
            leaderboardEntry.setPadding(new Insets(10));
            leaderboardEntry.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

            Label rankLabel = new Label("Rank: " + rank++);
            Label level = new Label("Level: " + entry.level());
            LeaderboardEntryTeamView teamView = new LeaderboardEntryTeamView(entry.team());

            leaderboardEntry.getChildren().addAll(rankLabel, level, teamView);
            entriesContainer.getChildren().add(leaderboardEntry);
        }

        getChildren().add(entriesContainer);
    }

    private class LeaderboardEntryTeamView extends HBox {
        public LeaderboardEntryTeamView(List<LeaderboardEntry.LeaderboardPokeEntry> teamEntries) {
            setSpacing(8);
            for (LeaderboardEntry.LeaderboardPokeEntry pokeEntry : teamEntries) {
                VBox teamEntry = new VBox(2);
                Label name = new Label(pokeEntry.name());
                Label level = new Label("Level: " + pokeEntry.level());
                teamEntry.getChildren().addAll(name, level);
                getChildren().add(teamEntry);
            }
        }
    }
}
