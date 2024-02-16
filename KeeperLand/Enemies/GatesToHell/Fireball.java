package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class Fireball extends Enemy {

    int numTurns = 1;

    public Fireball() {
        super("Summoned by a monster, explodes on impact after flying towards you, damaging you and killing itself.");
    }

    public void setBaseStats() {
        this.baseHp = 2;
        this.coins = 0;
        this.damage = 20;
        this.name = "Fireball";
        this.xp = 1;
        this.dodgeRate = 4;
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

    @Override
    public String getDodgeText() {
        return "You miss the fireball.";
    }
}
