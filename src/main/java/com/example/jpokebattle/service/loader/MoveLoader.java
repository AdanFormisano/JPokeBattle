package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.poke.Move;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoveLoader {
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private static List<Move> movesCache;
    private static volatile boolean isCacheLoaded = false;

    public MoveLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Move> loadAllMoves() {
        if (!isCacheLoaded) {
            synchronized (MoveLoader.class) {
                if (!isCacheLoaded) {
                    try {
                        List<Move> loadedMoves = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<Move>>(){});
                        movesCache = Collections.unmodifiableList(loadedMoves);
                        isCacheLoaded = true;
                    } catch (IOException e) {
                        System.out.println("Error loading moves from JSON file: " + e.getMessage());
                    }
                }
            }
        }
        return movesCache;
    }

    public Move getMoveByName(String name) {
        return this.loadAllMoves().stream().filter(move -> move.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Move> getMoveByType(String type) {
        return this.loadAllMoves().stream().filter(move -> move.getType().equals(type)).collect(Collectors.toList());
    }
}
