package KeeperLand.Abstracts;

import KeeperLand.Item;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public abstract class Environment {
    public Environment() {
        if (!Main.allPlaces.contains(this) && !this.getName().equalsIgnoreCase("starter land") /*Hard coding cuz im stupid*/) {
            Main.allPlaces.add((this));
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Environment e = (Environment) obj;
            return e.getName().equals(this.getName());
        } catch (Exception e) {
            return super.equals(obj);
        }
        //return super.equals(obj);
    }

    public void BattleStart(Player p, List<Enemy> allies) {

    }

    public abstract String getDescription();

    public abstract List<Item> getShopItems();

    public boolean isValid(Player p) {
        return true;
    }

    public abstract String getName();

    /**
     * Called after the player completes an action
     *
     * @param p       the player
     * @param enemies enemy
     */
    public abstract void playerAction(Player p, List<Enemy> enemies);//done

    /**
     * called after the player has used all their actions for a turn
     *
     * @param p the player
     */
    public abstract void turnEnd(Player p, List<Enemy> enemies);

    /**
     * alllows changing the players damage to an enemy
     *
     * @param preChange the damage before being changed
     * @return the new damage amount
     */
    public abstract int modifyPlayerDamage(int preChange);

    /**
     * Change the damage dealt by an enemy to the player
     *
     * @param preChange damage before being changed
     * @return the new damage to be delt
     */
    public abstract int modifyEnemyDamage(int preChange);
}
