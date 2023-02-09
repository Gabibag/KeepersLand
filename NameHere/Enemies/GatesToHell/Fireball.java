package NameHere.Enemies.GatesToHell;

import java.util.List;

import NameHere.Player;
import NameHere.Abstracts.Enemy;

public class Fireball extends Enemy {

    public void setBaseStats() {
        this.baseHp = 2;
        this.coins = 0;
        this.damage = 15;
        this.name = "Fireball";
        this.xp = 1;
    }
    int numTurns = 3;
    public int Attack(Player p, List<Enemy> allies) {
        if(numTurns == 0){
        System.out.println("The fireball explodes for " + this.damage + " damage");
        this.battleHp = 0;
        return this.damage;
        }
        else{
            System.out.println("THe FireBall flys towrds you.");
            numTurns--;
            return 0;
        }
        }
    @Override
    public boolean canSpawn(Player p) {
        return false;
    }
    
}
