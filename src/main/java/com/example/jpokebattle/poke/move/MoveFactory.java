package com.example.jpokebattle.poke.move;

import com.example.jpokebattle.service.data.DataMove;
import com.example.jpokebattle.service.loader.MoveLoader;

public class MoveFactory {
    public static Move getMove(String moveName) {
        MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
        DataMove moveData = ml.getMoveByName(moveName);

        MoveStrategy strategy = switch (moveData.getCategory()) {
            case PHYSICAL, SPECIAL -> new MoveDamageStrategy(moveData.getPower());
            case STATUS -> new MoveStatusStrategy(moveData.getStatType(), moveData.getStageLevel());
        };

        return new Move(moveData.getName(), moveData.getType(), moveData.getCategory(),
                moveData.getPp(), moveData.getAccuracy(), moveData.getPriority(), strategy);
    }
}

