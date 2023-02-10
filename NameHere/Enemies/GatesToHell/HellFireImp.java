package NameHere.Enemies.GatesToHell;

import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enviroments.GatesToHell;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

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
            System.out.println("The imp throws a " + Colors.RED + "fireball" + Colors.RESET + " at the player ");
            allies.add( new Fireball());
            return 0;
        }
        else{
            System.out.println("The imp " + Colors.RED + "burns" + Colors.RESET + " the player.");
            return this.damage;
        }

    }
    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof GatesToHell;
    }
    
}
