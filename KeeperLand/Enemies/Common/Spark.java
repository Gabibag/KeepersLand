package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Spark extends Enemy {
    Random r = new Random();

    public Spark() {
        super("Has no extra abilities, what you see is what you get!", true);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 2;
        this.damage = 24;
        this.xp = 1;
        this.name = "Spark";
        this.coins = 1;
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(4) == 0;

    }
}
