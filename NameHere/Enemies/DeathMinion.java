package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Item;
import NameHere.Player;

import java.util.Random;

public class DeathMinion extends Enemy {
    Random r = new Random();

    public DeathMinion() {
        super();
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 10;
        this.name = "Death's Minion";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(Item.soul);
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextInt(10) == 2); //(r.nextInt([spawnchance]) == 2)

    }
}