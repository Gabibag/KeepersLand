package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class CherryBlossoms extends Environment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Petal, ItemData.CherryBlossom, ItemData.CherryBark);
    }

    public String getDescription() {
        return "A peaceful land. Everything heals after every turn";
    }

    public String getName() {
        return "Cherry Blossoms";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //heal players and enemies for 10% of its current hp
        p.setBattleHp(p.getBattleHp() + (int) (p.getBattleHp() * 0.05) + 1);
        for (Enemy e : enemies) {
            e.setBattleHp(e.getBattleHp() + (int) (e.getBattleHp() * 0.05) + 1);
        }
        System.out.println("The cherry blossoms healed everyone for 5% of their current hp");
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
