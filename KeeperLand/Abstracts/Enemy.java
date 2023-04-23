package KeeperLand.Abstracts;

import KeeperLand.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static KeeperLand.Main.player;

public abstract class Enemy {
    protected int damage;

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    protected int baseHp;
    protected String name;
    protected int dodgeRate = 1;
    protected int xp;

    //create a get type method that returns the string "Enemy"
    public String getType() {
        return "Enemy";
    }
    public List<Item> getDrops() {

        return drops;
    }

    public ArrayList<Item> setDrops(List<Item> drops) {
        this.drops = drops;
        return null;
    }

    public void addDrops(Item drops) {
            this.drops.add(drops);
        }


    protected List<Item> drops = new ArrayList<>();
    protected int battleHp;
    protected int coins;
    protected int tokens;
    Random r = new Random();

    public Enemy() {
        this.setBaseStats();
        scaleStats();
        if(!Main.allEnemies.contains(this)){
        Main.allEnemies.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
        //to prevent errors with the list being static sized
        this.drops = new ArrayList<Item>(this.drops);
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
        this.coins *= Helper.getScaleFactor(2);
//        this.xp *= Helper.getScaleFactor();
    }

    public abstract void setBaseStats();

    public abstract boolean canSpawn(Player p);

    public int Attack(Player p, List<Enemy> allies) {
        //by default, just hits for its damage

        return damage;
    }

    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        //by default, just gives xp and money
        System.out.println("You defeated " + name + "!");
        p.addMoney(coins);
        player.addMoney(self.getCoins());
        System.out.println(
                "You gained " + self.getCoins() + Colors.CYAN + "◊" +
                Colors.RESET);
        p.addXp(xp);
        randDrops(p, this);
    }
    public void randDrops(Player p, Enemy e) {
        if (r.nextInt(1, 2) ==1) {
            for (Item drop : this.drops) {
                if (r.nextInt(drop.getRarity()) == 1) {
                    p.addInventory(drop);
                    System.out.println(Colors.CYAN + "You found a " + drop.getName() + "!" + Colors.RESET);
                    break;
                }
            }
        }else{
            for(Item drop : Item.allPool){
                if(r.nextInt(drop.getRarity()) == 1){
                    p.addInventory(drop);
                    System.out.println(Colors.CYAN + "You found a " + drop.getName() + "!" + Colors.RESET);
                    break;
                }
            }
        }

        p.addMoney(e.getCoins());
        p.addXp(e.xp);
    }
}