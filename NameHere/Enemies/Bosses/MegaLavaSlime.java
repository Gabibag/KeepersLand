package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Enemies.Lava.LavaSlime;
import NameHere.Player;

import java.util.List;

public class MegaLavaSlime extends Boss{
    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 3;
        this.xp = 100;
        this.name = "Mega Lava Slime";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
//        return true;
        return false;
    }
    @Override
    public void onDeath(Player p, List<Enemy> allies){
        System.out.println("The Mega Lava Slime splits into 3 Lava Slimes");
        for (int i = 0; i < 3; i++) {
            allies.add(new LavaSlime());
        }
    }
    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        
    }
}
