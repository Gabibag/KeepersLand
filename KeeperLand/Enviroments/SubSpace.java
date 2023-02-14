package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Helper;
import KeeperLand.Item;
import KeeperLand.Player;

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

    public void playerAction(Player p, List<Enemy> enemies) {
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
