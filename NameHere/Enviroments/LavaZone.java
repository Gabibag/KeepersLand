package NameHere.Enviroments;
import NameHere.*;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LavaZone extends Enviorment{
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(new Item[]{
            ItemData.LavaVial
        });
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
