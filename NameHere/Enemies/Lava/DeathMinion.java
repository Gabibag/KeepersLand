package NameHere.Enemies.Lava;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.LavaZone;
import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class DeathMinion extends Enemy {
    Random r = new Random();
public void setBaseStats(){
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 10;
        this.name = "Death's Minion";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(ItemData.soul);
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextInt(10) == 2) && Main.currentPlace instanceof LavaZone; //(r.nextInt([spawnchance]) == 2)

    }
}
