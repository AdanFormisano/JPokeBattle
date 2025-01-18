package com.example.jpokebattle.poke;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class TypeChart {
    private static final Map<Type, Map<Type, Double>> DAMAGE_MULTIPLIERS = new EnumMap<>(Type.class);

    static {
        // NORMAL chart
        Map<Type, Double> normalRels = new EnumMap<>(Type.class);
        normalRels.put(Type.ROCK, 0.5);
        normalRels.put(Type.BUG, 1.0);
        normalRels.put(Type.GHOST, 0.0);
        normalRels.put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.put(Type.NORMAL, normalRels);

        // FIGHTING chart
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.NORMAL, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.GHOST, 0.0);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.PSYCHIC, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.ICE, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIGHTING).put(Type.DARK, 2.0);

        // FLYING chart
        DAMAGE_MULTIPLIERS.get(Type.FLYING).put(Type.FIGHTING, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FLYING).put(Type.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FLYING).put(Type.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FLYING).put(Type.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FLYING).put(Type.ELECTRIC, 0.5);

        // POISON chart
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.GROUND, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.GHOST, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.STEEL, 0.0);
        DAMAGE_MULTIPLIERS.get(Type.POISON).put(Type.GRASS, 2.0);

        // GROUND chart
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.FLYING, 0.0);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.POISON, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GROUND).put(Type.ELECTRIC, 2.0);

        // ROCK chart
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.GROUND, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ROCK).put(Type.ICE, 2.0);

        // BUG chart
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.GHOST, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.BUG).put(Type.DARK, 2.0);

        // GHOST chart
        DAMAGE_MULTIPLIERS.get(Type.GHOST).put(Type.NORMAL, 0.0);
        DAMAGE_MULTIPLIERS.get(Type.GHOST).put(Type.GHOST, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GHOST).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GHOST).put(Type.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GHOST).put(Type.DARK, 0.5);

        // STEEL chart
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.ELECTRIC, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.STEEL).put(Type.ICE, 2.0);

        // FIRE chart
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.ROCK, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.BUG, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.STEEL, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.ICE, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.FIRE).put(Type.DRAGON, 0.5);

        // WATER chart
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.FIRE, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.WATER).put(Type.DRAGON, 0.5);

        // GRASS chart
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.FLYING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.POISON, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.ROCK, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.BUG, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.WATER, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.GRASS).put(Type.DRAGON, 0.5);

        // ELECTRIC chart
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.GROUND, 0.0);
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.WATER, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.GRASS, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.ELECTRIC, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ELECTRIC).put(Type.DRAGON, 0.5);

        // PSYCHIC chart
        DAMAGE_MULTIPLIERS.get(Type.PSYCHIC).put(Type.FIGHTING, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.PSYCHIC).put(Type.POISON, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.PSYCHIC).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.PSYCHIC).put(Type.PSYCHIC, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.PSYCHIC).put(Type.DARK, 0.0);

        // ICE chart
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.FLYING, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.GROUND, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.FIRE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.WATER, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.GRASS, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.ICE, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.ICE).put(Type.DRAGON, 2.0);

        // DRAGON chart
        DAMAGE_MULTIPLIERS.get(Type.DRAGON).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.DRAGON).put(Type.DRAGON, 2.0);

        // DARK chart
        DAMAGE_MULTIPLIERS.get(Type.DARK).put(Type.FIGHTING, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.DARK).put(Type.GHOST, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.DARK).put(Type.STEEL, 0.5);
        DAMAGE_MULTIPLIERS.get(Type.DARK).put(Type.PSYCHIC, 2.0);
        DAMAGE_MULTIPLIERS.get(Type.DARK).put(Type.DARK, 0.5);
    }

    public static double getMultiplier(Type attackType, Type defenseType) {
        return DAMAGE_MULTIPLIERS
                .getOrDefault(attackType, Collections.emptyMap())
                .getOrDefault(defenseType, 1.0);
    }
}
