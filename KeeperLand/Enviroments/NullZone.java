package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Item;
import KeeperLand.Player;

import java.util.List;

public class NullZone extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return List.of();
    }

    public String getDescription() {
        return "Nothing is special about this area.";
    }

    public String getName() {
        return "Null Zone";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
