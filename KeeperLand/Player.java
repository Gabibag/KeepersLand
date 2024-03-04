package KeeperLand;

import KeeperLand.Abstracts.Enemy;
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
    private int healAmount = 10;
    private int healVariance = 2;
    private int xpToLevel = 100;
    private int tokens = 0;
    private boolean isDead = false;
    private final List<StatusEffects> statusEffects = new ArrayList<>();
    private final ArrayList<Enemy> enemiesSeen = new ArrayList<>();

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
            if (f.length() > 100_000_000) System.out.println("Large save file detected, this may take a while.");
            Scanner r = new Scanner(f);
            Player p = new Player(r.nextLine(), r.nextInt(), r.nextInt(), new ArrayList<>());
            p.setMoney(r.nextInt());
            p.setActionAmount(r.nextInt());
            p.setHealVariance(r.nextInt());
            p.setHealAmount(r.nextInt());
            p.setLevel(r.nextInt());
            p.setStageNum(r.nextInt());
            p.setXp(r.nextInt());
            p.setXpToLevel(r.nextInt());
            int invSize = r.nextInt();
            r.nextLine();//idk why this is needed but item breaks if you remove it soooo
            //cuz next int doesn't move to next line
            for (int i = 0; i < invSize; i++) {
                String name = r.nextLine();
                int tier = Integer.parseInt(r.nextLine().trim());
                for (Item item : Main.allItem) {
                    if (!item.getName().equalsIgnoreCase(name)) {
                        continue;
                    }
                    Item invItem = new Item(item);
                    if (tier != 7) {
                        invItem.setTier(tier);
                        p.inventory.add(invItem);
                        break;
                    }
                    System.out.println("found tier 7 item, " + name);
                    invItem.setDmgIncr(Integer.parseInt(r.nextLine().trim()));
                    invItem.setHealIncrease(Integer.parseInt(r.nextLine().trim()));
                    invItem.setHealVariance(Integer.parseInt(r.nextLine().trim()));
                    invItem.setHpIncr(Integer.parseInt(r.nextLine().trim()));
                    invItem.setCost(Integer.parseInt(r.nextLine().trim()));
                    invItem.setTier(7);
                    System.out.println(invItem);
                    p.inventory.add(invItem);
                    break;
                }
            }

            r.close();
            return p;
        } catch (NumberFormatException e) {
            System.out.println("File is using an outdated format, please delete the file and create a new one. (Number Format Exception)");
            Helper.continuePrompt();
            return null;

        } catch (Exception e) {
            System.out.println("File not found");

            e.printStackTrace();
            return null;
        }

    }

    public static String formattedInventory(Player p) {
        List<Item> inv = p.inventory;
        StringBuilder ret = new StringBuilder("Inventory {" + inv.size() + "\n");
        for (Item i : inv) {
            ret.append(i.toString()).append("\n");
        }
        ret.append("}");
        return ret.toString();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public List<StatusEffects> getStatusEffects() {
        return statusEffects;
    }

    public void addStatusEffects(StatusEffects effect) {
        this.statusEffects.add(effect);
    }

    public void removeStatusEffects(StatusEffects effect) {
        this.statusEffects.remove(effect);
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
            f.write(this.xp + "\n");
            f.write(this.xpToLevel + "\n");
            f.write(this.inventory.size() + "\n");
            for (Item item : inventory) {
                f.write(item.getName() + "\n");
                f.write(item.getTier() + "\n");

                if (item.getTier() == 7) {
                    f.write(item.getDmgIncr() + "\n");
                    f.write(item.getHealIncr() + "\n");
                    f.write(item.getHealVarIncr() + "\n");
                    f.write(item.getHpIncr() + "\n");
                    f.write(item.getCost() + "\n");
                }

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
        tempHp += inventory.stream().mapToInt(Item::getHpIncr).sum();
        tempDmg += inventory.stream().mapToInt(Item::getDmgIncr).sum();
        tempHeal += inventory.stream().mapToInt(Item::getHealIncr).sum();
        tempHealVar += inventory.stream().mapToInt(Item::getHealVarIncr).sum();
        String c = Colors.PURPLE;
        String r = Colors.RESET;
        String s = Colors.RED;
        int maxSize = this.name.length();
        maxSize = Math.max(String.valueOf(tempHp).length(), maxSize);
        maxSize = Math.max(String.valueOf(tempDmg).length(), maxSize);
        maxSize = Math.max(String.valueOf(tempHealVar).length(), maxSize);
        maxSize = Math.max(String.valueOf(tempHeal).length(), maxSize);
        maxSize = Math.max(String.valueOf(this.level).length(), maxSize);
        maxSize = Math.max(String.valueOf(this.money).length(), maxSize);
        maxSize = Math.max(String.valueOf(this.xp).length(), maxSize);
        maxSize = Math.max(String.valueOf(this.xpToLevel).length(), maxSize);
        maxSize = Math.max(String.valueOf(this.stageNum).length(), maxSize);
        maxSize += 29;
        StringBuilder hpSpace = new StringBuilder();
        StringBuilder dmgSpace = new StringBuilder();
        StringBuilder variSpace = new StringBuilder();
        StringBuilder healSpace = new StringBuilder();
        StringBuilder levelSpace = new StringBuilder();
        StringBuilder moneySpace = new StringBuilder();
        StringBuilder xpSpace = new StringBuilder();
        StringBuilder toLevelSpace = new StringBuilder();
        StringBuilder stageSpace = new StringBuilder();
        String nameSpace = " ".repeat(Math.max(0, maxSize - this.name.length() - 4));
        hpSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(tempHp).length() - 2)));
        dmgSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(tempDmg).length() - 6)));
        variSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(tempHealVar).length() - 13)));
        healSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(tempHeal).length() - 4)));
        levelSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(level).length() - 5)));
        moneySpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(money).length() - 5)));
        xpSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(xp).length() - 2)));
        toLevelSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(xpToLevel).length() - 17)));
        stageSpace.append(" ".repeat(Math.max(0, maxSize - String.valueOf(stageNum).length() - 12)));

        String ret = c + "Name: " + r + nameSpace + this.getName() + c;
        ret += "\nHP: " + s + hpSpace + tempHp + c;
        ret += "\nDamage: " + s + dmgSpace + tempDmg + c;
        ret += "\nHeal Variance: " + Colors.YELLOW + variSpace + tempHealVar + c;
        ret += "\nHeal: " + Colors.YELLOW + healSpace + tempHeal + c;
        ret += "\nMoney: " + Colors.CYAN + moneySpace + this.money + c;
        ret += "\nLevel: " + Colors.BLUE + levelSpace + level + c;
        ret += "\nXP: " + Colors.BLUE + xpSpace + xp + c;
        ret += "\nLevel Requirement: " + Colors.RESET + toLevelSpace + xpToLevel + c;
        ret += "\nStage Number: " + Colors.RESET + stageSpace + stageNum + c;

        return ret;

    }

    public boolean hasSeenEnemy(Enemy e) {

        if (!enemiesSeen.stream().anyMatch(enemy -> enemy.getName().equals(e.getName())) && e.isSpecial()) {
            enemiesSeen.add(e);
            return false;
        }
        return true;
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
