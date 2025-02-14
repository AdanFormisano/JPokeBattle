package com.example.jpokebattle.service.data;

import java.util.List;

public record LeaderboardEntry(List<LeaderboardPokeEntry> team, int level ) {
    public record LeaderboardPokeEntry(String name, int level) {
    }
}
