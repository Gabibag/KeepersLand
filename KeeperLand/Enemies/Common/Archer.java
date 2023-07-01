package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Archer extends Enemy {
    Random r = Main.r;

    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 10;
        this.xp = 4;
        this.name = "Archer";
        this.coins = 1;

    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(10) == 2; //(r.nextInt([spawnchance]) == 2)

    }
}
