package NameHere.Enemies.Sprites;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Spirit;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class AbsorbSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Absorb Sprite";
        this.damage = 1;
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //add 10% of all the enemies battlehp and damage to its battlehp and damage
        for (Enemy target : allies) {
            this.setBattleHp(this.getBattleHp() + (int)(target.getBattleHp()*0.02));
            this.setDamage(this.getDamage() + (int)(target.getDamage()*0.02));
        }
        System.out.println("The Absorb Spirit absorbs 2% of all the enemies hp and damage");

        return damage;
    }
}
