package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.SessionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StatsView extends VBox {
    private SceneController sceneController;
    private SessionData sessionData;
    private ObservableList<Stat> stats = FXCollections.observableArrayList();
    private ListView<Stat> statsList = new ListView<>();
    private ObservableList<Stat> EV = FXCollections.observableArrayList();
    private ListView<Stat> EVList = new ListView<>();

    public StatsView(SceneController sceneController, SessionData sessionData) {
        this.sceneController = sceneController;
        this.sessionData = sessionData;
        setupUI();
    }

//    public static class Stat {
//        private StringProperty name;
//        public void setName(String name) { nameProperty().set(name); }
//        public String getName() { return name.get(); }
//        public StringProperty nameProperty() {
//            if (name == null) name = new SimpleStringProperty(this, "name");
//            return name;
//        }
//
//        private IntegerProperty value;
//        public void setValue(int value) { valueProperty().set(value); }
//        public int getValue() { return value.get(); }
//        public IntegerProperty valueProperty() {
//            if (value == null) value = new SimpleIntegerProperty(this, "value");
//            return value;
//        }
//
//        public Stat(String name, int value) {
//            setName(name);
//            setValue(value);
//        }
//    }

    private class Stat {
        private String name;
        private int value;

        public Stat(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    private ListCell<Stat> createStatCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Stat stat, boolean empty) {
                super.updateItem(stat, empty);
                if (empty || stat == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox container = new HBox();

                    Text nameText = new Text(stat.getName() + ": ");
                    Text valueText = new Text(String.valueOf(stat.value));

                    HBox valueBox = new HBox(valueText);
                    valueBox.setAlignment(Pos.CENTER_RIGHT);
                    HBox.setHgrow(valueBox, javafx.scene.layout.Priority.ALWAYS);

                    container.getChildren().addAll(nameText, valueBox);
                    setGraphic(container);
                }
            }
        };
    }

    private void setupUI() {
        Text name = new Text("Bulbasaur");
        Text level = new Text("Level: 5");
        Text hp = new Text("HP: " + sessionData.currentPlayerPokemon.getStats().getCurrentHP() + "/" + sessionData.currentPlayerPokemon.getStats().getMaxHP());
        Text statsText = new Text("Stats");
        Text EVText = new Text("EVs");

        stats.addAll(
                new Stat("HP", (int) sessionData.currentPlayerPokemon.getStats().getCurrentHP()),
                new Stat("Attack", (int) sessionData.currentPlayerPokemon.getStats().getAttack()),
                new Stat("Defense", (int) sessionData.currentPlayerPokemon.getStats().getDefense()),
                new Stat("Sp. Attack", (int) sessionData.currentPlayerPokemon.getStats().getSpecialAttack()),
                new Stat("Sp. Defense", (int) sessionData.currentPlayerPokemon.getStats().getSpecialDefense()),
                new Stat("Speed", (int) sessionData.currentPlayerPokemon.getStats().getSpeed())
        );


        statsList.setItems(stats);
        statsList.setPrefHeight(140);
        statsList.setMinHeight(140);
        statsList.setMaxHeight(140);
        statsList.setPrefWidth(120);
        statsList.setMinWidth(120);
        statsList.setMaxWidth(120);

        statsList.setCellFactory(lv -> createStatCell());

        EV.addAll(
                new Stat("HP", (int) sessionData.currentPlayerPokemon.getStats().getEV().getHP()),
                new Stat("Attack", (int) sessionData.currentPlayerPokemon.getStats().getEV().getAttack()),
                new Stat("Defense", (int) sessionData.currentPlayerPokemon.getStats().getEV().getDefense()),
                new Stat("Sp. Attack", (int) sessionData.currentPlayerPokemon.getStats().getEV().getSpecialAttack()),
                new Stat("Sp. Defense", (int) sessionData.currentPlayerPokemon.getStats().getEV().getSpecialDefense()),
                new Stat("Speed", (int) sessionData.currentPlayerPokemon.getStats().getEV().getSpeed())
        );
        EVList.setItems(EV);
        EVList.setPrefHeight(140);
        EVList.setMinHeight(140);
        EVList.setMaxHeight(140);
        EVList.setPrefWidth(120);
        EVList.setMinWidth(120);
        EVList.setMaxWidth(120);

        EVList.setCellFactory(lv -> createStatCell());

        getChildren().addAll(name, level, hp, statsText, statsList, EVText, EVList);
    }
}
