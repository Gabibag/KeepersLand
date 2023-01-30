package NameHere.Enviroments;

import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;

import java.util.*;

public class LavaZone extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.LavaVial, ItemData.TougherTimes);
    }

    public String getName() {
        return "Lava Zone";
    }

    public void playerAction(Player p){

    }
    public void turnEnd(Player p, List<Enemy> enemies){
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    public String getDescription() {
        return "A lava zone full of lava...";
    }

}
