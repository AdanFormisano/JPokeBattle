package com.example.jpokebattle.poke;

import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.DataMove;
import com.example.jpokebattle.service.data.DataMoveBasic;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.data.DataType;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private final int id;
    private final String name;
    private final BaseStats baseStats;
    private final Stats stats;
    private final Nature nature = new Nature();
    private List<Move> moveList = new ArrayList<>();
    public boolean isFainted = false;
    public Image spriteFront;
    public Image spriteBack;

    public Pokemon (DataPokemon dataPokemon, boolean isGUI) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        baseStats = new BaseStats(dataPokemon.getHp(), dataPokemon.getAttack(), dataPokemon.getDefense(), dataPokemon.getSpecialAttack(), dataPokemon.getSpecialDefense(), dataPokemon.getSpeed(), dataPokemon.getAbility(), dataPokemon.getType());
        stats = new Stats(baseStats, nature, dataPokemon.getLevelingRate(), dataPokemon.getExpYield());
        learnMoves(checkNewMoves());

        // Load sprites
        if (isGUI) {
            System.out.printf("Loading sprites from %s%n", "src/main/resources/assets/Sprite/" + name + ".png");
            System.out.printf("Loading sprites from %s%n", "src/main/resources/assets/BackSprite/" + name + "_back.png");
            spriteFront = new Image("file:" + dataPokemon.getSpriteFrontPath(), 128, 128, true, false);
            spriteBack = new Image("file:" + dataPokemon.getSpriteBackPath(), 128, 128, true, false);
        }
    }

    public Pokemon(DataPokemon dataPokemon, int lvl, boolean isGUI) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        baseStats = new BaseStats(dataPokemon.getHp(), dataPokemon.getAttack(), dataPokemon.getDefense(), dataPokemon.getSpecialAttack(), dataPokemon.getSpecialDefense(), dataPokemon.getSpeed(), dataPokemon.getAbility(), dataPokemon.getType());
        stats = new Stats(baseStats, nature, dataPokemon.getLevelingRate(), dataPokemon.getExpYield());
        while (stats.getLevel() < lvl) {
            stats.increaseLevel();
        }
        learnMoves(checkNewMoves());

        // Load sprites
        if (isGUI) {
            System.out.printf("Loading sprites from %s%n", "src/main/resources/assets/Sprite/" + name + ".png");
            System.out.printf("Loading sprites from %s%n", "src/main/resources/assets/BackSprite/" + name + "_back.png");
            spriteFront = new Image("file:" + dataPokemon.getSpriteFrontPath(), 128, 128, true, false);
            spriteBack = new Image("file:" + dataPokemon.getSpriteBackPath(), 128, 128, true, false);
        }
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<DataType> getType() { return baseStats.getType(); }
    public BaseStats getBaseStats() { return baseStats; }
    public List<Move> getMoveList() { return this.moveList; }
    public Move getMove(int index) { return moveList.get(index); }
    public Move getMove(String moveName) { return moveList.stream().filter(move -> move.getName().equals(moveName)).findFirst().orElse(null); }
    public Nature getNature() { return this.nature; }
    public Stats getStats() { return stats; }
    public Image getSpriteFront() { return spriteFront; }
    public Image getSpriteBack() { return spriteBack; }

    // Game methods
    public void takeDamage(double damageTaken) { stats.decreaseCurrentHP(damageTaken); }
    public void heal(int healAmount) { stats.increaseCurrentHP(new PositiveInt(healAmount)); }

    public List<String> checkNewMoves() {
        PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

        List<String> newMoves = new ArrayList<>();

        for (DataMoveBasic dataMoveBasic : pl.getPokemonByName(name).getMoves()) {
            if (dataMoveBasic.getLvl() > stats.getLevel()) {
                break;
            } else {
                newMoves.add(dataMoveBasic.getName());
            }
        }
        return newMoves;
    }

    public void learnMove(String toLearn) {
        MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
        DataMove dataMove = ml.getMoveByName(toLearn);
        if (moveList.size() < 4 && moveList.stream().noneMatch(m -> m.getName().equals(dataMove.getName()))) {
            moveList.add(new Move(dataMove.getName(), dataMove.getType(), dataMove.getCategory(), dataMove.getPower(), dataMove.getAccuracy(), dataMove.getPriority(), dataMove.getPp()));
        }
    }

    public void learnMoves(List<String> toLearn) {
        for (String move : toLearn) {
            learnMove(move);
        }
    }

    public void learnAndForgetMove(String toLearn, String toForget) {
        learnMove(toLearn);
        moveList.removeIf(m -> m.getName().equals(toForget));
    }
}
