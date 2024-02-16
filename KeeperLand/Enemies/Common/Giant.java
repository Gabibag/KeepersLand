package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Giant extends Enemy {
    Random r = Main.r;

    public Giant() {
        super("A basic monster, what you see is what you get!");
    }

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 4;
        this.xp = 20;
        this.name = "Giant";
        this.coins = 15;
    }


    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(20) == 2; // 0.05% chance of spawning

    }
}
