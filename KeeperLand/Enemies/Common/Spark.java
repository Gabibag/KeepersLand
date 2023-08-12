package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Spark extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 2;
        this.damage = 24;
        this.xp = 1;
        this.name = "Spark";
        this.coins = 1;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(8) == 0;

    }
}
