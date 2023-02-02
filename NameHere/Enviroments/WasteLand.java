package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class WasteLand extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.GhostSpirit,
                             ItemData.doransBlade,
                             ItemData.bloodStone);
    }

    public String getDescription() {
        return "";
    }

    public String getName() {
        return "Waste Land";

    }

    public int modifyPlayerDamage(int preChange) {
        System.out.println("The Waste Land has made you weaker");

        return preChange - (int)(preChange*(0.2));

    }

    public void playerAction(Player p) {
        System.out.println("You have been poisoned by the Waste Land");
        p.setBattleHp((int)(p.getBattleHp() - (p.getBattleHp()*0.2)));
    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        if (preChange%3 == 2){
            System.out.println("The Waste Land has made the enemy weaker");
            return preChange - (int)(preChange*(0.2));
        }
        return preChange;
    }
}
