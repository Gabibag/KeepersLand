package NameHere.Enemies.WindyHeights;

import NameHere.Abstracts.Enemy;
import NameHere.Colors;
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
        return turnsTillAttack == 3 ? " begins to " + Colors.YELLOW + "glow." + Colors.RESET  : turnsTillAttack == 2 ? Colors.YELLOW + " glows" + Colors.RESET + " even brighter" : turnsTillAttack == 1 ? " is going to "+ Colors.RED + "nuke"+ Colors.RESET + " you next turn" : "";
    }
    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof WindyHeights;
    }
    
}
