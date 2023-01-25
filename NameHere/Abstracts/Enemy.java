package NameHere.Abstracts;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public abstract class Enemy{
    protected int baseHp;
    protected int damage;

    public String getName() {
        return name;
    }

    protected String name;
    protected int xp;
    protected List<Item> drops;

    public int getBattleHp() {
        return battleHp;
    }

    public void setBattleHp(int battleHp) {
        this.battleHp = battleHp;
    }

    protected int battleHp;

    public Enemy(int baseHp, int damage, String name, int xp) {
        this.baseHp = baseHp;
        this.damage = damage;
        this.name = name;
        this.xp = xp;
        this.drops = null;
        this.battleHp = baseHp;
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public Enemy() {
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public abstract boolean canSpawn(Player p);
    public  void Attack(Player p){
        //by default, just hits for its damage
        System.out.println(name + " deals " +damage  + " damage");
        p.setBattleHp( p.getBattleHp() - this.damage);
    }


}