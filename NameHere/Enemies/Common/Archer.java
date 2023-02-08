package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class Archer extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 10;
        this.xp = 4;
        this.name = "Archer";
        this.coins = 1;

    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextFloat() > 70; //(r.nextInt([spawnchance]) == 2)

    }
}
