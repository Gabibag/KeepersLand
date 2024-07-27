package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpriteLord extends Boss {
    public SpriteLord() {
        super("Drops a Sprite Shard. Creates 5 random sprites on death.");

    }

    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 4;
        this.xp = 200;
        this.name = "Sprite Lord";
        this.coins = 75;
        this.tokens = 2;
        this.drops.add(ItemData.SpriteShard);
    }

    @Override
    public boolean canSpawn() {
        return true; //75% spawn chance
    }

    @Override
    public String getDodgeText() {
        return " dissolves your attack.";
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        for (int i = 0; i < 5; i++) {
            //add random elements of the list allSprites in main to allies
            try {
                Enemy e = Main.allSprites.get(Main.r.nextInt(0, Main.allSprites.size() - 1)).getClass().getDeclaredConstructor().newInstance();
                e.setLevel(this.getLevel());
                e.setMutate(Main.allMutations.get(Main.r.nextInt(0, Main.allMutations.size() - 1)));
                allies.add(e);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        for (int i = 0; i < 4; i++) {
            Enemy e = null;
            try {
                e = Main.allSprites.get(Main.r.nextInt(0, Main.allSprites.size() - 1)).getClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
            e.setLevel(this.getLevel());
            e.setMutate(Main.allMutations.get(Main.r.nextInt(0, Main.allMutations.size() - 1)));
            allies.add(e);
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
                "  /$$$$$$  /$$$$$$$  /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$$$$$$$  /$$$$$$ \n" +
                " /$$__  $$| $$__  $$| $$__  $$|_  $$_/|__  $$__/| $$_____/ /$$__  $$\n" +
                "| $$  \\__/| $$  \\ $$| $$  \\ $$  | $$     | $$   | $$      | $$  \\__/\n" +
                "|  $$$$$$ | $$$$$$$/| $$$$$$$/  | $$     | $$   | $$$$$   |  $$$$$$ \n" +
                " \\____  $$| $$____/ | $$__  $$  | $$     | $$   | $$__/    \\____  $$\n" +
                " /$$  \\ $$| $$      | $$  \\ $$  | $$     | $$   | $$       /$$  \\ $$\n" +
                "|  $$$$$$/| $$      | $$  | $$ /$$$$$$   | $$   | $$$$$$$$|  $$$$$$/\n" +
                " \\______/ |__/      |__/  |__/|______/   |__/   |________/ \\______/ ");
        Helper.Sleep(1);
        System.out.println(Colors.CLEAR);
    }
}
