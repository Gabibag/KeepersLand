package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Ghost extends Enemy {
    Random r = new Random();

    public Ghost()  {//TODO: Add a higher miss rate
        super();
        this.baseHp = 10;
        this.damage = 4;
        this.xp = 10;
        this.name = "Ghost";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 10;
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextInt(10) == 2); //10% spawn chance

    }
}
