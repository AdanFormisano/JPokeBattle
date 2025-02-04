package com.example.jpokebattle.service.data;

import com.example.jpokebattle.poke.EffortValue;

import java.util.List;

public class DataPokemon {
    private int id;
    private String name;
    private List<DataType> type;
    private String ability;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private EffortValue effortValue;
    private List<DataMoveBasic> moves;
    private String levelingRate;
    private int expYield;
    private String spriteFrontPath;
    private String spriteBackPath;

    // Getters
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public List<DataType> getType() { return this.type; }
    public String getAbility() { return this.ability; }
    public int getHp() { return this.hp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }
    public EffortValue getEffortValue() { return this.effortValue; }
    public List<DataMoveBasic> getMoves() { return this.moves; }
    public String getLevelingRate() { return this.levelingRate; }
    public int getExpYield() { return this.expYield; }
    public String getSpriteFrontPath() { return spriteFrontPath; }
    public String getSpriteBackPath() { return this.spriteBackPath; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(List<DataType> type) { this.type = type; }
    public void setAbility(String ability) { this.ability = ability; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setEffortValue(EffortValue effortValue) { this.effortValue = effortValue; }
    public void setMoves(List<DataMoveBasic> moves) { this.moves = moves; }
    public void setLevelingRate(String levelingRate) { this.levelingRate = levelingRate; }
    public void setExpYield(int expYield) { this.expYield = expYield; }
    public void setSpriteFrontPath(String spritePath) { this.spriteFrontPath = spritePath; }
    public void setSpriteBackPath(String backSpritePath) { this.spriteBackPath = backSpritePath; }

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
