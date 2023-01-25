package NameHere.Abstracts;
import java.util.List;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

public abstract class Enemy{
    protected int baseHp;
    protected int damage;
    protected String name;
    protected int xp;
    protected List<Item> drops;
    public String getName(){ return name;}

    public Enemy() {
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public abstract boolean canSpawn(Player p);
    public  void Attack(Player p){
        //by default, just hits for its damage
        System.out.println("A " + name + " attacks for " +damage  );
        p.setHp( p.getHp() - this.damage);
    }
}