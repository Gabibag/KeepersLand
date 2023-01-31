package NameHere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player {
    private int stageNum = 0;
    private int money;
    private String name;
    private int hp;
    private int dmg;
    private List<Item> inventory;
    private int actionAmount = 2;//num of moves in a turn
    private int battleHp;
    private int xp = 0;
    private int level = 1;
    private int healAmount = 2;
    private int healVariance = 2;
    public Player(String name, int hp, int dmg, List<Item> inventory) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
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
    }
    public static Player loadFromFile(String file){
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(file));
            int num = ByteBuffer.wrap(Arrays.copyOfRange(allBytes, 0, 4)).getInt();
            System.out.println("num " + num);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    public void Save(String file){
        try (FileOutputStream fos = new FileOutputStream(file))
        {
            fos.write(this.name.length());
            fos.write(this.name.getBytes(StandardCharsets.UTF_8));
            fos.write(this.stageNum);
            fos.write(this.actionAmount);
            fos.write(this.dmg);
            fos.write(this.money);
            System.out.println("Successfully saved to " + file);
        } catch (IOException e) {
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

    public void addInventory(Item item) {
        this.inventory.add(item);
    }

    public int getActionAmount() {
        return actionAmount;
    }

    public void setActionAmount(int amount) {
        this.actionAmount = amount;
    }
}
