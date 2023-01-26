package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Overlord extends Enemy {
    Random r = new Random();

    public Overlord() {
        super();
        this.baseHp = 100;
        this.damage = 20;
        this.xp = 100;
        this.name = "Overlord";
        this.battleHp = baseHp;
        this.coins = 100;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(100)==2; //(r.nextInt([spawnchance]) == 2)

    }
}
