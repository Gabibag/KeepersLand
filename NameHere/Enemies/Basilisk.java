package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Basilisk extends Enemy {
    Random r = new Random();

    public Basilisk() {
        super();
        this.baseHp = 30;
        this.damage = 4;
        this.xp = 10;
        this.name = "Basilisk";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return true; //(r.nextInt([spawnchance]) == 2)

    }
}
