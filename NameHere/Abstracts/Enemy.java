package NameHere.Abstracts;

import NameHere.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemy {
    protected int damage;
    protected int baseHp;
    protected String name;
    protected int dodgeRate = 1;
    protected int xp;
    protected List<Item> drops = new ArrayList<>();
    protected int battleHp;
    protected int coins;
    protected int tokens;
    Random r = new Random();

    public Enemy() {
        this.setBaseStats();
        scaleStats();
        Main.allEnemies.add((this)); //adds all enemies to a list
        this.battleHp = this.baseHp;
        this.drops.add(ItemData.OmegaShard);
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(int dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getBattleHp() {
        return battleHp;
    }
    public String displayBattleHp() {
        return battleHp + "hp";
    }
    public void setBattleHp(int battleHp) {
        this.battleHp = battleHp;
    }

    public void scaleStats() {
        this.baseHp *= Helper.getScaleFactor(0);
        this.damage *= Helper.getScaleFactor(1);
        this.coins *= Helper.getScaleFactor(0);
//        this.xp *= Helper.getScaleFactor();
    }

    public abstract void setBaseStats();

    public abstract boolean canSpawn(Player p);

    public int Attack(Player p, List<Enemy> allies) {
        //by default, just hits for its damage
        System.out.println(name + " deals " + damage + " damage");
        return damage;
    }

    public void onDeath(Player p, List<Enemy> allies) {

    }

    public void randDrops(Player p, Enemy e) {
        for (Item drop : this.drops) {
            if (r.nextInt(drop.getRarity()) == 1) {
                p.addInventory(drop);
                System.out.println(Colors.CYAN + "You found a " + drop.getName() + "!" + Colors.RESET);
                break;
            }
        }
        
        p.addMoney(e.getCoins());
        p.addXp(e.xp);
    }
}