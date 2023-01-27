package NameHere.Enemies.Graveyard;

import NameHere.Abstracts.Enemy;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Random;

public class Skeleton extends Enemy {
    Random r = new Random();
public void setBaseStats(){
        this.baseHp = 30;
        this.damage = 2;
        this.xp = 10;
        this.name = "Skeleton";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(ItemData.skeletonBone);
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextBoolean()); 

    }
}
