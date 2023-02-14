package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class StardewValley extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.DullSkull,
                             ItemData.GlowingSkull,
                             ItemData.starDust);
    }

    public String getDescription() {
        return "What? You need a description for this?";
    }

    public String getName() {
        return "Stardew Valley";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //make the enemies attack each other
        if (Main.r.nextInt(4) == 1) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp() - 1);
            }


            System.out.println("Stardew Valley made all enemies attack themselves");
        }
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
