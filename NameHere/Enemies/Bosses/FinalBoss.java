package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;

import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class FinalBoss extends Boss {
    public void setBaseStats() {
        this.baseHp = 75;
        this.damage = 10;
        this.xp = 120;
        this.name = "FinalBoss";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return (p.getStageNum() % 10 != 0) && (Main.r.nextBoolean() || Main.r.nextBoolean()); //75% spawn chance
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {

    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {

    }
}
