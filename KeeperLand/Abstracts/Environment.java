package KeeperLand.Abstracts;

import KeeperLand.Item;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Environment {
    protected ArrayList<Enemy> allowedEnemies = new ArrayList<>();

    public Environment() {
        if (!Main.allPlaces.contains(this) && !this.getName().equalsIgnoreCase("starter land") /*Hard coding cuz im stupid*/) {
            Main.allPlaces.add((this));
        }
    }

    /**
     * Overrides the equals method to instead compare the names of the environments
     *
     * @param obj the environment to compare to
     * @return true if the names are the same, false otherwise
     */
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

    /**
     * An effect that occurs BEFORE the battle even begins, meaning no enemies are present. Currently only used to
     * display an intro message to the player in StarterLand
     *
     * @param p      the player
     * @param allies the enemies
     */
    public void BattleStart(Player p, List<Enemy> allies) {

    }

    /**
     * Returns a description of the environment
     *
     * @return a string description of the environment
     */
    public abstract String getDescription();

    /**
     * Returns a list of items that can be bought in the shop
     *
     * @return a list of items
     */
    public abstract List<Item> getShopItems();

    /**
     * Returns a list of enemies that can be fought in the environment. By default, returns those in the common enemies list
     *
     * @return an ArrayList of enemies
     */
    public ArrayList<Enemy> allowedEnemies() {
        return new ArrayList<>(Main.commonEnemies);
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
