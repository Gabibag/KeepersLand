package KeeperLand.Enviroments;

import KeeperLand.*;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;

import java.util.Arrays;
import java.util.List;

public class WasteLand extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.GhostSpirit,
                ItemData.doransBlade,
                ItemData.toxicWaste);
    }

    public String getDescription() {
        return "Used to be a forest. Now its this.";
    }

    public String getName() {
        return "Waste Land";

    }

    public int modifyPlayerDamage(int preChange) {


        return preChange - (int) (preChange * (0.2));

    }

    public void playerAction(Player p, List<Enemy> enemies) {

        System.out.println(Colors.YELLOW + "You have been poisoned by the Waste Land " + Colors.RESET + "(⬇ " + ((int) (p.getBattleHp() * 0.2)) + ")");
        p.setBattleHp((int) (p.getBattleHp() - (p.getBattleHp() * 0.2)));
    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        if (preChange % 3 == 2) {
            System.out.println(Colors.RED + "Enemy Damage Decreased!" + Colors.RESET + "(" + preChange + " -> " + (int) (preChange * (0.8)) + ")");
            return preChange - (int) (preChange * (0.2));
        }
        return preChange;
    }
}
