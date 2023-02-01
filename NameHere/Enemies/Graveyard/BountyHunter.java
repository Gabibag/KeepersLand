package NameHere.Enemies.Graveyard;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.Graveyard;
import NameHere.*;


public class BountyHunter extends Enemy {

    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Bounty Hunter";
        this.drops.add(ItemData.bountyHunterSword);
        this.drops.add(ItemData.woodenSword);
        this.coins = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof Graveyard;
    }


}