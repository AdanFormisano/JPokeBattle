package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;

public interface MoveStrategy {
    void execute(PokeInBattle attacker, PokeInBattle target);
}

