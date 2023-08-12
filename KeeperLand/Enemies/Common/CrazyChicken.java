package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class CrazyChicken extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 5;
        this.xp = 5;
        this.name = "Crazy Chicken";
        this.coins = 1;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(10) == 0;

    }
}
