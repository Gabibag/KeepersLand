package KeeperLand.Enemies.Graveyard;

import KeeperLand.Player;
import KeeperLand.Abstracts.Enemy;

public class SkeletonHand extends Enemy {
    public void setBaseStats(){
        this.baseHp = 1;
        this.battleHp = this.baseHp;
        this.damage = 10;
        this.coins = 1;
        this.xp = 1;
        this.dodgeRate = 1;
        this.name = "Severed Skeleton Hand";
        
    }
    public boolean canSpawn(Player p){return false;}
    
}
