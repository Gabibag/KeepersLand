package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Giant extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 2;
        this.xp = 20;
        this.name = "Giant";
        this.coins = 15;
    }


    @Override
    public boolean canSpawn(Player p) {

        return true; //20% chance of spawning

    }
}
