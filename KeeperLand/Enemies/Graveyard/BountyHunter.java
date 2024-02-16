package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;


public class BountyHunter extends Enemy {

    public BountyHunter() {
        super("A basic monster, what you see is what you get!");
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

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof Graveyard;
    }


}
