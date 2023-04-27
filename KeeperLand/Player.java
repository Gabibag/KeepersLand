package KeeperLand;

import KeeperLand.Abstracts.StatusEffects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int stageNum;
    private int money;
    private String name;
    private int hp;
    private int dmg;
    private int battleDamage;
    private List<Item> inventory;
    private int actionAmount = 2;//num of moves in a turn
    private int battleHp;
    private int xp = 0;
    private int level = 1;
    private int healAmount = 2;
    private int healVariance = 2;
    private int xpToLevel = 100;
    private int tokens = 0;

    public List<StatusEffects> getStatusEffects() {
        return statusEffects;
    }

    public void addStatusEffects(StatusEffects effect) {
        this.statusEffects.add(effect);
    }
    public void removeStatusEffects(StatusEffects effect) {
        this.statusEffects.remove(effect);
    }

    private List<StatusEffects> statusEffects = new ArrayList<>();

    public Player(String name, int hp, int dmg, List<Item> inventory) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.battleDamage = dmg;
        this.inventory = inventory;
        this.battleHp = hp;
        this.stageNum = 1;

    }

    public Player(String name, int hp, int dmg) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.battleHp = hp;
        this.stageNum = 1;
        this.battleDamage = dmg;
    }

    public static Player loadFromFile(String file) {
        try {
            File f = new File(file);
            Scanner r = new Scanner(f);
            Player p = new Player(r.nextLine(), r.nextInt(), r.nextInt(), new ArrayList<>());
            p.setMoney(r.nextInt());
            p.setActionAmount(r.nextInt());
            p.setHealVariance(r.nextInt());
            p.setHealAmount(r.nextInt());
            p.setLevel(r.nextInt());
            p.setStageNum(r.nextInt());
            int invSize = r.nextInt();
            for (int i = 0; i < invSize; i++) {
                r.nextLine();//idk why this is needed but it breaks if you remove it soooo
                String name = r.nextLine();
                int cost = Integer.parseInt(r.nextLine());
                Item is = new Item(0, 0, name, null, 0, cost);
                is.setDmgIncr(r.nextInt());
                r.nextLine(); //again, don't ask me :)
                is.setDescription(r.nextLine().replace("*n", "\n"));
                is.setHealIncrease(r.nextInt());
                is.setHealVariance(r.nextInt());
                is.setHpIncr(r.nextInt());
                is.setRarity(r.nextInt());
                p.inventory.add(is);
            }
            r.close();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void Save(String file) {
        //File f = new File(file);
        try {
            FileWriter f = new FileWriter(file);
            f.write(this.name + "\n");
            f.write(this.hp + "\n");
            f.write(this.dmg + "\n");
            f.write(this.money + "\n");
            f.write(this.actionAmount + "\n");
            f.write(this.healVariance + "\n");
            f.write(this.healAmount + "\n");
            f.write(this.level + "\n");
            f.write(this.stageNum + "\n");
            f.write(this.inventory.size() + "\n");
            for (Item item : inventory) {
                f.write(item.getName() + "\n");
                f.write(item.getCost() + "\n");
                f.write(item.getDmgIncr() + "\n");
                f.write(item.getDescription().replace("\n", "*n") + "\n");
                f.write(item.getHealIncrease() + "\n");
                f.write(item.getHealVariance() + "\n");
                f.write(item.getHpIncr() + "\n");
                f.write(item.getRarity() + "\n");
            }
            f.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public void incStageNum(int amount) {
        this.stageNum += amount;
    }

    public void takeDamage(int a) {
        this.battleHp -= a;
    }
    //set can be down via the get, since its a refrence type

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public void addXp(int i) {
        this.xp += i;
    }

    public int getHealVariance() {
        return healVariance;
    }

    public void setHealVariance(int healVariance) {
        this.healVariance = healVariance;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int val) {
        money = val;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void chargeMoney(int amount) {
        this.money -= amount;
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

    public void setBattleHp(int hp) {
        this.battleHp = hp;
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

    public int getDamage() {
        return dmg;
    }

    public void setDamage(int dmg) {
        this.dmg = dmg;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }


    public void addInventory(Item item) {
        this.inventory.add(item);
    }

    public void removeInventory(Item item) {
        this.inventory.remove(item);
    }

    public int getActionAmount() {
        return actionAmount;
    }

    public void setActionAmount(int amount) {
        this.actionAmount = amount;
    }

    public int getXpToLevel() {
        return xpToLevel;
    }

    public void setXpToLevel(int xpToLevel) {
        this.xpToLevel = xpToLevel;
    }

    public String toString() {
        int tempHp = this.hp;
        int tempDmg = this.dmg;
        int tempHeal = this.healAmount;
        int tempHealVar = this.healVariance;
        for (Item i : inventory) {
            tempHp += i.getHpIncr();
            tempDmg += i.getDmgIncr();
            tempHeal += i.getHealIncrease();
            tempHealVar += i.getHealVariance();
        }
        return "Name: " + this.name + "\nHP: " + tempHp + "\nDamage: " + tempDmg + "\nMoney: " + this.money +
                "\nHeal Variance: " + tempHealVar + "\nHeal Amount: " + tempHeal + "\nLevel: " +
                this.level + "\nXp: " + this.xp + "\nLevel Requirement: " + this.xpToLevel + "\nStage Number: " +
                this.stageNum;

    }

    public int getBattleDamage() {
        return battleDamage;
    }

    public void setBattleDamage(int dmg) {
        this.battleDamage = dmg;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}
