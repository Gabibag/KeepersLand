package NameHere.Enemies.Spirits;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class TankSpirit extends Spirit {
    public void setBaseStats() {
        this.baseHp = 20;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "TankSpirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //if the allies battlehp is less than this hp, set the allies battlehp to this hp and tell the player that you have to kill this first
        for (Enemy e : allies) {
            if (e.getBattleHp() < this.getBattleHp()) {
                e.setBattleHp(this.getBattleHp());
                System.out.print("The Tank Spirit has set the battle hp of " + e.getName() + " to " + this.getBattleHp());
                System.out.println(", you must kill the Tank Spirit first");
            }
        }

        return 0;
    }
}
