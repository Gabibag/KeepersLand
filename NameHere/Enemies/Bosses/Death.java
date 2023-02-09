package NameHere.Enemies.Bosses;

import NameHere.*;
import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Enemies.Lava.DeathMinion;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Death extends Boss{
    protected static boolean End;
    public void setBaseStats() {
        this.baseHp = 100;
        this.damage = 10;
        this.xp = 100;
        this.name = "DEATH";
        this.coins = 50;
        this.tokens = 1;
        this.drops.add(ItemData.DeathShard);
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
//        return false;
    }
    @Override
    public void onDeath(Player p, List<Enemy> allies){
        Helper.Sleep(1);
        System.out.println("Death escapes and summons its minions to kill you");
        for (int i = 0; i < Main.r.nextInt(2,4) + 4; i++) {
            allies.add(new DeathMinion());
        }
        Helper.contiuePrompt();
        System.out.println(Colors.CLEAR);
    }
    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println(
                """
                        _______
                        |_   _|
                          | |\s
                          | |\s
                         _| |_
                        |_____|
                        """);
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
        System.out.println("Press enter when the x is visable to dodge death's attack! ");
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Create a new thread
        Future<Integer> f = executor.submit(new Callable<Integer>() {
            int progress = 0;
            public Integer call(){
                while(true){
                    progress++;
                    StringBuilder s = new StringBuilder();
                    s.append("=".repeat(Math.max(0, progress)));// I- power of intellij
                    s.append(" ".repeat(Math.max(0, 15 - progress)));
                    String pointer = progress == 8 ? "X" : "O";
                    s = new StringBuilder(s.substring(0, 7) + pointer + s.substring(8));
                    if(progress > 15){
                        progress = 0;
                    }
                    
                    System.out.print(s + " \r");
                    if(Death.End){
                        return progress -1;
                    }
                    Helper.Sleep(0.08);
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
                System.out.println("You dodged death's attack!");
                dmg = 0;
            }
            else if(Math.abs(8 - i) == 1 || Math.abs(8 - i) == 2){
                dmg /= 2;
                System.out.println("You are grazed by death's attack!, and take half damage\nYou are hit for " + dmg + " damage");

            }
            else{
                System.out.println("Death's attack hits you for  " + dmg + " damage");
            }
            System.out.println();
            Death.End = false;
            return dmg;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
        
    }
}
