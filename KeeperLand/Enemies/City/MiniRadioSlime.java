package KeeperLand.Enemies.City;

import KeeperLand.Player;
import KeeperLand.Abstracts.Enemy;

public class MiniRadioSlime extends Enemy {

    @Override
    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 2;
        this.coins = 0;
        this.xp = 0;
        this.name = "Mini Radioactive Slime";
        
    }

    @Override
    public boolean canSpawn(Player p) {
        return false;
    }
    
}
