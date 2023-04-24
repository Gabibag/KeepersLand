package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.Helper;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Arrays;
import java.util.List;

public class SubSpace extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.starDust, ItemData.SubspaceOrb);
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
        int min = p.getBattleHp() * (p.getStageNum() / 5);
        if (min <= 100){
            min = 100;
        }
        if (p.getBattleHp() > min) {
            System.out.println("The subspace environment limits your health. Your health " + (min > 0.5 ? "drops" : "plummets") + " to " + min);
            Helper.continuePrompt();
            p.setBattleHp(p.getBattleHp() * (p.getStageNum() / 5));
        }
    }


    public void turnEnd(Player p, List<Enemy> enemies) {

    }

    public int modifyEnemyDamage(int preChange) {
        return Math.min(preChange, 80);
    }
}
