import java.util.*;
public class Battle extends Interactable{
    @Override
    public void OnChoose(Player p) {
        int Actions = p.getActionAmount();
        List<Enemy> spawns =getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, 3);//Maybe make an amount of enemies in environment?
        System.out.println("You enter a battle against " + enemies.size() + " enemies");
        while(enemies.size() > 0){
            while(Actions > 0){
            System.out.println("Your move("+ Actions +" actions left this turn): ");
            System.out.println("[1] Attack");
            System.out.println("[2] Defend");
            System.out.println("[3] Use Item");
            int choice = Main.getInput("Your Choice: ");
            //TODO implement
            switch(choice){
                case 1:
                    System.out.println("Attack");
                    break;
                case 2:
                    System.out.println("defend");
                    break;
                case 3:
                    System.out.println("Open Inv");
                    break;
            }
            Actions--;
        }
        for (Enemy enemy : enemies) {
            System.out.println(enemy.getClass().getName() + " attacks!");
            enemy.Attack(p);
        }
        Actions = p.getActionAmount();
        }
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
