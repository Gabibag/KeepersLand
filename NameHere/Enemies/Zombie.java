package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Zombie extends Enemy {
    Random r = new Random();

    public Zombie() {
        super();
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 10;
        this.name = "Zombie";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(4)==2; //(r.nextInt([spawnchance]) == 2)

    }
}
