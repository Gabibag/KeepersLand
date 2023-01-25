import java.util.*;
public class Battle extends Interactable{
    @Override
    public void OnChoose(Player p) {
        List<Enemy> Enemy =getEnemies(p); //adds all enemies to a list of enemies
    }
    public List<Enemy> getEnemies(Player p){
        List<Enemy> returned = new ArrayList<Enemy>();
        for(Enemy e: Main.allEnemies){
            if(e.canSpawn(p)){
                returned.add((e));
            }
        }
        return returned;
    }
}
