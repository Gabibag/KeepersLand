package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.ToxicEnv;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Assassin extends Enemy {
    Random r = new Random();

    public void setBaseStats() {
        this.baseHp = 1;
        this.damage = 10;
        this.xp = 10;
        this.name = "Assassin";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 10;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(10) == 2 && Main.currentPlace instanceof ToxicEnv; //10% spawn chance

    }
}
