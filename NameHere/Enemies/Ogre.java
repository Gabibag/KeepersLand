package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Ogre extends Enemy {
    Random r = new Random();

    public Ogre() {
        super();
        this.baseHp = 40;
        this.damage = 2;
        this.xp = 10;
        this.name = "Ogre";
        this.battleHp = baseHp;
        this.coins = 5;
    }

    @Override
    public boolean canSpawn(Player p) {

        return true; //(r.nextInt([spawnchance]) == 2)

    }
}
