package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.Colors;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class LavaZone extends Environment {
    int turnsTillLava = 4;

    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.LavaVial, ItemData.TougherTimes, ItemData.MoltenGem);
    }

    public String getName() {
        return "Lava Zone";
    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        turnsTillLava--;

        if (turnsTillLava == 0) {
            System.out.println("The volcano erupts, and everyone takes " + p.getBattleHp() / 2 + " damage!");
            for (Enemy e : enemies) {
                e.setBattleHp(p.getBattleHp() / 2 + e.getBattleHp());
            }
            p.setBattleHp(p.getBattleHp() / 2 + p.getBattleHp());
            turnsTillLava = 3;
            return;
        }
        System.out.println(Colors.YELLOW + "The volcano will erupt in " + turnsTillLava + " turns" + Colors.RESET);
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    public String getDescription() {
        return "A large volcano surrounded by pools of lava and mountains of ash.";
    }

}
