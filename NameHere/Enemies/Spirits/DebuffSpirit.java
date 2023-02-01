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
        //remove 10% of the player's dmg (the variable damage is named dmg)
        p.setDmg((int)(p.getDmg() - (p.getDmg()*0.1)));
        System.out.println("The Debuff Spirit debuffs the player for 10% of their damage");
        return 0;
    }
}
