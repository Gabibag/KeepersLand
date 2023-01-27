package NameHere.Enviroments;

import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class Graveyard extends Enviorment{

    @Override
    public String getDescription() {
        return "A spooky Graveyard full of enemies";
    }

    @Override
    public List<Item> getShopItems() {
        
        return Arrays.asList(new Item[]{
            ItemData.tombStone,
            ItemData.GraveFlower
        });

    }

    @Override
    public String getName() {
        return "Graveyard";
    }

    @Override
    public void playerAction(Player p) {

    }

    @Override
    public void turnEnd(Player p) {

    }

    @Override
    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    @Override
    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
