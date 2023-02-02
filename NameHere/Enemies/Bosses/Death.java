package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enemies.Lava.DeathMinion;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.security.PublicKey;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Death extends Boss{
    protected static boolean End;
    public void setBaseStats() {
        this.baseHp = 100;
        this.damage = 10;
        this.xp = 100;
        this.name = "DEATH";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
//        return false;
    }
    @Override
    public void onDeath(Player p, List<Enemy> allies){
        Helper.Sleep(1);

        System.out.println(Colors.CLEAR + Colors.BLACK + "NO, YOU " + Colors.RED + "CANNOT DEFEAT ME!" + Colors.BLACK + " I SHALL " + Colors.RED + "BRING YOU DOWN " + Colors.BLACK + " EVEN IF I SACRIFICE " + Colors.BLACK_BACKGROUND + Colors.RED_BOLD + "MY SELF" +
                           Colors.RESET);
        for (int i = 0; i < Main.r.nextInt(3) + 4; i++) {
            allies.add(new DeathMinion());
        }
        Helper.contiuePrompt();
        System.out.println(Colors.CLEAR);
    }
    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println(
                                     "______\n"
                                    +"|_   _|\n"
                                    +"  | | \n"
                                    +"  | | \n"
                                    +" _| |_\n"
                                    +"|_____|\n" );
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           "                       \n" +
                           "     /\\                \n" +
                           "    /  \\     _ __ ___  \n" +
                           "   / /\\ \\   | '_ ` _ \\ \n" +
                           "  / ____ \\  | | | | | |\n" +
                           " /_/    \\_\\ |_| |_| |_|" );
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR + Colors.RED_BOLD +
                           "  _____    ______              _______   _    _ \n" +
                           " |  __ \\  |  ____|     /\\     |__   __| | |  | |\n" +
                           " | |  | | | |__       /  \\       | |    | |__| |\n" +
                           " | |  | | |  __|     / /\\ \\      | |    |  __  |\n" +
                           " | |__| | | |____   / ____ \\     | |    | |  | |\n" +
                           " |_____/  |______| /_/    \\_\\    |_|    |_|  |_|" );
        Helper.Sleep(2);
        System.out.println(Colors.CLEAR);
    }
    public int BossAttack(Player p, List<Enemy> allies) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Create a new thread
        Future<Integer> f = executor.submit(new Callable<Integer>() {
            int progress = 0;
            public Integer call(){
                while(true){
                    progress++;
                    String s = "";
                    for (int i = 0; i < progress; i++) {
                        s += "=";
                    }
                    for(int i = 0; i < 15 - progress; i++){
                        s += " ";
                    }
                    String pointer = progress == 8 ? "X" : "O";
                    s = s.substring(0, 7) + pointer + s.substring(8);
                    if(progress > 15){
                        progress = 0;
                    }
                    
                    System.out.print(s + " \r");
                    if(Death.End){
                        return progress -1;
                    }
                    Helper.Sleep(0.05);
                }
                
            }
        });
        Scanner s = new Scanner(System.in);
        s.nextLine();

        Death.End = true;
        try {
            int i = f.get();
            int dmg = this.getDamage();
            if(i == 8){
                System.out.println("You dodged deaths attack!");
                dmg = 0;
            }
            else if(Math.abs(8 - i) == 1){
                dmg /= 2;
                System.out.println("You are grazed by death's attack!, and take half damage\n You are hit for " + dmg + " damage");

            }
            else{
                System.out.println("Death's attack hits you for  " + dmg + " damage");
            }
            System.out.println();
            Death.End = false;
            return dmg;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
        
    }
}
