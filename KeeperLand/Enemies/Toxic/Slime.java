package KeeperLand.Enemies.Toxic;

import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.ToxicEnv;

public class Slime extends Enemy {

public void setBaseStats(){
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
        this.battleHp = baseHp;
        this.drops.add(ItemData.slimeShield);
        this.coins = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof ToxicEnv;
    }
}
