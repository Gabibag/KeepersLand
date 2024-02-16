package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.ToxicEnv;
import KeeperLand.Main;
import KeeperLand.Player;

public class Basilisk extends Enemy {
    public Basilisk() {
        super("A basic monster, what you see is what you get!");
    }

    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 4;
        this.xp = 10;
        this.name = "Basilisk";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof ToxicEnv; //(r.nextInt([spawnchance]) == 2)

    }
}
