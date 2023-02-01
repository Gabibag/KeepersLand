package NameHere.Enemies.Spirits;
import NameHere.Abstracts.Spirit;
import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class DoubleSpirit extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "DoubleSpirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1,10)==2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //make all allies but itself attack again
        System.out.println("The Double Spirit makes all allies attack again");
        for (Enemy e : allies) {
            if (e != this) {
                e.Attack(p, allies);
            }
        }
        return 0;
    }
}
