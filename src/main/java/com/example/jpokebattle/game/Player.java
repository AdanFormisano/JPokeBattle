package com.example.jpokebattle.game;

public class Player {
    private final String name;
    private final int money;
    private final int wins;

    public Player(String name) {
        this.name = name;
        this.money = 0;
        this.wins = 0;
    }

    public Player(String name, int money, int wins) {
        this.name = name;
        this.money = money;
        this.wins = wins;
    }

    // Getters
    public String getName() { return this.name; }
    public int getMoney() { return this.money; }
    public int getWins() { return this.wins; }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", wins=" + wins +
                '}';
    }
}
