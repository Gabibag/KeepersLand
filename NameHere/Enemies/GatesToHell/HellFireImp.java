package NameHere.Enemies.GatesToHell;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import java.util.List;
import NameHere.Enviroments.GatesToHell;

public class HellFireImp extends Enemy {

    @Override
    public void setBaseStats() {
        this.baseHp = 15;
        this.coins = 3;
        this.damage = 5;
        this.xp = 2;
        this.name = "Hellfire Imp";
        
    }
    @Override
    public int Attack(Player p, List<Enemy> allies) {
        if(Main.r.nextFloat() > 0.6){
            System.out.println("The imp creates a fireball hurtling towards the player for " + (this.damage * 3) + " damage");
            allies.add( new Fireball());
            return 0;
        }
        else{
            System.out.println("The imp burns the player for " + this.damage + " damage");
            return this.damage;
        }

    }
    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof GatesToHell;
    }
    
}
