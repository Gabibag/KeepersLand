package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Spirit;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class DoubleSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Double Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1,10)==2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //make all allies but itself attack again
        System.out.println("The Double Spirit makes all " + Colors.RED + "allies attack again"+ Colors.RESET);
        for (Enemy e : allies) {
            if (!(e instanceof DoubleSprite)) {
                int i = e.Attack(p, allies);
                p.takeDamage(i);
            }
        }
        return 0;
    }
}
