package NameHere.Enviroments;

import NameHere.Abstracts.Enviorment;
import NameHere.Colors;
import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class ToxicEnv extends Enviorment{
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(new Item(1, 0, "Toxic Fang", "A strange toxic fang of some creature.", 100, 15),
                             new Item(2, 2, "Swamp Potion","A mystery potion created by a witch in the swamp.", 400, 45));
    }
    public String getDescription(){
        return "A toxic swamp that makes attack do more damage.";
    }
    public String getName(){
        return "Toxic Swamp";
    }
    public int modifyPlayerDamage(int preChange){
        return preChange;
    }
    public void playerAction(Player p){

    }
    public void turnEnd(Player p){

    }
    public int modifyEnemyDamage(int preChange){
        //TODO scaling
        int dmgInc = Main.r.nextInt(3);
        if(dmgInc == 0) { 
            return preChange;
        }
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + dmgInc)+"!");
        return preChange + dmgInc;
    }
}
