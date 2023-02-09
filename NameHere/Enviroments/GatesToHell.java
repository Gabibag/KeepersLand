package NameHere.Enviroments;

import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;

import java.util.Arrays;
import java.util.List;

public class GatesToHell extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.barFromHell,
                             ItemData.doransBlade,
                             ItemData.bloodStone);
    }

    public String getDescription() {
        return "It hurts to move. For you. Not the enemies.";
    }

    public String getName() {
        return "Gates To Hell";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p) {
        //remove 2% of player hp

        p.setBattleHp(p.getBattleHp() - (int) (p.getBattleHp() * 0.02 < 1 ? 1 : p.getBattleHp() * 0.02));
        System.out.println(Colors.CLEAR +  "The gates to hell hurt you for " + (int)(p.getBattleHp() * 0.02 < 1 ? 1 : p.getBattleHp() * 0.02) + " damage!");
        Helper.contiuePrompt();
    }

    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
