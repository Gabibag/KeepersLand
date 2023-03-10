package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class CaveDweller extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 2;
        this.xp = 20;
        this.name = "Cave Dweller";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }

}
