package NameHere.Enemies.Spirits;

import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Spirit;
import java.util.List;

public class DebuffSpirit extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Debuff Spirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove an action from the player
        p.setActionAmount(p.getActionAmount() - 1);
        System.out.println("Debuff Spirit removes an action from you");
        return 0;
    }
}