package KeeperLand;


import java.util.ArrayList;
import java.util.List;

public class Item {
    public static List<Item> allPool = new ArrayList<>();
    private int healIncrease = 0;
    private int dmgIncr;
    private int hpIncr;
    private int HealVariance = 0;
    private String name;
    private String description;
    private int rarity;
    private int tokenCost = 0;
    /*make this a number from 1-1000, for drop chance, also doubles as epic, common, etc.
    1-10  - common
    10-20 - uncommon
    20-30 - rare
    30-40 - not epic but still cool
    40-60 - super cool
    60-100 - legendary
     */
    private int cost;

    public Item(int dmgIncr, int hpIncr, String name, String description) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;
        this.cost = (dmgIncr * 60) + (hpIncr * 25);
        if (this.cost < 50) {
            this.rarity = 2;
        } else {
            this.rarity = (this.cost / 10) + 1;
        }

        Main.allItem.add(this);
    }

    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        int cost = (dmgIncr * 60) + (hpIncr * 25) + (heal * (heal <= 2 ? 20 : (heal <= 4 ? 30 : 40))) + (healvair * 5);
        if (cost < 50) {
            this.rarity = 2;
            this.cost = cost;
        } else {
            this.rarity = (cost / 50) + 1;
            this.cost = (int) (cost * 0.99);
        }

        this.healIncrease = heal;
        this.HealVariance = healvair;
        Main.allItem.add(this);
    }

    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair, boolean isAllPool) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        int cost = (dmgIncr * 60) + (hpIncr * 25) + (heal * (heal <= 2 ? 20 : (heal <= 4 ? 30 : 40))) + (healvair * 5);
        if (cost < 50) {
            this.rarity = 2;
            this.cost = cost;
        } else {
            this.rarity = (cost / 50) + 1;
            this.cost = (int) (cost * 0.99);
        }

        this.healIncrease = heal;
        this.HealVariance = healvair;
        if (isAllPool) {
            allPool.add(this);
        } else {
            Main.allItem.add(this);
        }
    }

    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair, int dropRate, int costMultiplier) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        cost = ((dmgIncr * 60) + (hpIncr * 25) + (heal * 60) + (healvair * 15)) * costMultiplier;
        this.tokenCost = 10;
        this.rarity = dropRate;
        this.healIncrease = heal;
        this.HealVariance = healvair;
        Main.allItem.add(this);
    }


    public String toString() {

        return displayItem(this);
    }
    private static String displayItem(Item item) {
        //sort items by cost descending

        Player player = Main.player;
        int maxNameLength = item.getName().length();
        int maxColLength = 0;
        if (String.valueOf(item.getHealVariance()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHealVariance()).length();
        }
        if (String.valueOf(item.getHealIncrease()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHealIncrease()).length();
        }
        if (String.valueOf(item.getHpIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHpIncr()).length();
        }
        if (String.valueOf(item.getDmgIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getDmgIncr()).length();
        }

        try {
            StringBuilder spaceCount = new StringBuilder();
            StringBuilder variCount = new StringBuilder();
            StringBuilder hpCount = new StringBuilder();
            StringBuilder healCount = new StringBuilder();
            StringBuilder dmgCount = new StringBuilder();
            spaceCount.append(" ".repeat(Math.max(0, maxNameLength - item.getName().length() + 2)));
            variCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHealVariance()).length())));
            hpCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHpIncr()).length())));
            healCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHealIncrease()).length())));
            dmgCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getDmgIncr()).length())));
            String col = Colors.PURPLE;
            return (
                    col + item.getName() + spaceCount +
                            Colors.RED + " ⚔" + item.getDmgIncr() + dmgCount + Colors.GREEN + " ❤" + item.getHpIncr() + hpCount + Colors.YELLOW + " ✧" + item.getHealIncrease() + healCount + Colors.PURPLE + " ⚕" + item.getHealVariance() + variCount + Colors.CYAN + " ◊" + item.getCost() + Colors.RESET + " (" + item.getDescription() + ")");

        } catch (Exception e) {
            //items.add(Item.empty);
        }
        return "Error displaying item";
    }

    public int getHealVariance() {
        return HealVariance;
    }

    public void setHealVariance(int v) {
        HealVariance = v;
    }

    public int getHealIncrease() {
        return this.healIncrease;
    }

    public void setHealIncrease(int s) {
        this.healIncrease = s;
    }

    public int getDmgIncr() {
        return dmgIncr;
    }

    public void setDmgIncr(int dmgIncr) {
        this.dmgIncr = dmgIncr;
    }

    public int getHpIncr() {
        return hpIncr;
    }

    public void setHpIncr(int hpIncr) {
        this.hpIncr = hpIncr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTokenCost() {
        return tokenCost;
    }

    public void setTokenCost(int tokenCost) {
        this.tokenCost = tokenCost;
    }

    //Items


}
