package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.loader.NatureLoader;

import java.util.Random;

public class Nature {
    private final String name;
    private final String increasedStat;
    private final String decreasedStat;

    private static final Random random = new Random();

    // Generate nature with specific name, increased stat, and decreased stat
    public Nature(String name, String increasedStat, String decreasedStat) {
        this.name = name;
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    // Generate random nature
    public Nature() {
        int natureIndex = random.nextInt(26);

        NatureLoader natureLoader = new NatureLoader("src/main/resources/com/example/jpokebattle/data/nature.json");
        String[] natureData = natureLoader.getNatureDataById(natureIndex);

        this.name = natureData[0];
        this.increasedStat = natureData[1];
        this.decreasedStat = natureData[2];
    }

    public String getName() { return name; }
    public String getIncreasedStat() { return increasedStat; }
    public String getDecreasedStat() { return decreasedStat; }
}
