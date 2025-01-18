package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.service.data.DataMove;
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
    private static List<DataMove> movesCache;
    private static volatile boolean isCacheLoaded = false;

    public MoveLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<DataMove> loadAllMoves() {
        if (!isCacheLoaded) {
            synchronized (MoveLoader.class) {
                if (!isCacheLoaded) {
                    try {
                        List<DataMove> loadedDataMoves = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<DataMove>>(){});
                        movesCache = Collections.unmodifiableList(loadedDataMoves);
                        isCacheLoaded = true;
                    } catch (IOException e) {
                        System.out.println("Error loading moves from JSON file: " + e.getMessage());
                    }
                }
            }
        }
        return movesCache;
    }

    public DataMove getMoveByName(String name) {
        return this.loadAllMoves().stream().filter(dataMove -> dataMove.getName().equals(name)).findFirst().orElse(null);
    }

    public List<DataMove> getMoveByType(String type) {
        return this.loadAllMoves().stream().filter(dataMove -> dataMove.getType().equals(type)).collect(Collectors.toList());
    }
}
