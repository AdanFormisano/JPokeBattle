package com.example.jpokebattle.poke;

import com.example.jpokebattle.poke.move.AbstractMove;
import com.example.jpokebattle.poke.move.MoveFactory;
import com.example.jpokebattle.service.PositiveInt;
import com.example.jpokebattle.service.data.DataMove;
import com.example.jpokebattle.service.data.DataMoveBasic;
import com.example.jpokebattle.service.data.DataPokemon;
import com.example.jpokebattle.service.loader.MoveLoader;
import com.example.jpokebattle.service.loader.PokeLoader;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private final int id;
    private String name;
    private final BaseStats baseStats;
    private final Stats stats;
    private final Nature nature = new Nature();
    private List<AbstractMove> moveList = new ArrayList<>();
    public boolean isFainted = false;
    public Image spriteFront;
    public Image spriteBack;
    private int evoLevel;
    private String evoTo;

    public Pokemon (DataPokemon dataPokemon, boolean isGUI) {
        id = dataPokemon.getId();
        name = dataPokemon.getName();
        baseStats = new BaseStats(dataPokemon.getHp(), dataPokemon.getAttack(), dataPokemon.getDefense(), dataPokemon.getSpecialAttack(), dataPokemon.getSpecialDefense(), dataPokemon.getSpeed(), dataPokemon.getAbility(), dataPokemon.getType());
        stats = new Stats(baseStats, nature, dataPokemon.getLevelingRate(), dataPokemon.getExpYield());
        evoLevel = dataPokemon.getEvolution().level();
        evoTo = dataPokemon.getEvolution().to();

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
        stats.calculateAllStats();
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
    public List<Type> getType() { return baseStats.getType(); }
    public BaseStats getBaseStats() { return baseStats; }
    public List<AbstractMove> getMoveList() { return this.moveList; }
    public AbstractMove getMove(int index) { return moveList.get(index); }
    public AbstractMove getMove(String moveName) { return moveList.stream().filter(move -> move.getName().equals(moveName)).findFirst().orElse(null); }
    public Nature getNature() { return this.nature; }
    public Stats getStats() { return stats; }
    public Image getSpriteFront() { return spriteFront; }
    public Image getSpriteBack() { return spriteBack; }
    public int getEvoLevel() { return evoLevel; }
    public String getEvoTo() { return evoTo; }

    // Game methods
    public void takeDamage(double damageTaken) { stats.decreaseCurrentHP(damageTaken); }
    public void fullHP() { stats.setHPToMax();}

    public List<String> checkNewMoves() {
        PokeLoader pl = new PokeLoader("src/main/resources/com/example/jpokebattle/data/pokemons.json");

        List<String> newMoves = new ArrayList<>();
        List<String> knownMoves = new ArrayList<>();

        for (AbstractMove move : moveList) {
            knownMoves.add(move.getName());
        }

        for (DataMoveBasic dataMoveBasic : pl.getPokemonByName(name).getMoves()) {
            if (dataMoveBasic.getLvl() > stats.getLevel()) {
                break;
            } else {
                if (!knownMoves.contains(dataMoveBasic.getName())) {
                    newMoves.add(dataMoveBasic.getName());
                }
            }
        }
        return newMoves;
    }

    public void learnMove(String toLearn) {
        MoveLoader ml = new MoveLoader("src/main/resources/com/example/jpokebattle/data/moves.json");
        DataMove dataMove = ml.getMoveByName(toLearn);
        if (moveList.size() < 4 && moveList.stream().noneMatch(m -> m.getName().equals(dataMove.getName()))) {
            moveList.add(MoveFactory.getMove(dataMove.getName()));
        }
    }

    public List<String> learnMoves(List<String> toLearn) {
        List<String> remainingMoves = new ArrayList<>();

        for (AbstractMove move : moveList) {
            toLearn.remove(move.getName());
        }

        if (toLearn.size() + moveList.size() > 4) {
            List<String> learnableMoves = toLearn.subList(0, 4 - moveList.size());
            remainingMoves.addAll(toLearn.subList(4 - moveList.size(), toLearn.size()));
            for (String move : learnableMoves) {
                learnMove(move);
            }
        } else {
            for (String move : toLearn) {
                learnMove(move);
            }
        }
        return remainingMoves;
    }

    public void learnAndForgetMove(String toLearn, String toForget) {
        moveList.removeIf(m -> m.getName().equals(toForget));
        learnMove(toLearn);
    }

    public void evolve(DataPokemon evoData) {
        name = evoData.getName();
        baseStats.setType(evoData.getType());
//        baseStats.setAbility(evoData.getAbility());
        baseStats.setBaseHP(evoData.getHp());
        baseStats.setAttack(evoData.getAttack());
        baseStats.setDefense(evoData.getDefense());
        baseStats.setSpecialAttack(evoData.getSpecialAttack());
        baseStats.setSpecialDefense(evoData.getSpecialDefense());
        baseStats.setSpeed(evoData.getSpeed());
        evoLevel = evoData.getEvolution().level();
        evoTo = evoData.getEvolution().to();
        spriteFront = new Image("file:" + evoData.getSpriteFrontPath(), 128, 128, true, false);
        spriteBack = new Image("file:" + evoData.getSpriteBackPath(), 128, 128, true, false);

        // Refresh stats
        stats.calculateAllStats();
    }
}
