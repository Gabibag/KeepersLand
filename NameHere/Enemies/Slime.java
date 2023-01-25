package NameHere.Enemies;

import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Enemy;

public class Slime extends Enemy{

    public Slime() {
        super();
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
        this.battleHp = baseHp;
        this.drops.add(Item.slimeShield);
        this.dropRate = 10;
        this.coins = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
