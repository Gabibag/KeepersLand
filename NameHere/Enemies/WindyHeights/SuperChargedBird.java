package NameHere.Enemies.WindyHeights;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.WindyHeights;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class SuperChargedBird extends Enemy{

    @Override
    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 40;
        this.coins = 10;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Super Charged Bird";
    }
    int turnsTillAttack = 3;
    @Override
    public int Attack(Player p, List<Enemy> enemies){
        if(turnsTillAttack == 0){
            turnsTillAttack = 3;
            System.out.println("The Super Charged Bird's sphere explodes, dealing " + this.damage + " damage");
            return this.damage;
        }
        else{
            System.out.println("The Super Charged Bird" + getEnding());
            //return 0;
        }
        turnsTillAttack--;
        return 0;
    }
    private String getEnding() {
        return turnsTillAttack == 3 ? " shakes, causing a glowing circle to appear in front of it" : turnsTillAttack == 2 ? "'s sphere grows even larger" : turnsTillAttack == 1 ? " stops shaking, and points the large glowing sphere at you" : "";
    }
    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof WindyHeights;
    }
    
}
