package NameHere.Enemies.Sprites;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class TankSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 15;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Tank Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 20) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //if the allies battlehp is less than this hp, set the allies battlehp to this hp and tell the player that you have to kill this first
        for (Enemy e : allies) {
            if (e.getBattleHp() < this.getBattleHp()) {
                e.setBattleHp(this.getBattleHp());
                System.out.print("The Tank Spirit has resets the battle hp of " + e.getName() + " to " + this.getBattleHp());
                System.out.println(", you must kill the Tank Spirit first");
            }
        }

        return 0;
    }
}
