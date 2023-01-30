package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Colors;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;

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
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + dmgInc) + "!");
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
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + dmgInc) + "!");
        return preChange + dmgInc;
    }
}
