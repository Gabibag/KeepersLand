import java.util.*;
public class Battle extends Interactable{
    @Override
    public void OnChoose(Player p) {
        List Enemy =getEnemies(p);
    }
    public List<Enemy> getEnemies(Player p){
        List<Enemy> returned = new ArrayList<Enemy>();
        for(Enemy e: Main.allEnemies){
            if(e.canSpawn(Main.player)){
                returned.add((e));
            }
        }
        return returned;
    }
}
