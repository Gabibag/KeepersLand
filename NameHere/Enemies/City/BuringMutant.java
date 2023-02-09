package NameHere.Enemies.City;

import java.util.List;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.AbandonedCity;

public class BuringMutant extends Enemy {

    @Override
    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 8;
        this.coins = 10;
        this.xp = 4;
        this.name = "Radioactive Mutant";
        
    }
    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The muntant burns the player and itself for " + this.damage + " damage");
        this.battleHp -= this.damage;
        return this.damage;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    
}
