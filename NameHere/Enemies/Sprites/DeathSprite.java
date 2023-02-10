package NameHere.Enemies.Sprites;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class DeathSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 5;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Death Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 20) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove 10% of the regular player's hp
        System.out.println("The Death Spirit removes 10% of the player's hp");
        return (int)(p.getHp()*0.1);
    }
}
