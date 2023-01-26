package NameHere.Enviroments;
import java.util.List;
import NameHere.Player;
import NameHere.Colors;
import NameHere.Item;
import NameHere.Abstracts.Enviorment;

public class ToxicEnv extends Enviorment{
    @Override
    public List<Item> getShopItems() {
        return null;
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
        System.out.println(Colors.RED + "The toxic air increases the damage to " + (preChange + 5)+"!");
        return preChange + 5;
    }
}
