package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;

import NameHere.Player;

import java.util.List;

public class DemonLord extends Boss {
    public void setBaseStats() {
        this.baseHp = 100;
        this.damage = 20;
        this.xp = 100;
        this.name = "Demon Lord";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return (p.getStageNum() % 10 == 0);
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {


    }

    @Override
    public void bossOnSpawn(List<Enemy> allies) {
    }
}
