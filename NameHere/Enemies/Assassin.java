package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class Assassin extends Enemy {
    Random r = new Random();

    public Assassin() {
        super();
        this.baseHp = 1;
        this.damage = 10;
        this.xp = 10;
        this.name = "Assassin";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 15;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(10)==2; //10% spawn chance

    }
}
