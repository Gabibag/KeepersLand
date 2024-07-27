package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

import java.util.Random;

public class Demon extends Enemy {
    Random r = Main.r;

    public Demon() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 10;
        this.xp = 20;
        this.name = "Demon";
        this.battleHp = baseHp;
        this.coins = 10;
        this.drops.add(ItemData.demonSword);
    }


}
