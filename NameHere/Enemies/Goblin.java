package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Item;
import NameHere.Player;

import java.util.Random;

public class Goblin extends Enemy {
    Random r = new Random();

    public Goblin() {
        super();
        this.baseHp = 10;
        this.damage = 15;
        this.xp = 5;
        this.name = "Goblin";
        this.battleHp = baseHp;
        this.coins = 3;
        this.drops.add(Item.giantSkin);
    }

    @Override
    public boolean canSpawn(Player p) {

        return true; //(r.nextInt([spawnchance]) == 2)

    }
}
