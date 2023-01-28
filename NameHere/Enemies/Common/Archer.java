package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Archer extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 7;
        this.xp = 4;
        this.name = "Archer";
        this.coins = 1;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(5) == 2&&(p.getStageNum() % 10 != 0); //(r.nextInt([spawnchance]) == 2)

    }
}
