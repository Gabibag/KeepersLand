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
        System.out.println(Colors.RED + "The lava's heat increases the damage!");
        return preChange + 5;
    }
}
