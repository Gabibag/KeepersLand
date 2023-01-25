package NameHere;
import java.util.*;
import java.util.function.Function;
public class Player {
    private int money;
    private String name;
    private int hp;
    private int dmg;
    private List<Item> inventory;
    private int actionAmount = 2;//num of moves in a turn
    private int battleHp;
    private int xp = 0;
    private int level = 1;

    //set can be down via the get, since its a refrence type

    public int getMoney(){
        return money;
    }
    public void setMoney(int val){
        money = val;
    }
    public void addMoney(int amount){
        this.money += amount;
    }
    public void chargeMoney(int amount) {
        this.money -= amount;
    }
    public Player(String name, int hp, int dmg, List<Item> inventory){
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.inventory = inventory;
        this.battleHp = hp;

    }
    public Player(String name, int hp, int dmg) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.battleHp = hp;
    }
    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBattleHp() {
        return battleHp;
    }

    public void setBattleHp(int battleHp) {
        this.battleHp = battleHp;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
    public void setActionAmount(int amount){
        this.actionAmount = amount;
    }
    public int getActionAmount() {
        return actionAmount;
    }
}
