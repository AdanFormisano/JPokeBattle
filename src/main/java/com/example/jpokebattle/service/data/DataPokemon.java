package com.example.jpokebattle.service.data;

import com.example.jpokebattle.poke.EffortValue;
import com.example.jpokebattle.poke.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataPokemon {
    private int id;
    private String name;
    private List<Type> type;
    private String ability;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private EffortValueDTO effortValue;
    private List<DataMoveBasic> moves;
    private String levelingRate;
    private int expYield;
    @JsonProperty(required = false)
    private EvolutionData evolution;
    private String spriteFrontPath;
    private String spriteBackPath;

    // Getters
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public List<Type> getType() { return this.type; }
    public String getAbility() { return this.ability; }
    public int getHp() { return this.hp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public List<DataMoveBasic> getMoves() { return this.moves; }
    public String getLevelingRate() { return this.levelingRate; }
    public int getExpYield() { return this.expYield; }
    public EvolutionData getEvolution() { return this.evolution; }
    public String getSpriteFrontPath() { return spriteFrontPath; }
    public String getSpriteBackPath() { return this.spriteBackPath; }
    public EffortValue getEffortValue() {
        return new EffortValue(
                effortValue.getHp(),
                effortValue.getAttack(),
                effortValue.getDefense(),
                effortValue.getSpecialAttack(),
                effortValue.getSpecialDefense(),
                effortValue.getSpeed()
        );
    }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(List<Type> type) { this.type = type; }
    public void setAbility(String ability) { this.ability = ability; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setMoves(List<DataMoveBasic> moves) { this.moves = moves; }
    public void setLevelingRate(String levelingRate) { this.levelingRate = levelingRate; }
    public void setExpYield(int expYield) { this.expYield = expYield; }
    public void setEvolution(EvolutionData evolution) { this.evolution = evolution; }
    public void setSpriteFrontPath(String spritePath) { this.spriteFrontPath = spritePath; }
    public void setSpriteBackPath(String backSpritePath) { this.spriteBackPath = backSpritePath; }
    public void setEffortValue(EffortValueDTO effortValue) { this.effortValue = effortValue; }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type.get(0) + '\'' +
                ", type='" + type.get(1) + '\'' +
                ", ability='" + ability + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                ", moves=" + moves +
                ", levelingRate=" + levelingRate +
                ", expYield=" + expYield +
                '}';
    }
}
