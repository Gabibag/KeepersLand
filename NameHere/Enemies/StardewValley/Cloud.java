package NameHere.Enemies.StardewValley;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.StardewValley;
import NameHere.Main;
import NameHere.Player;

import java.util.List;
import java.util.Random;

public class Cloud extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 20;
        this.name = "Cloud";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 2;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof StardewValley; //(r.nextInt([spawnchance]) == 2)

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //20% chance to do 20% less damage
        if (r.nextInt(5) == 2) {
            return (int)(damage*0.8);
        }
        return damage;
    }
}
