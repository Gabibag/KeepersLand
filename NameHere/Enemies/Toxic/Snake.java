package NameHere.Enemies.Toxic;

import java.util.List;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.ToxicEnv;

public class Snake extends Enemy {
    public void setBaseStats(){
        this.baseHp = 15;
        this.damage = 8;
        this.xp = 10;
        this.name = "Snake";
        this.coins = 3;
    }
    public int Attack(Player p, List<Enemy> allies) {
        int dmg = (int)(p.getHp() * (8f / 100));
        System.out.println("The snake bites the player for "+this.damage+"% of their hp. You take" + dmg +  " damage");
        return dmg;
    }
    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof ToxicEnv; //(r.nextInt([spawnchance]) == 2)

    }
    
}
