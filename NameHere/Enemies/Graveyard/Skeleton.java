package NameHere.Enemies.Graveyard;

import NameHere.Abstracts.Enemy;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Random;

public class Skeleton extends Enemy {
    Random r = new Random();

    public Skeleton() {
        super();
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

        return (r.nextBoolean()); //(r.nextInt([spawnchance]) == 2)

    }
}
