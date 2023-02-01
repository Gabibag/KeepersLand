package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enemies.Lava.DeathMinion;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class Death extends Boss{
    public void setBaseStats() {
        this.baseHp = 100;
        this.damage = 10;
        this.xp = 100;
        this.name = Colors.BLACK + "DEATH" + Colors.RESET;
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
                                     "_____\n"
                                    +"|_   _|\n"
                                    + " | | \n"
                                   +"   | | \n"
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
}
