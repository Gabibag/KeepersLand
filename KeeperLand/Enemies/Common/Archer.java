package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Archer extends Enemy {
    Random r = Main.r;

    public Archer() {
        super("Has no extra abilities, what you see is what you get!", true);
    }

    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 10;
        this.xp = 4;
        this.name = "Archer";
        this.coins = 1;

    }

    @Override
    public boolean canSpawn() {
        return true; //(r.nextInt([spawnchance]) == 2)
    }

}
