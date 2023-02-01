package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class StardewValley extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList();
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

    public void playerAction(Player p) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //make everything in the list of enemies attack each other
        for (Enemy e : enemies) {
            for (Enemy e2 : enemies) {
                if (e != e2) {
                    e.setBattleHp(e.getBattleHp() - e2.getDamage());
                }
            }
        }
        System.out.println("Stardew Valley made all enemies attack each other");
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
