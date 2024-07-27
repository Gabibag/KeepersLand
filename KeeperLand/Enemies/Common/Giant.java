package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Giant extends Enemy {
    Random r = Main.r;

    public Giant() {
        super("Has no extra abilities, what you see is what you get!", true);
    }

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 4;
        this.xp = 20;
        this.name = "Giant";
        this.coins = 15;
    }


    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(4) == 2;

    }
}
