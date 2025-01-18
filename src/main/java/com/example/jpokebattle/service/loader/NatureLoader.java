package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.poke.Nature;
import com.example.jpokebattle.service.data.DataNature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NatureLoader {
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private static List<DataNature> natureCache;
    private volatile boolean isCacheLoaded = false;

    public NatureLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<DataNature> loadAllNatures() {
        if (!isCacheLoaded) {
            synchronized (NatureLoader.class) {
                if (!isCacheLoaded) {
                    try {
                        List<DataNature> loadedDataNatures = objectMapper.readValue(new File(this.jsonFilePath), new TypeReference<List<DataNature>>(){});
                        natureCache = Collections.unmodifiableList(loadedDataNatures);
                        isCacheLoaded = true;
                    } catch (IOException e) {
                        System.out.println("Error loading natures from JSON file: " + e.getMessage());
                    }
                }
            }
        }
        return natureCache;
    }

    public Nature getNatureById(int id) {
        DataNature dataNature = this.loadAllNatures().stream().filter(nature -> nature.getId() == id).findFirst().orElse(null);
        if (dataNature == null) {
            return null;
        }
        return new Nature(dataNature.getName(), dataNature.getIncreasedStat(), dataNature.getDecreasedStat());
    }

    public Nature getNatureByName(String name) {
        DataNature dataNature = this.loadAllNatures().stream().filter(nature -> nature.getName().equals(name)).findFirst().orElse(null);
        if (dataNature == null) {
            return null;
        }
        return new Nature(dataNature.getName(), dataNature.getIncreasedStat(), dataNature.getDecreasedStat());
    }

    public String[] getNatureDataById(int id) {
        DataNature dataNature = this.loadAllNatures().stream().filter(nature -> nature.getId() == id).findFirst().orElse(null);
        if (dataNature == null) {
            return null;
        }
        return new String[] { dataNature.getName(), dataNature.getIncreasedStat(), dataNature.getDecreasedStat() };
    }
}
