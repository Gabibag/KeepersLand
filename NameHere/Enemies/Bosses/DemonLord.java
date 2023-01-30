package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Helper;
import NameHere.Player;

import java.util.List;

public class DemonLord extends Boss {
    public void setBaseStats() {
        this.baseHp = 120;
        this.damage = 20;
        this.xp = 100;
        this.name = Colors.RED + "Demon Lord" + Colors.RESET;
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
