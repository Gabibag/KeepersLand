package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class Bug extends Boss {
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
