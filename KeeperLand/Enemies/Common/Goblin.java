package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

import java.util.Random;

public class Goblin extends Enemy {
    Random r = Main.r;

    public Goblin() {
        super("Has no extra abilities, what you see is what you get!", true);
    }

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 7;
        this.xp = 5;
        this.name = "Goblin";
        this.coins = 3;
        this.drops.add(ItemData.giantSkin);
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(3) == 2;

    }
}
