package NameHere.Enviroments;
import java.util.*;
import NameHere.*;
import NameHere.Abstracts.Enviorment;
public class LavaEnv extends Enviorment{

    @Override
    public List<Item> getShopItems() {
        return null;
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
        return "A lava zone";
    }

}
