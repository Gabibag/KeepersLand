package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class TankSprite extends Sprite {
    public void setBaseStats() {
        this.baseHp = 15;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Tank Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //if the allies battlehp is less than this hp, set the allies battlehp to this hp and tell the player that you have to kill this first
        for (Enemy e : allies) {
            if (e.getBattleHp() < this.getBattleHp()) {
                e.setBattleHp(this.getBattleHp());
                System.out.print("The Tank Sprite has "+ Colors.BLACK + "resets" + Colors.RESET + " the battle hp of " + Colors.RED + e.getName() + Colors.RESET + " to " + Colors.RED + this.getBattleHp());
                System.out.println(", you must kill the Tank Sprite first");
            }
        }

        return 0;
    }
}
