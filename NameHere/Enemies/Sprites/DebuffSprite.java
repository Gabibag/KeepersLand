package NameHere.Enemies.Sprites;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Colors;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class DebuffSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Debuff Spirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 20) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove an action from the player
        p.setActionAmount(1);
        System.out.println("Debuff Spirit " + Colors.RED + "removes" + Colors.RESET + " an action from you");
        return 0;
    }
}
