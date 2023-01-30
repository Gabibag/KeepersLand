package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enemies.Toxic.HealingSpirit;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class Angel extends Boss {
    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 3;
        this.xp = 120;
        this.name = "Angel";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return (Main.r.nextBoolean()||Main.r.nextBoolean()); //75% spawn chance
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {

    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println("\n" +
                           "O~~   O~~      O~~ O~~\n" +
                           "O~~  O~~    O~ O~~ O~~\n" +
                           "O~~ O~~        O~~ O~~\n" +
                           "O~ O~      O~~ O~~ O~~\n" +
                           "O~~  O~~   O~~ O~~ O~~\n" +
                           "O~~   O~~  O~~ O~~ O~~\n" +
                           "O~~     O~~O~~O~~~O~~~\n" +
                           "                      ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           "O~~~ O~~~~~~              \n" +
                           "     O~~O~~               \n" +
                           "     O~~O~~        O~~    \n" +
                           "     O~~O~ O~    O~   O~~ \n" +
                           "     O~~O~~  O~~O~~~~~ O~~\n" +
                           "     O~~O~   O~~O~        \n" +
                           "     O~~O~~  O~~  O~~~~   \n" +
                           "                          ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR + Colors.YELLOW +
                           "      O~                                   O~~\n" +
                           "     O~ ~~                                 O~~\n" +
                           "    O~  O~~    O~~ O~~     O~~      O~~    O~~\n" +
                           "   O~~   O~~    O~~  O~~ O~~  O~~ O~   O~~ O~~\n" +
                           "  O~~~~~~ O~~   O~~  O~~O~~   O~~O~~~~~ O~~O~~\n" +
                           " O~~       O~~  O~~  O~~ O~~  O~~O~        O~~\n" +
                           "O~~         O~~O~~~  O~~     O~~   O~~~~  O~~~\n" +
                           "                          O~~                 ");
        Helper.Sleep(1);
        System.out.println(Colors.RESET + Colors.CLEAR);
        for (int i = 0; i < 4; i++) {
            allies.add(new HealingSpirit());
        }
    }
}
