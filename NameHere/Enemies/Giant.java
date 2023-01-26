package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Giant extends Enemy{
    Random r = new Random();
    public Giant() {
        super();
        this.baseHp = 60;
        this.damage = 1;
        this.xp = 20;
        this.name = "Giant";
        this.battleHp = baseHp;
        this.coins = 8;
    }


    @Override
    public boolean canSpawn(Player p) {

        return (r.nextInt(5) == 2); //20% chance of spawning

    }
}