package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

import java.util.Random;

public class Warrior extends Enemy {
    Random r = Main.r;

    public Warrior() {
        super("Has no extra abilities, what you see is what you get!", true);
    }

    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 5;
        this.xp = 20;
        this.name = "Warrior";
        this.drops.add(ItemData.warriorSword);
        this.coins = 3;
    }

    @Override
    public boolean canSpawn() {

        return true;

    }
}
