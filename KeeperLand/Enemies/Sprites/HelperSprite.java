package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Spirit;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class HelperSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Helper Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove 1 health from each item in the list allies, if it is the only element in the list, deal 100000 damage to the player
        if (allies.size() == 1) {
            p.setBattleHp(p.getBattleHp() - 100000);
            System.out.println("The Helper Spirit deals " + Colors.RED + "100000 damage" + Colors.RESET + " to the player");
        } else {
            for (Enemy target : allies) {
                if (allies != this) {
                    target.setBattleHp(target.getBattleHp() - 1);
                }
            }
            System.out.println("The Helper Spirit removes " + Colors.RED + "1 health" + Colors.RESET + " from each enemy in the party");
        }

        return 0;
    }
}
