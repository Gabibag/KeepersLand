package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class GateKeepers extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 0;
        this.xp = 20;
        this.name = "GateKeepers";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }


}
