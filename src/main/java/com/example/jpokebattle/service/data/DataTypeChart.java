package com.example.jpokebattle.service.data;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DataTypeChart {
    private static final Map<DataType, Map<DataType, Double>> DAMAGE_MULTIPLIERS = new EnumMap<>(DataType.class);

    static {
        // NORMAL chart
        DAMAGE_MULTIPLIERS.put(DataType.NORMAL, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.NORMAL).put(DataType.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.NORMAL).put(DataType.BUG, 1.0);
        DAMAGE_MULTIPLIERS.get(DataType.NORMAL).put(DataType.GHOST, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.NORMAL).put(DataType.STEEL, 0.5);

        // FIGHTING chart
        Map<DataType, Double> fightingMap = new EnumMap<>(DataType.class);
        DAMAGE_MULTIPLIERS.put(DataType.FIGHTING, fightingMap);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.NORMAL, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.GHOST, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.PSYCHIC, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.ICE, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIGHTING).put(DataType.DARK, 2.0);

        // FLYING chart
        Map<DataType, Double> flyingMap = new EnumMap<>(DataType.class);
        DAMAGE_MULTIPLIERS.put(DataType.FLYING, flyingMap);
        DAMAGE_MULTIPLIERS.get(DataType.FLYING).put(DataType.FIGHTING, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FLYING).put(DataType.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FLYING).put(DataType.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FLYING).put(DataType.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FLYING).put(DataType.ELECTRIC, 0.5);

        // POISON chart
        Map<DataType, Double> poisonMap = new EnumMap<>(DataType.class);
        DAMAGE_MULTIPLIERS.put(DataType.POISON, poisonMap);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.GROUND, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.GHOST, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.STEEL, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.POISON).put(DataType.GRASS, 2.0);

        // GROUND chart
        DAMAGE_MULTIPLIERS.put(DataType.GROUND, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.FLYING, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.POISON, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GROUND).put(DataType.ELECTRIC, 2.0);

        // ROCK chart
        DAMAGE_MULTIPLIERS.put(DataType.ROCK, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.GROUND, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ROCK).put(DataType.ICE, 2.0);

        // BUG chart
        DAMAGE_MULTIPLIERS.put(DataType.BUG, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.GHOST, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.BUG).put(DataType.DARK, 2.0);

        // GHOST chart
        DAMAGE_MULTIPLIERS.put(DataType.GHOST, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.GHOST).put(DataType.NORMAL, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.GHOST).put(DataType.GHOST, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GHOST).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GHOST).put(DataType.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GHOST).put(DataType.DARK, 0.5);

        // STEEL chart
        DAMAGE_MULTIPLIERS.put(DataType.STEEL, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.ELECTRIC, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.STEEL).put(DataType.ICE, 2.0);

        // FIRE chart
        DAMAGE_MULTIPLIERS.put(DataType.FIRE, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.ICE, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.FIRE).put(DataType.DRAGON, 0.5);

        // WATER chart
        DAMAGE_MULTIPLIERS.put(DataType.WATER, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.WATER).put(DataType.DRAGON, 0.5);

        // GRASS chart
        DAMAGE_MULTIPLIERS.put(DataType.GRASS, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.WATER, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.GRASS).put(DataType.DRAGON, 0.5);

        // ELECTRIC chart
        DAMAGE_MULTIPLIERS.put(DataType.ELECTRIC, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.GROUND, 0.0);
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.WATER, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.ELECTRIC, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ELECTRIC).put(DataType.DRAGON, 0.5);

        // PSYCHIC chart
        DAMAGE_MULTIPLIERS.put(DataType.PSYCHIC, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.PSYCHIC).put(DataType.FIGHTING, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.PSYCHIC).put(DataType.POISON, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.PSYCHIC).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.PSYCHIC).put(DataType.PSYCHIC, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.PSYCHIC).put(DataType.DARK, 0.0);

        // ICE chart
        DAMAGE_MULTIPLIERS.put(DataType.ICE, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.ICE, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.ICE).put(DataType.DRAGON, 2.0);

        // DRAGON chart
        DAMAGE_MULTIPLIERS.put(DataType.DRAGON, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.DRAGON).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.DRAGON).put(DataType.DRAGON, 2.0);

        // DARK chart
        DAMAGE_MULTIPLIERS.put(DataType.DARK, new EnumMap<>(DataType.class));
        DAMAGE_MULTIPLIERS.get(DataType.DARK).put(DataType.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.DARK).put(DataType.GHOST, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.DARK).put(DataType.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(DataType.DARK).put(DataType.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(DataType.DARK).put(DataType.DARK, 0.5);
    }

    public static double getMultiplier(DataType attackDataType, DataType defenseDataType) {
        return DAMAGE_MULTIPLIERS
                .getOrDefault(attackDataType, Collections.emptyMap())
                .getOrDefault(defenseDataType, 1.0);
    }
}
