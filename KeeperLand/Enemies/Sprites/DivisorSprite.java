package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class DivisorSprite extends Sprite {
    public DivisorSprite() {
        super("Halves your health. Good luck.");
    }

    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.damage = 1;
        this.name = "Divisor Sprite";
    }

    public boolean canSpawn() {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //return the player's battle hp divided by two
        System.out.println("The Divisor Sprite " + Colors.RED + "divides" + Colors.RESET + " the player's hp by two");
        return p.getBattleHp() / 2;
    }
}
