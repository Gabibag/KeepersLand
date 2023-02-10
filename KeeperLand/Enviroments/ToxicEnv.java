package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Colors;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class ToxicEnv extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.toxicFang,
                             ItemData.doransBlade,
                             ItemData.swampPot);
    }

    public String getDescription() {
        return "A toxic swamp that makes attack do more damage.";
    }

    public String getName() {
        return "Toxic Swamp";
    }

    public int modifyPlayerDamage(int preChange) {
        int dmgInc = Main.r.nextInt(3);
        if (dmgInc == 0) {
            return preChange;
        }
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + dmgInc) + "!" + Colors.RESET);
        return preChange + dmgInc;

    }

    public void playerAction(Player p){

    }
    public void turnEnd(Player p, List<Enemy> enemies){

    }

    public int modifyEnemyDamage(int preChange) {
        int dmgInc = Main.r.nextInt(3);
        if ((dmgInc == 0) || (preChange == 0)) {
            return preChange;
        }
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + dmgInc) + "!" + Colors.RESET);
        return preChange + dmgInc;
    }
}
