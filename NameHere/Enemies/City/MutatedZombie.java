package NameHere.Enemies.City;

import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.AbandonedCity;

import java.util.Arrays;
import java.util.List;
public class MutatedZombie extends Enemy{

    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 2;
        this.xp = 10;
        this.name = "Mutated Zombie";
        this.coins = 4;
        this.drops = Arrays.asList(ItemData.RadiationSuit);
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    public int Attack(Player p, List<Enemy> allies) {
        this.damage++;
        System.out.println("The Mutated Zombie's radiation burns the player for " + this.damage + " damage, and it grows stronger next turn");
        return this.damage;
    }
    
}
