package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class WindyHeights extends Enviorment {
    private int turn = 0;
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Rock, ItemData.StoneShield, ItemData.StoneSword);
    }

    public String getDescription() {
        return "The heights some how make it easier to move?";
    }

    public String getName() {
        return "Windy Heights";
    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    public void playerAction(Player p){

    }
    public void turnEnd(Player p, List<Enemy> enemies){
        //increase the dodge rate of the enemy by 1 every other turn
        if (turn % 2 == 0) {
            for (Enemy e : enemies) {
                e.setDodgeRate(e.getDodgeRate() + 1);
            }
        }
        turn++;
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
