package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class CherryBlossoms extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Petal, ItemData.CherryBlossom, ItemData.CherryBark);
    }

    public String getDescription() {
        return "A peaceful land. Everything heals after every turn";
    }

    public String getName() {
        return "Cherry Blossoms";

    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;

    }

    public void playerAction(Player p) {

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //heal players and enemies for 10% of its current hp
        p.setBattleHp(p.getBattleHp() + (int) (p.getBattleHp() * 0.05) + 1);
        for (Enemy e : enemies) {
            e.setBattleHp(e.getBattleHp() + (int) (e.getBattleHp() * 0.05) + 1);
        }
        System.out.println("The cherry blossoms healed everyone for 5% of their current hp");
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
