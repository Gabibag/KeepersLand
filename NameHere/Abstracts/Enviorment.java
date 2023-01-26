package NameHere.Abstracts;
import java.util.*;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;
public abstract class Enviorment {
    public Enviorment(){
        Main.allPlaces.add((this));
    }
    public abstract List<Item> getShopItems();
    //TODO status effects
    public boolean isVaild(Player p){
        return true;
    }
    public abstract String getName();
    /**
     * Called after the player completes an action 
     * @param p the player
     */
    public abstract void playerAction(Player p);//done
    /**
     * called after the player has used all their actions for a turn
     * @param p the player
     */
    public abstract void turnEnd(Player p);
    /**
     * alllows changing the players damage to an enemy
     * @param preChange the damage before being changed
     * @return the new damage amount
     */
    public abstract int modifyPlayerDamage(int preChange);
    /**
     * Change the damage dealt by an enemy to the player
     * @param preChange damage before being changed
     * @return the new damage to be delt
     */
    public abstract int modifyEnemyDamage(int preChange);
}
