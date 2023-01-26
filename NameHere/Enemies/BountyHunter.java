package NameHere.Enemies;

import NameHere.Abstracts.Enemy;
import NameHere.Item;
import NameHere.Player;


public class BountyHunter extends Enemy{

    public BountyHunter() {
        super();
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Bounty Hunter";
        this.battleHp = baseHp;
        this.drops.add(Item.bountyHunterSword);
        this.drops.add(Item.woodenSword);
        this.coins = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }


}
