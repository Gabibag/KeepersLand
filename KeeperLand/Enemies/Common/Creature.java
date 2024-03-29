package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Creature extends Enemy {
    Random r = new Random();

    public Creature() {
        super("A basic monster, what you see is what you get!");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 8;
        this.xp = 20;
        this.name = "Creature";
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(4) == 0;

    }
}
