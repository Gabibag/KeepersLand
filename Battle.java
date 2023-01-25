import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Battle extends Interactable{
    @Override
    public void OnChoose(Player p) {
        int Actions = p.getActionAmount();
        List<Enemy> spawns =getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, 3);//Maybe make an amount of enemies in environment?
//        System.out.println(Colors.CLEAR + Colors.CYAN + "You enter a battle against " + enemies.size() + " enemies" + Colors.RESET);
        System.out.println(Colors.CLEAR);
        System.out.println(Colors.RED + "A Battle occurs!" + Colors.RESET);
        Sleep(1);
        System.out.println(Colors.CLEAR);
        while(enemies.size() > 0){
            while(Actions > 0){
                for (Enemy enemy : enemies){
                    System.out.print(Colors.RED + enemy.getClass().getName() + "  ");
                }

                System.out.println(Colors.CYAN + "\nActions left:" + Actions +   Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Defend" );
                System.out.println("[3] Use Item"+ Colors.RESET);
                int choice = Main.getInput("\nPlayer: ");
                //TODO implement
                switch (choice) {
                    case 1 -> {
                        System.out.println("Attack");
                        for(int i = 0; i < enemies.size(); i ++){
                        System.out.println(Colors.PURPLE + "[" + (i + 1) +"] " + enemies.get(i).getClass().getName());
                        }
                    }

                    case 2 -> System.out.println("defend");
                    case 3 -> System.out.println("Open Inv");
                }

            Actions--;
            System.out.println(Colors.CLEAR);
            }
            System.out.println(Colors.RED);
            for (Enemy enemy : enemies) {

                enemy.Attack(p);
                Sleep(1);

            }
            Sleep(1.4);
            System.out.println(Colors.RESET+ Colors.CLEAR);
            Actions = p.getActionAmount();
        }

    }

    private static void Sleep(double s) {
        try {
            TimeUnit.MILLISECONDS.sleep((long)s*1000);
        } catch (InterruptedException e) {
            System.out.println(Colors.RED_BOLD + "You cannot quit at this time."+Colors.RESET);
        }
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
