package NameHere.Abstracts;

import NameHere.Colors;
import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemy {
    protected int baseHp;
    protected int damage;
    protected String name;

    public int getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(int dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    protected int dodgeRate = 1;
    protected int xp;
    protected List<Item> drops = new ArrayList<>();
    protected int battleHp;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    protected int coins;
    Random r = new Random();
    public Enemy() {
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getBattleHp() {
        return battleHp;
    }

    public void setBattleHp(int battleHp) {
        this.battleHp = battleHp;
    }

    public abstract boolean canSpawn(Player p);

    public void Attack(Player p) {
        //by default, just hits for its damage
        System.out.println(name + " deals " + damage + " damage");
        p.setBattleHp(p.getBattleHp() - this.damage);
    }

    public void randDrops(Player p) {
        for (Item drop : this.drops) {
            if (r.nextInt(0, drop.getRarity()) == drop.getRarity() - 1) {
                p.addInventory(drop);
                System.out.println(Colors.CYAN + "You found a " + drop.getName() + "!" + Colors.RESET);
                break;
            }
        }

    }
}