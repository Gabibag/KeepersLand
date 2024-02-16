package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Ghost extends Enemy {
    final Random r = Main.r;

    public Ghost() {
        super("A basic monster, what you see is what you get!");
    }

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
