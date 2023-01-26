package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.LavaZone;
import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class Demon extends Enemy{
    Random r = new Random();
    public Demon() {
        super();
        this.baseHp = 10;
        this.damage = 10;
        this.xp = 20;
        this.name = "Demon";
        this.battleHp = baseHp;
        this.coins = 10;
        this.drops.add(Item.demonSword);
    }

    @Override
    public boolean canSpawn(Player p) {

        return (Main.currentPlace instanceof LavaZone) && (r.nextInt(5) == 2); //only spawns in lava Environments with a 20% chance

    }
}
