package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.LavaZone;
import KeeperLand.Enviroments.StarterLand;
import KeeperLand.Main;
import KeeperLand.Player;

public class Overlord extends Enemy {

    public Overlord() {
        super("Run.");
    }

    public void setBaseStats() {
        this.baseHp = Integer.MAX_VALUE / 10000;
        this.damage = Integer.MAX_VALUE / 10000;
        this.xp = 1000;
        this.name = "Overlord";
        this.coins = 1000;

    }

    @Override
    public boolean canSpawn(Player p) {

        return !(Main.currentPlace instanceof StarterLand) && (Main.r.nextInt(50) == 2 && Main.currentPlace instanceof LavaZone); //(r.nextInt([spawnchance]) == 2)

    }
}
