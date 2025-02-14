package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.service.data.DataMove;
import com.example.jpokebattle.service.loader.MoveLoader;

public class MoveFactory {
    public static AbstractMove getMove(String moveName) {
        MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
        DataMove moveData = ml.getMoveByName(moveName);

        return switch (moveData.getCategory()) {
            case PHYSICAL, SPECIAL ->
                    new MoveDamage(moveData.getName(), moveData.getType(), moveData.getCategory(), moveData.getPower(), moveData.getAccuracy(), moveData.getPriority(), moveData.getPp());
            case STATUS ->
                    new MoveStatus(moveData.getName(), moveData.getType(), moveData.getCategory(), moveData.getPp(), moveData.getAccuracy(), moveData.getPriority(), moveData.getStatType(), moveData.getStageLevel());
        };
    }
}
