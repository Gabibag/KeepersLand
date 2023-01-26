package NameHere.Enviroments;

import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.Player;

import java.util.List;

public class Graveyard extends Enviorment{

    @Override
    public String getDescription() {
        return "A spooky Graveyard full of enemies";
    }

    @Override
    public List<Item> getShopItems() {
        Item i = new Item(1, 0,"Tombstone", "Wait,how does that work?", 15, 25);
        i.setHealIncrease(1);
        return Arrays.asList(new Item[]{
            i,
            new Item(0, 5,"GraveFlower", "A Flower said to cure sickness", 30, 50)
        });
        return Arrays.asList(Item.skeletonBone);
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
