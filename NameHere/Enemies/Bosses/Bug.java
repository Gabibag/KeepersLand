package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Bug extends Boss {
    protected static boolean End;

    public void setBaseStats() {
        this.baseHp = 75;
        this.damage = 5;
        this.xp = 120;
        this.name = "Bug";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return (Main.r.nextBoolean() || Main.r.nextBoolean()); //75% spawn chance
//        return false;
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {

    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println(
                           " /$$$$$$$            /$$             /$$              \n" +
                           "| $$__  $$          | $$            | $$              \n" +
                           "| $$  \\ $$  /$$$$$$ | $$  /$$$$$$  /$$$$$$    /$$$$$$ \n" +
                           "| $$  | $$ /$$__  $$| $$ /$$__  $$|_  $$_/   /$$__  $$\n" +
                           "| $$  | $$| $$$$$$$$| $$| $$$$$$$$  | $$    | $$$$$$$$\n" +
                           "| $$  | $$| $$_____/| $$| $$_____/  | $$ /$$| $$_____/\n" +
                           "| $$$$$$$/|  $$$$$$$| $$|  $$$$$$$  |  $$$$/|  $$$$$$$\n" +
                           "|_______/  \\_______/|__/ \\_______/   \\___/   \\_______/\n" +
                           "                                                      \n" +
                           "                                                      \n" +
                           "                                                      ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           " /$$$$$$$$/$$                \n" +
                           "|__  $$__/ $$                \n" +
                           "   | $$  | $$$$$$$   /$$$$$$ \n" +
                           "   | $$  | $$__  $$ /$$__  $$\n" +
                           "   | $$  | $$  \\ $$| $$$$$$$$\n" +
                           "   | $$  | $$  | $$| $$_____/\n" +
                           "   | $$  | $$  | $$|  $$$$$$$\n" +
                           "   |__/  |__/  |__/ \\_______/\n" +
                           "                             \n" +
                           "                             \n" +
                           "                             ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           " /$$$$$$$                     \n" +
                           "| $$__  $$                    \n" +
                           "| $$  \\ $$ /$$   /$$  /$$$$$$ \n" +
                           "| $$$$$$$ | $$  | $$ /$$__  $$\n" +
                           "| $$__  $$| $$  | $$| $$  \\ $$\n" +
                           "| $$  \\ $$| $$  | $$| $$  | $$\n" +
                           "| $$$$$$$/|  $$$$$$/|  $$$$$$$\n" +
                           "|_______/  \\______/  \\____  $$\n" +
                           "                     /$$  \\ $$\n" +
                           "                    |  $$$$$$/\n" +
                           "                     \\______/ ");
        Helper.Sleep(1.5);
        System.out.println(Colors.CLEAR);
    }
    public int BossAttack(Player p, List<Enemy> allies) {
        System.out.println("Press enter when a green circle appears");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            public Integer call()  {
                String shape = "n";
                String Color = "n";
                while(!Bug.End){
                     shape = Helper.getRandomElements(Arrays.asList("O", "X", "■"), 1).get(0);
                    Color = Helper.RandomColor();
                    System.out.print("\r :" + Color + shape + Colors.RESET);

                    Helper.Sleep(0.5);
                }
                System.out.println(Colors.RESET);
                int result =  shape.equals("O") ? 1 : 0;
                result += (Color.equals(Colors.GREEN)) ? 1 : 0; 
                return result;
            }
        });
        try {
            Main.s.nextLine();
            Bug.End = true;
            int res = future.get();
            Bug.End = false;
            if(res ==0){
                System.out.println("You failed to dodge");
                 return this.damage;
            }
            if(res == 2){
                System.out.println("You dodged the attack");
                return 0;
            }
            if(res == 1){
                System.out.println("You almost dodged the attack");
                return this.damage/2;
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    //override the attack command in enemy
    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("Bug deals <error> damage");
        return Main.r.nextInt(damage-(int)(damage*0.2), damage + (int)(damage*0.2));
    }

    @Override
    public String displayBattleHp(){
        return "<error>";
    }
}
