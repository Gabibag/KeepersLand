package NameHere.Enemies.City;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.AbandonedCity;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class BuringMutant extends Enemy {

    @Override
    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 20;
        this.coins = 10;
        this.xp = 4;
        this.name = "Radioactive Mutant";
        
    }
    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The mutant burns the player and itself for " + this.damage + " damage");
        this.battleHp -= this.damage/2;
        return this.damage;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    
}
