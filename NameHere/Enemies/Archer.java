package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Archer extends Enemy {
    Random r = new Random();

    public Archer() {
        super();
        this.baseHp = 5;
        this.damage = 7;
        this.xp = 4;
        this.name = "Archer";
        this.battleHp = baseHp;
        this.coins = 1;
    }

    @Override
    public boolean canSpawn(Player p) {

        return true; //(r.nextInt([spawnchance]) == 2)

    }
}