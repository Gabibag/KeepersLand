package NameHere.Enviroments;
import NameHere.*;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;

import java.util.ArrayList;
import java.util.List;

public class LavaZone extends Enviorment{
    @Override
    public List<Item> getShopItems() {
        Item i = new Item(3, 0, "Lava Vial", "A vial of molten lava.", 15, 15);
        List<Item> r = new ArrayList<Item>();
        r.add(i);
        return r;
    }
    public String getName(){
        return "Lava Zone";
    }
    public void playerAction(Player p){

    }
    public void turnEnd(Player p){

    }
    public int modifyEnemyDamage(int preChange){
        return preChange;
    }
    public int modifyPlayerDamage(int preChange){
        return preChange;
    }
    public String getDescription(){
        return "A lava zone full of lava...";
    }

}
