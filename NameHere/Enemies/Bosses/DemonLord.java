package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Enemies.Lava.Demon;
import NameHere.Colors;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemonLord extends Boss {
    public static boolean End = false;

    public void setBaseStats() {
        this.baseHp = 120;
        this.damage = 20;
        this.xp = 100;
        this.name = "Demon Lord";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
//        return false;
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {


    }
    @Override
    public int BossAttack(Player p, List<Enemy> allies){
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Create a new thread
        Future<Integer> f = executor.submit(new Callable<Integer>() {
            public Integer call(){
                int Pos = Main.r.nextInt(7);
                int SPos = Main.r.nextInt(7);
                System.out.println(Pos);
                System.out.println(SPos);
                if(Pos == SPos){
                    SPos = (SPos + 1) % 6;
                }
                if(!(Math.abs(Pos - SPos) % 2 == 0) ){
                    System.out.println("Dif" + Math.abs(Pos - SPos));
                    SPos = (SPos + 1);
                    if(SPos == 7){
                        SPos = 0;
                    }

                }
                if(Pos == SPos){
                    SPos = (SPos + 1) % 6;
                }
                System.out.println(Pos);
                System.out.println(SPos);
                Boolean PDown = false;
                Boolean SDown = true;
                while(!DemonLord.End){
                    if(Pos <= 0 ){
                        PDown = false; 
                    }
                    else if(Pos >= 6){
                        PDown = true;
                    }
                    if(SPos <= 0 ){
                        SDown = false; 
                    }
                    else if(SPos >= 6){
                        SDown = true;
                    }
                    if(PDown){
                        Pos--;
                    }
                    else{
                        Pos++;
                    }
                    if(SDown){
                        SPos--;
                    }
                    else{

                        SPos++;
                    } 

                    String O = ".......";
                    String S = ".......";
                    O = O.substring(0, Pos) + Pos + O.substring(Pos + 1);
                    S = S.substring(0, SPos) + SPos + S.substring(SPos + 1);
                    System.out.println(O + "\n" + S);
                    //Helper.Prompt("a");
                    Helper.Sleep(0.3);
                    System.out.print(String.format("\033[%dA",2));
                }
                System.out.println(Colors.CLEAR);
                return Math.abs(Pos - SPos);
            }
        });
        Scanner s = new Scanner(System.in);
        s.nextLine();
        DemonLord.End = true;
        try {
            int i = f.get();
            int dmg = this.getDamage();
            if(i == 0){
                System.out.println("You dodged the demon's attack!");
                dmg = 0;
            }
            else if(Math.abs(1 - i) == 1){
                dmg /= 2;
                System.out.println("You are grazed by the demon's attack!, and take half damage\n You are hit for " + dmg + " damage");

            }
            else{
                System.out.println("The Demon Lord's attack hits you for  " + dmg + " damage");
            }
            System.out.println();
            DemonLord.End = false;
            return dmg;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to get result");
        return 0;
        
    }

    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println("\n" +Colors.CLEAR +
                           " ▄▀▀▀█▄    ▄▀▀█▄   ▄▀▄▄▄▄   ▄▀▀█▄▄▄▄ \n" +
                           "█  ▄▀  ▀▄ ▐ ▄▀ ▀▄ █ █    ▌ ▐  ▄▀   ▐ \n" +
                           "▐ █▄▄▄▄     █▄▄▄█ ▐ █        █▄▄▄▄▄  \n" +
                           " █    ▐    ▄▀   █   █        █    ▌  \n" +
                           " █        █   ▄▀   ▄▀▄▄▄▄▀  ▄▀▄▄▄▄   \n" +
                           "█         ▐   ▐   █     ▐   █    ▐   \n" +
                           "▐                 ▐         ▐        "  );
        Helper.Sleep(1.5);
        System.out.println("\n" + Colors.CLEAR +
                           " ▄▀▀▀█▀▀▄  ▄▀▀▄ ▄▄   ▄▀▀█▄▄▄▄ \n" +
                           "█    █  ▐ █  █   ▄▀ ▐  ▄▀   ▐ \n" +
                           "▐   █     ▐  █▄▄▄█    █▄▄▄▄▄  \n" +
                           "   █         █   █    █    ▌  \n" +
                           " ▄▀         ▄▀  ▄▀   ▄▀▄▄▄▄   \n" +
                           "█          █   █     █    ▐   \n" +
                           "▐          ▐   ▐     ▐         ");
        Helper.Sleep(1.5);
        System.out.println("\n" +Colors.CLEAR + Colors.BLACK +
                           " ▄▀▀█▄▄   ▄▀▀█▄▄▄▄  ▄▀▀▄ ▄▀▄  ▄▀▀▀▀▄   ▄▀▀▄ ▀▄     ▄▀▀▀▀▄    ▄▀▀▀▀▄   ▄▀▀▄▀▀▀▄  ▄▀▀█▄▄  \n" +
                           "█ ▄▀   █ ▐  ▄▀   ▐ █  █ ▀  █ █      █ █  █ █ █    █    █    █      █ █   █   █ █ ▄▀   █ \n" +
                           "▐ █    █   █▄▄▄▄▄  ▐  █    █ █      █ ▐  █  ▀█    ▐    █    █      █ ▐  █▀▀█▀  ▐ █    █ \n" +
                           "  █    █   █    ▌    █    █  ▀▄    ▄▀   █   █         █     ▀▄    ▄▀  ▄▀    █    █    █ \n" +
                           " ▄▀▄▄▄▄▀  ▄▀▄▄▄▄   ▄▀   ▄▀     ▀▀▀▀   ▄▀   █        ▄▀▄▄▄▄▄▄▀ ▀▀▀▀   █     █    ▄▀▄▄▄▄▀ \n" +
                           "█     ▐   █    ▐   █    █             █    ▐        █                ▐     ▐   █     ▐  \n" +
                           "▐         ▐        ▐    ▐             ▐             ▐                          ▐        ");
        Helper.Sleep(2);
        System.out.println(Colors.CLEAR);
    }
}
