package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class DoubleSprite extends Sprite {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Double Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //make all allies but itself attack again
        System.out.println("The Double Sprite makes all " + Colors.RED + "allies attack again" + Colors.RESET);
        for (int i = 0; i < allies.size(); i++) {
            Enemy e = allies.get(i);
            if (!(e instanceof DoubleSprite)) {
                int j = e.Attack(p, allies);
                p.takeDamage(j);
            }
        }
        return 0;
    }
}
