package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.*;
import KeeperLand.Enemies.Toxic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToxicEnv extends Environment {
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
        int dmgInc = Main.r.nextInt(3) * Main.player.getStageNum() / 2;
        if (dmgInc == 0) {
            return preChange;
        }

        return preChange + dmgInc;

    }

    public void playerAction(Player p, List<Enemy> enemies) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    @Override
    public ArrayList<Enemy> allowedEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>(super.allowedEnemies());
        enemies.add(new Assassin());
        enemies.add(new Basilisk());
        enemies.add(new Slime());
        enemies.add(new Snake());
        enemies.add(new SwampMonster());
        return enemies;
    }

    public int modifyEnemyDamage(int preChange) {
        int dmgInc = Main.r.nextInt(3 * Main.player.getStageNum() / 2);
        if ((dmgInc == 0) || (preChange == 0)) {
            return preChange;
        }
        System.out.println(Colors.RED + "The toxic air increases the enemies' damage to " + (preChange + dmgInc) + "!" + Colors.RESET + "(" + preChange + " -> " + (preChange + dmgInc) + ")");
        return preChange + dmgInc;
    }
}
