package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;


public class BountyHunter extends Enemy {

    public BountyHunter() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Bounty Hunter";
        this.drops.add(ItemData.bountyHunterSword);
        this.drops.add(ItemData.woodenSword);
        this.coins = 2;
    }


}
