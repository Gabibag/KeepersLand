package NameHere.Enviroments;

import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.Player;

import java.util.List;

public class Graveyard extends Enviorment{

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<Item> getShopItems() {
        return null;
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
