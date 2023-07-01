package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Ghost extends Enemy {
    Random r = Main.r;

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 4;
        this.xp = 10;
        this.name = "Ghost";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 10;
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextInt(10) == 2) && Main.currentPlace instanceof Graveyard; //10% spawn chance

    }
}
