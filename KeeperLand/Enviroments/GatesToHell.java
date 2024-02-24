package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.Enemies.GatesToHell.GateKeepers;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class GatesToHell extends Environment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.barFromHell,
                ItemData.doransBlade,
                ItemData.bloodStone);
    }

    public String getDescription() {
        return "It hurts to move. For you. Not the enemies.";
    }

    public String getName() {
        return "Gates To Hell";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p, List<Enemy> enemies) {
        //remove 2% of player hp

        p.setBattleHp(p.getBattleHp() - (int) (p.getBattleHp() * 0.02 < 1 ? 1 : p.getBattleHp() * 0.02));
        for (Enemy enemy : enemies) {
            if (enemy instanceof GateKeepers) {
                p.setBattleHp(p.getBattleHp() - (int) (p.getBattleHp() * 0.2 < 1 ? 1 : p.getBattleHp() * 0.2));
            }
        }

        System.out.println("The gates to hell hurt you for " + (int) (p.getBattleHp() * 0.02 < 1 ? 1 : p.getBattleHp() * 0.02) + " damage!");
    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
