package NameHere.Interacts;
import java.util.*;

import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;

public class Battle extends Interactable{
    final int NUM_MOVES = 3;//must be updated 
    @Override public String getName(){
        return "Battle";
    } 
    @Override
    public void OnChoose(Player p) {
        int Actions = p.getActionAmount();
        List<Enemy> spawns =getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, 3);//Maybe make an amount of enemies in environment?
        System.out.println("You enter a battle against " + enemies.size() + " enemies");
        while(enemies.size() > 0){
            while(Actions > 0){
            System.out.println("Your move("+ Actions +" actions left this turn): ");
            System.out.println("[1] Attack");
            System.out.println("[2] Defend");
            System.out.println("[3] Use Item");
            int choice = Helper.getInput("Your Choice: ", NUM_MOVES);
            //TODO implement
            switch(choice){
                case 1:
                    System.out.println("Attack");
                    break;
                case 2:
                    System.out.println("defend");
                    break;
                case 3:
                    System.out.println("Open Inv");
                    break;
            }
            Actions--;
        }
        for (Enemy enemy : enemies) {
            System.out.println(enemy.getName() + " attacks!");
            enemy.Attack(p);
        }
        Actions = p.getActionAmount();
        }
    }
    public List<Enemy> getEnemies(Player p){
        List<Enemy> returned = new ArrayList<Enemy>();
        for(Enemy e: Main.allEnemies){
            if(e.canSpawn(p)){
                returned.add((e));
            }
        }
        return returned;
    }
}
