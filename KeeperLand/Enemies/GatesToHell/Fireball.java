package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class Fireball extends Enemy {

    int numTurns = 1;

    public void setBaseStats() {
        this.baseHp = 2;
        this.coins = 0;
        this.damage = 20;
        this.name = "Fireball";
        this.xp = 1;
    }

    public int Attack(Player p, List<Enemy> allies) {
        if (numTurns == 0) {
            System.out.println("The fireball " + Colors.RED + " explodes" + Colors.RESET);
            this.battleHp = 0;
            return this.damage;
        } else {
            System.out.println("The fireball fly's towards you.");
            numTurns--;
            return 0;
        }
    }

    @Override
    public boolean canSpawn(Player p) {
        return false;
    }

}
