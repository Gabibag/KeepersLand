package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class SubSpace extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList();
    }

    public String getDescription() {
        return "Why is my health literally 0?";
    }

    public String getName() {
        return "Sub Space";

    }
    public int modifyPlayerDamage(int preChange) {
        return Math.min(preChange, 80);

    }

    public void playerAction(Player p) {
        if (p.getBattleHp()>100){
            System.out.println("The subspace environment limits your health. Your health " + ((p.getBattleHp()<250) ? "drops" : "plummets" ) + " to 100");
            Helper.contiuePrompt();
            p.setBattleHp(100);
        }
    }


    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        return Math.min(preChange, 80);
    }
}
