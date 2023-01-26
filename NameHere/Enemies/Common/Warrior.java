package NameHere.Enemies.Common;

import NameHere.Abstracts.Enemy;
import NameHere.Item;
import NameHere.Player;

import java.util.Random;
public class Warrior extends Enemy{
    Random r = new Random();
    public Warrior() {
        super();
        this.baseHp = 15;
        this.damage = 5;
        this.xp = 20;
        this.name = "Warrior";
        this.battleHp = baseHp;
        this.drops.add(Item.warriorSword);
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextBoolean()||r.nextBoolean()); //25% chance of not spawning, kinda rare as it deals quite a bit damage

    }
}
