package com.example.jpokebattle.service.loader;

import com.example.jpokebattle.service.data.Leaderboard;
import com.example.jpokebattle.service.data.LeaderboardEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LeaderboardLoader {
    private static final int MAX_TEAM_SIZE = 6;
    private final Path leaderboardPath;
    private final ObjectMapper objectMapper;
    private final ReentrantReadWriteLock lock;
    private volatile Leaderboard leaderboardCache;

    public LeaderboardLoader(String leaderboardPath) throws IOException {
        this.leaderboardPath = Paths.get(leaderboardPath);
        this.objectMapper = new ObjectMapper();
        this.lock = new ReentrantReadWriteLock();

        Files.createDirectories(this.leaderboardPath.getParent());

        loadLeaderboard();
    }

    public Leaderboard loadLeaderboard() {
        lock.readLock().lock();
        try {
            if (leaderboardCache != null) {
                return leaderboardCache;
            }
        } finally {
            lock.readLock().unlock();
        }

        lock.writeLock().lock();
        try {
            if (leaderboardCache != null) {
                return leaderboardCache;
            }
            if (!Files.exists(leaderboardPath)) {
                leaderboardCache = new Leaderboard(new ArrayList<>());
                saveLeaderboard();
            } else {
                List<LeaderboardEntry> entries = objectMapper.readValue(leaderboardPath.toFile(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, LeaderboardEntry.class));
                leaderboardCache = new Leaderboard(entries);
            }
            return leaderboardCache;
        } catch (IOException e) {
            leaderboardCache = new Leaderboard(new ArrayList<>());
            return leaderboardCache;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int addEntry(LeaderboardEntry entry) {
        if (entry.team() == null || entry.team().size() > MAX_TEAM_SIZE) {
            throw new IllegalArgumentException("Team must contain between 1 and " + MAX_TEAM_SIZE + " Pokemon");
        }

        lock.writeLock().lock();
        try {
            List<LeaderboardEntry> currentEntries = new ArrayList<>(loadLeaderboard().entries());
            currentEntries.add(entry);
            currentEntries.sort(Comparator.comparing(LeaderboardEntry::level).reversed());

            leaderboardCache = new Leaderboard(currentEntries);
            saveLeaderboard();

            return currentEntries.indexOf(entry) + 1;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<LeaderboardEntry> getTopEntries(int limit) {
        List<LeaderboardEntry> entries = loadLeaderboard().entries();
        if (limit <= 0 || limit > entries.size()) {
            return entries;
        }
        return entries.subList(0, limit);
    }

    private void saveLeaderboard() {
        try {
            objectMapper.writeValue(leaderboardPath.toFile(), leaderboardCache.entries());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save leaderboard", e);
        }
    }
}