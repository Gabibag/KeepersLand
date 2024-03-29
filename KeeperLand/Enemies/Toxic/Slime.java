package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.ToxicEnv;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

public class Slime extends Enemy {

    public Slime() {
        super("A basic monster, what you see is what you get!");
    }

    public void setBaseStats() {
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
