package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class CrazyChicken extends Enemy {
    Random r = new Random();

    public CrazyChicken() {
        super("Has no extra abilities, what you see is what you get!", true);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 5;
        this.xp = 5;
        this.name = "Crazy Chicken";
        this.coins = 1;
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(10) == 0;

    }
}
