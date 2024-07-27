package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.Enemies.WindyHeights.Ninja;
import KeeperLand.Enemies.WindyHeights.SuperChargedBird;
import KeeperLand.Enemies.WindyHeights.Vulture;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WindyHeights extends Environment {
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

    @Override
    public ArrayList<Enemy> allowedEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>(super.allowedEnemies());
        enemies.add(new Ninja());
        enemies.add(new SuperChargedBird());
        enemies.add(new Vulture());
        return enemies;
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
