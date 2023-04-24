package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Giant extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 4;
        this.xp = 20;
        this.name = "Giant";
        this.coins = 15;
    }


    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(40) == 2; //0.025% chance of spawning

    }
}
