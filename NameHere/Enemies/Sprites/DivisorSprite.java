package NameHere.Enemies.Sprites;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Colors;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class DivisorSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Divisor Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //return the player's battle hp divided by two
        System.out.println("The Divisor Spirit " + Colors.RED + "divides"+ Colors.RESET + " the player's hp by two");
        return p.getBattleHp()/2;
    }
}
