package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Random;

public class Goblin extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 7;
        this.xp = 5;
        this.name = "Goblin";
        this.coins = 3;
        this.drops.add(ItemData.giantSkin);
    }

    @Override
    public boolean canSpawn(Player p) {

        return true;

    }
}
