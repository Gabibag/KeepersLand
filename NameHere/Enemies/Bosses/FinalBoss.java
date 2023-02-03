package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.List;

public class FinalBoss extends Boss {
    public void setBaseStats() {
        this.baseHp = 7500;
        this.damage = 500;
        this.xp = 12000;
        this.name = "Keeper";
        this.coins = 5000;
        this.tokens = 100;

    }

    @Override
    public boolean canSpawn(Player p) {
        return false; //75% spawn chance
    }

    @Override
    public void scaleStats() {
        //do nothing
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {

    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {

    }
}
