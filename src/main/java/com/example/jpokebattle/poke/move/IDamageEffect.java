package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.game.PokeInBattle;
import com.example.jpokebattle.poke.Pokemon;

public interface IDamageEffect {
    void dealDamage(PokeInBattle attacker, PokeInBattle target);
}
