package com.example.jpokebattle.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataNature {
    private int id;
    private String name;
    private String increasedStat;
    private String decreasedStat;

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

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setIncreasedStat(String increasedStat) { this.increasedStat = increasedStat; }
    public void setDecreasedStat(String decreasedStat) { this.decreasedStat = decreasedStat; }

    @Override
    public String toString() {
        return "Nature{" +
                "name='" + name + '\'' +
                ", increasedStat='" + increasedStat + '\'' +
                ", decreasedStat='" + decreasedStat + '\'' +
                '}';
    }
}
