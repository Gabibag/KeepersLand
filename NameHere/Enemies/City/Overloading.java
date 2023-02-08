package NameHere.Enemies.City;

import java.util.List;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.AbandonedCity;

public class Overloading extends Enemy{

    @Override
    public void setBaseStats() {
        this.baseHp  = 10;
        this.damage = 30;
        this.name = "OverLoaded Zombie";
        this.coins = 7;
        this.xp = 4; 
        
    }
    int chargeLeft = 3;
    public int Attack(Player p, List<Enemy> a){
        if(chargeLeft == 0){
            System.out.println("The OverLoaded Zombie explodes, dealing " + this.damage + " damage to the player and killing itself");
            a.remove(this);
            return this.damage;
        }
        chargeLeft--;
        System.out.println("The OverLoaded Zombie charges up");
        return 0;
    } 
    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    
}
