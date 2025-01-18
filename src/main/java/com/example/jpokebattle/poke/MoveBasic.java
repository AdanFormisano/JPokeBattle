package com.example.jpokebattle.poke;

public class MoveBasic {
    private int lvl;
    private String name;

    public MoveBasic() {}

    public int getLvl() { return this.lvl; }
    public String getName() { return this.name; }

    public void setLvl(int lvl) { this.lvl = lvl; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "MoveBasic{" +
                "lvl=" + lvl +
                ", name='" + name + '\'' +
                '}';
    }
}
