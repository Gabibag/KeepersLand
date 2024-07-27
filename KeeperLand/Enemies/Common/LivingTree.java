package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Mutations.Defensive;

import java.util.Random;

public class LivingTree extends Enemy {
    final Random r = new Random();

    public LivingTree() {
        super("Has no extra abilities, what you see is what you get!", true);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 5;
        this.xp = 1;
        this.name = "Living Tree";
        if (r.nextBoolean()) this.mutate = new Defensive();
        this.coins = 1;
    }

    @Override
    public boolean canSpawn() {

        return true;

    }

}
