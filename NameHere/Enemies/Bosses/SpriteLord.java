package NameHere.Enemies.Bosses;

import NameHere.*;
import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpriteLord extends Boss {
    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 4;
        this.xp = 200;
        this.name = "Spirit Lord";
        this.coins = 75;
        this.tokens = 2;
        this.drops.add(ItemData.SpriteShard);
    }

    @Override
    public boolean canSpawn(Player p) {
        return (Main.r.nextBoolean() || Main.r.nextBoolean()); //75% spawn chance
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {
        for (int i = 0; i < 5; i++) {
            //add random elements of the list allSpirits in main to allies
            try {
                allies.add(Main.allSpirits.get(Main.r.nextInt(0, Main.allSpirits.size() - 1)).getClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        for (int i = 0; i < 4; i++) {
            allies.add(Main.allSpirits.get(Main.r.nextInt(0, Main.allSpirits.size() - 1)));
        }
        System.out.println("""

                                    /$$   /$$ /$$ /$$ /$$
                                   | $$  /$$/|__/| $$| $$
                                   | $$ /$$/  /$$| $$| $$
                                   | $$$$$/  | $$| $$| $$
                                   | $$  $$  | $$| $$| $$
                                   | $$\\  $$ | $$| $$| $$
                                   | $$ \\  $$| $$| $$| $$
                                   |__/  \\__/|__/|__/|__/""");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           " /$$$$$$$$/$$                \n" +
                           "|__  $$__/ $$                \n" +
                           "   | $$  | $$$$$$$   /$$$$$$ \n" +
                           "   | $$  | $$__  $$ /$$__  $$\n" +
                           "   | $$  | $$  \\ $$| $$$$$$$$\n" +
                           "   | $$  | $$  | $$| $$_____/\n" +
                           "   | $$  | $$  | $$|  $$$$$$$\n" +
                           "   |__/  |__/  |__/ \\_______/");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                           "  /$$$$$$            /$$           /$$   /$$             \n" +
                           " /$$__  $$          |__/          |__/  | $$             \n" +
                           "| $$  \\__/  /$$$$$$  /$$  /$$$$$$  /$$ /$$$$$$   /$$$$$$$\n" +
                           "|  $$$$$$  /$$__  $$| $$ /$$__  $$| $$|_  $$_/  /$$_____/\n" +
                           " \\____  $$| $$  \\ $$| $$| $$  \\__/| $$  | $$   |  $$$$$$ \n" +
                           " /$$  \\ $$| $$  | $$| $$| $$      | $$  | $$ /$$\\____  $$\n" +
                           "|  $$$$$$/| $$$$$$$/| $$| $$      | $$  |  $$$$//$$$$$$$/\n" +
                           " \\______/ | $$____/ |__/|__/      |__/   \\___/ |_______/ \n" +
                           "          | $$                                           \n" +
                           "          | $$                                           \n" +
                           "          |__/                                           ");
        Helper.Sleep(1);
        System.out.println(Colors.CLEAR);
    }
}
