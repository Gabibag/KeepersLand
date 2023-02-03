package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class ItemEntity extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 20;
        this.name = "this shouldn't exist";
        this.battleHp = baseHp;
        this.coins = 3;
    }
    public void setBaseStats(int baseHp, int damage, int xp, String ItemName, int coins) {
        this.baseHp = baseHp;
        this.damage = damage;
        this.xp = xp;
        this.name = ItemName;
        this.battleHp = baseHp;
        this.coins = coins;
    }

    @Override
    public boolean canSpawn(Player p) {

        return false; //(r.nextInt([spawnchance]) == 2)

    }
}
