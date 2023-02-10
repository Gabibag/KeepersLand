package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enemies.Lava.LavaSlime;
import KeeperLand.Helper;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.List;

public class MegaLavaSlime extends Boss{
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 100;
        this.name = "Mega Lava Slime";
        this.coins = 50;
        this.tokens = 1;
        this.drops.add(ItemData.ShatteredShard);
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
//        return false;
    }
    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self){
        System.out.println("The Mega Lava Slime splits into 3 Lava Slimes");
        for (int i = 0; i < 3; i++) {
            allies.add(new LavaSlime());
        }
    }
    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println("\n" +
                           "    _____                       __            __        __  __  __        __   __     \n" +
                           "   |     \\                     |  \\          |  \\      |  \\|  \\|  \\      |  \\ |  \\    \n" +
                           "    \\$$$$$ __    __   _______ _| $$_         | $$   __  \\$$| $$| $$       \\$$_| $$_   \n" +
                           "      | $$|  \\  |  \\ /       \\   $$ \\        | $$  /  \\|  \\| $$| $$      |  \\   $$ \\  \n" +
                           " __   | $$| $$  | $$|  $$$$$$$\\$$$$$$        | $$_/  $$| $$| $$| $$      | $$\\$$$$$$  \n" +
                           "|  \\  | $$| $$  | $$ \\$$    \\  | $$ __       | $$   $$ | $$| $$| $$      | $$ | $$ __ \n" +
                           "| $$__| $$| $$__/ $$ _\\$$$$$$\\ | $$|  \\      | $$$$$$\\ | $$| $$| $$      | $$ | $$|  \\\n" +
                           " \\$$    $$ \\$$    $$|       $$  \\$$  $$      | $$  \\$$\\| $$| $$| $$      | $$  \\$$  $$\n" +
                           "  \\$$$$$$   \\$$$$$$  \\$$$$$$$    \\$$$$        \\$$   \\$$ \\$$ \\$$ \\$$       \\$$   \\$$$$ ");
        Helper.Sleep(1.5);
        System.out.println(Colors.CLEAR);
    }
}
