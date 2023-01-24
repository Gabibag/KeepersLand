import java.util.*;
public class Player {
    private int money;
    private String name;
    private int hp;
    private int dmg;
    private List<Item> inventory;
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
    public Player(String name, int hp, int dmg, List<Item> inventory) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.inventory = inventory;
    }
    public Player(String name, int hp, int dmg) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
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
}
