package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class WindyHeights extends Enviorment {
    private int turn = 0;

    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Rock, ItemData.StoneShield, ItemData.StoneSword);
    }

    public String getDescription() {
        return "The heights some how make it easier to move?";
    }

    public String getName() {
        return "Windy Heights";
    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //increase the dodge rate of the enemy by 1 every other turn
        if (turn % 4 == 0) {
            for (Enemy e : enemies) {
                e.setDodgeRate(e.getDodgeRate() + 1);
            }
        }
        turn++;
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
