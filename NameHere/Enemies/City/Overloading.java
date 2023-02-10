package NameHere.Enemies.City;

import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enviroments.AbandonedCity;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

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
            System.out.println("The OverLoaded Zombie " + Colors.RED + " explodes" + Colors.RESET + " and kills itself");
            this.battleHp = 0;
            return this.damage;
        }
        chargeLeft--;
        System.out.println("The OverLoaded Zombie " + Colors.YELLOW + "charges" + Colors.RESET + " up");
        return 0;
    } 
    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    
}
