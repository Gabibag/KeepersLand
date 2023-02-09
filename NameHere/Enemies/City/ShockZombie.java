package NameHere.Enemies.City;

import java.util.List;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.AbandonedCity;

public class ShockZombie extends Enemy{

    @Override
    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 6;
        this.coins =  4;
        this.xp = 4;
        this.name = "Electric Mutant"; 
        
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The muntant shocks the player for " + this.damage + " damage");
        System.out.println("The muntant shocks its allies for " + (int)(this.damage/3) + " damage");
        for(Enemy e : allies) {
            if(e != this){
            e.setBattleHp(e.getBattleHp() - (int)(this.damage/3));
            }

        }
        return this.damage;
    }
    
}
