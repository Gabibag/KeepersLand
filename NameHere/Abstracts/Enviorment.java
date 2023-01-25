package NameHere.Abstracts;
import java.util.*;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;
public abstract class Enviorment {
    public Enviorment(){
        Main.allPlaces.add((this));
    }
    public abstract List<Item> getShopItems();
    //TODO status effects
    public boolean isVaild(Player p){
        return true;
    }
}
