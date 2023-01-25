package NameHere.Enemies;

import NameHere.Player;
import NameHere.Abstracts.Enemy;

public class Slime extends Enemy{

    public Slime() {
        super();
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
