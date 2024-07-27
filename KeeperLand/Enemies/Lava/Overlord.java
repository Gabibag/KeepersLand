package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

public class Overlord extends Enemy {

    public Overlord() {
        super("Run.", false);
    }

    public void setBaseStats() {
        this.baseHp = Integer.MAX_VALUE / 10000;
        this.damage = Integer.MAX_VALUE / 10000;
        this.xp = 1000;
        this.name = "Overlord";
        this.coins = 1000;

    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(100) == 2;

    }
}
