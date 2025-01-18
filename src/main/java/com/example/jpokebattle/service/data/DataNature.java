package com.example.jpokebattle.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataNature {
    private final int id;
    private final String name;
    private final String increasedStat;
    private final String decreasedStat;

    @JsonCreator
    public DataNature(@JsonProperty("id") int id,
                      @JsonProperty("name") String name,
                      @JsonProperty("increasedStat") String increasedStat,
                      @JsonProperty("decreasedStat") String decreasedStat) {
        this.id = id;
        this.name = name;
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    // Getters
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getIncreasedStat() { return this.increasedStat; }
    public String getDecreasedStat() { return this.decreasedStat; }

    @Override
    public String toString() {
        return "Nature{" +
                "name='" + name + '\'' +
                ", increasedStat='" + increasedStat + '\'' +
                ", decreasedStat='" + decreasedStat + '\'' +
                '}';
    }
}
