package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Snake extends Enemy {
    public Snake() {
        super("Deals a percentage of damage to you instead of a solid number.", false);
    }

    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 8;
        this.xp = 10;
        this.name = "Snake";
        this.coins = 3;
    }

    public int Attack(Player p, List<Enemy> allies) {
        int dmg = (int) (p.getHp() * (8f / 100));
        System.out.println("The snake " + Colors.RED + "bites " + Colors.RESET + "the player for 8% of their hp.");
        return dmg;
    }

    @Override
    public int getDamage() {
        if (Main.player == null) {
            return damage;
        }
        return (int) (Main.player.getHp() * (8f / 100));
    }


}
