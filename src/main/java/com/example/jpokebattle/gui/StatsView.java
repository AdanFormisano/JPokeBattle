package com.example.jpokebattle.gui;

import com.example.jpokebattle.game.GameController;
import com.example.jpokebattle.poke.Stats;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class StatsView extends VBox {
    private SceneController sceneController;
    private GameController gc = GameController.getInstance();

    public StatsView(SceneController sceneController, boolean isPlayer) {
        this.sceneController = sceneController;
        if (isPlayer) {
            setupUIPlayer();
        } else {
            setupUIOpponent();
        }
    }

    private HBox createStatLabel(String statName,ObservableValue<String> value) {
        HBox container = new HBox(10);
        Text nameText = new Text(statName + ": ");
        Text valueText = new Text();

        valueText.textProperty().bind(value);

        HBox valueBox = new HBox(valueText);
        valueBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(valueBox, Priority.ALWAYS);

        container.getChildren().addAll(nameText, valueBox);
        return container;
    }

    private void setupUIPlayer() {
        Text name = new Text(gc.getSessionData().currentPlayerPokemon.getName());
        getChildren().add(name);
        // Get the stats object from your Pokemon
        Stats stats = gc.getSessionData().currentPlayerPokemon.getStats();

        // Create bindings for combined stats
        createBindings(stats);
//        createBindings();
    }

    private void setupUIOpponent() {
        Text name;
        if (Objects.equals(GameController.currentBattle.getCurrentEnemyPokemon().getName(), GameController.currentBattle.getCurrentPlayerPokemon().getName())) {
            name = new Text(GameController.currentBattle.getCurrentEnemyPokemon().getName() + " (Enemy)");
        } else {
            name = new Text(GameController.currentBattle.getCurrentEnemyPokemon().getName());
        }
        getChildren().add(name);

        // Get the stats object from your Pokemon
        Stats stats = GameController.currentBattle.getCurrentEnemyPokemon().getStats();

        // Create bindings for combined stats
        createBindings(stats);
    }

    private void createBindings(Stats stats) {
        StringBinding hpBinding = (StringBinding) Bindings.concat(
                stats.currentHPProperty().asString("%.1f"),
                "/", stats.maxHPProperty().asString("%.0f")
        );

        StringBinding expBinding = (StringBinding) Bindings.concat(stats.currentExpProperty().asString("%.0f"),
                "/", stats.getTotalExpNeeded() + " EXP"
        );

        HBox hpBox = createStatLabel("HP", hpBinding);
        HBox expBox = createStatLabel("EXP", expBinding);
        HBox levelBox = createStatLabel("Level", stats.levelProperty().asString());
        HBox attackBox = createStatLabel("Attack", stats.attackProperty().asString());
        HBox defenseBox = createStatLabel("Defense", stats.defenseProperty().asString());
        HBox specialAttackBox = createStatLabel("Special Attack", stats.specialAttackProperty().asString());
        HBox specialDefenseBox = createStatLabel("Special Defense", stats.specialDefenseProperty().asString());
        HBox speedBox = createStatLabel("Speed", stats.speedProperty().asString());

        HBox EVhpBox = createStatLabel("EV HP", stats.getEV().hpProperty().asString());
        HBox EVAttackBox = createStatLabel("EV Attack", stats.getEV().attackProperty().asString());
        HBox EVDefenseBox = createStatLabel("EV Defense", stats.getEV().defenseProperty().asString());
        HBox EVSpecialAttackBox = createStatLabel("EV Special Attack", stats.getEV().specialAttackProperty().asString());
        HBox EVSpecialDefenseBox = createStatLabel("EV Special Defense", stats.getEV().specialDefenseProperty().asString());
        HBox EVSpeedBox = createStatLabel("EV Speed", stats.getEV().speedProperty().asString());

        getChildren().addAll(hpBox, expBox, levelBox, attackBox, defenseBox, specialAttackBox, specialDefenseBox, speedBox, EVhpBox, EVAttackBox, EVDefenseBox, EVSpecialAttackBox, EVSpecialDefenseBox, EVSpeedBox);
    }
}
