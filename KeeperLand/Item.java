package KeeperLand;


import java.util.ArrayList;
import java.util.List;

import static KeeperLand.Interacts.Inventory.isNot0;
import static KeeperLand.Main.r;

public class Item {
    public static final List<Item> allPool = new ArrayList<>();
    private int healIncrease = 0;
    private int dmgIncr;
    private int hpIncr;
    private int healVariance = 0;
    private String name;
    private String description;
    private int rarity;
    private int tier = 1;
    private int tokenCost = 0;
    private int count = 1;
    private final int DROP_RATE = 1;
    private int cost;

    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        this.cost = itemCost(dmgIncr, hpIncr, heal, healvair, 1);
        this.rarity = determineRarity(cost);

        this.healIncrease = heal;
        this.healVariance = healvair;
        Main.allItem.add(this);
    }

    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair, boolean isAllPool) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        this.cost = itemCost(dmgIncr, hpIncr, heal, healvair, 1);
        this.rarity = determineRarity(cost);

        this.healIncrease = heal;
        this.healVariance = healvair;
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

        this.cost = itemCost(dmgIncr, hpIncr, heal, healvair, costMultiplier);
        this.tokenCost = 10;
        this.rarity = dropRate;
        this.healIncrease = heal;
        this.healVariance = healvair;
        Main.allItem.add(this);
    }

    public Item(int dmgIncr, int hpIncr, String name, String description) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;
        this.cost = itemCost(dmgIncr, hpIncr, 0, 0, 1);

        Main.allItem.add(this);
    }

    public Item(Item i) {
        this.dmgIncr = i.getDmgIncr();
        this.hpIncr = i.getHpIncr();
        this.name = i.getName();
        this.description = i.getDescription();
        this.cost = i.getCost();
        this.rarity = i.getRarity();
        this.healIncrease = i.getHealIncr();
        this.healVariance = i.getHealVarIncr();
        this.tokenCost = i.getTokenCost();
        this.tier = i.getTier();
    }

    private void resetCount() {
        this.healIncrease /= count;
        this.dmgIncr /= count;
        this.hpIncr /= count;
        this.healVariance /= count;
        this.cost /= count;
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    public static int itemCost(int dmgIncr, int hpIncr, int heal, int healvair, int costMultiplier) {
        int c = (int) ((dmgIncr * 6) + (hpIncr * 2.5) + (heal * 6) + (healvair * 1.5) * costMultiplier * 0.9);
//        as c aproaches 2000, it gets harder to get more expensive
        return Math.max(c, 30);
    }

    private static int determineRarity(int cost) {
        if (cost < 50) {
            return 2;
        } else {
            return (cost / 50) + 1;
        }
    }

    public void setCount(int newCount) {
        if (newCount == 1) {
            resetCount();
            return;
        }
        //make sure count is 1, if not, divide all stats by current count
        if (count != 1) {
            resetCount();
        }
        this.healIncrease *= newCount;
        this.dmgIncr *= newCount;
        this.hpIncr *= newCount;
        this.healVariance *= newCount;
        this.cost *= newCount;
        this.count = newCount;
    }


    public String toString() {

        return displayItem(this);
    }

    private static String displayItem(Item item) {
        //sort items by cost descending

        Player player = Main.player;
        int maxNameLength = item.getName().length();
        int maxColLength = 8;
        if (String.valueOf(item.getHealVarIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHealVarIncr()).length();
        }
        if (String.valueOf(item.getHealIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHealIncr()).length();
        }
        if (String.valueOf(item.getHpIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getHpIncr()).length();
        }
        if (String.valueOf(item.getDmgIncr()).length() > maxColLength) {
            maxColLength = String.valueOf(item.getDmgIncr()).length();
        }


        try {
            StringBuilder variCount = new StringBuilder();
            StringBuilder hpCount = new StringBuilder();
            StringBuilder healCount = new StringBuilder();
            StringBuilder dmgCount = new StringBuilder();
            StringBuilder tierCount = new StringBuilder();
            String spaceCount = " ".repeat(Math.max(0, maxNameLength - item.getName().length() + 2));
            variCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHealVarIncr()).length())));
            hpCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHpIncr()).length())));
            healCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getHealIncr()).length())));
            dmgCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(item.getDmgIncr()).length())));
            tierCount.append(" ".repeat(Math.max(0, maxColLength - item.getStrTier().length()) + 8));
            String col = Colors.PURPLE;

            return (
                    col + item.getName() + spaceCount +
                            isNot0(Colors.RED, item.getDmgIncr()) + " ⚔" + item.getDmgIncr() + dmgCount +
                            isNot0(Colors.GREEN, item.getHpIncr()) + " ❤" + item.getHpIncr() + hpCount +
                            isNot0(Colors.YELLOW, item.getHealIncr()) + " ✧" + item.getHealIncr() + healCount +
                            isNot0(Colors.PURPLE, item.getHealVarIncr()) + " ⚕" + item.getHealVarIncr() + variCount +
                            item.getColTier() + item.getStrTier() + tierCount + Colors.CYAN + " ◊" + item.getCost());


        } catch (Exception e) {
            //items.add(Item.empty);
        }
        return "Error displaying item";
    }

    public int getHealVarIncr() {
        return healVariance;
    }

    public void setHealVariance(int v) {
        healVariance = v;
    }

    public int getHealIncr() {
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

    public int getTier() {
        return tier;
    }

    public String getStrTier() {
        String romanNumeral = getNumeral();

        return "Type " + romanNumeral;
    }

    public String getNumeral() {
        return switch (this.tier) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "Z";
            case 7 -> "Complex";
            default -> "I";
        };
    }

    public String getColTier() {
        return switch (this.tier) {
            case 2 -> Colors.GREEN;
            case 3 -> Colors.BLUE;
            case 4 -> Colors.PURPLE;
            case 5 -> Colors.YELLOW;
            case 6, 7 -> Colors.CYAN;
            default -> Colors.WHITE;
        };
    }

    public void setTier(int tier) {
        if (tier == 7) {
            return;
        }
        this.setHealIncrease((int) (this.getHealIncr() * ((tier * 0.4) + 1)));
        this.setDmgIncr((int) (this.getDmgIncr() * ((tier * 0.4) + 1)));
        this.setHpIncr((int) (this.getHpIncr() * ((tier * 0.4) + 1)));
        this.setCost((int) (this.getCost() * ((tier * 0.4) + 1)));
        this.setHealVariance((int) (this.getHealVarIncr() * ((tier * 0.7))));
        this.tier = tier;
    }


    public void createComplexItem() {
        List<Item> items = Main.player.getInventory().stream().filter(item -> item.getName().equals(this.getName())).toList();
        for (int i = items.size() - 1; i >= 0; i--) {
            Item item = items.get(i);
            if (item == this) continue;
            this.setDmgIncr(this.getDmgIncr() + item.getDmgIncr());
            this.setHpIncr(this.getHpIncr() + item.getHpIncr());
            this.setHealIncrease(this.getHealIncr() + item.getHealIncr());
            this.setHealVariance(this.getHealVarIncr() + item.getHealVarIncr());
            Main.player.getInventory().removeIf(item1 -> item1 != this);
        }
        //check if the item is in the player's inventory. If it isn't, add it
        if (!Main.player.getInventory().contains(this)) {
            Main.player.getInventory().add(this);
        }
        System.out.println("Complex item created, item " + this.getName() + " has been upgraded to tier 7. Combined " + items.size() + " items.");
    }

    /**
     * @implNote Generates a random tier 1-6. <br>
     * 1 = Type 1 50% chance<br>
     * 2 = Type 2 25% chance<br>
     * 3 = Type 3 Rare 15% chance<br>
     * 4 = Type 4 7% chance<br>
     * 5 = Type 5 2.5% chance<br>
     * 6 = Type 6 0.5% chance<br>
     */
    public Item randTier() {

        int tier = 1;
        int rand = r.nextInt(1000);
        if (rand > 700 && rand <= 900) {
            tier = 2;
        } else if (rand > 900 && rand <= 975) {
            tier = 3;
        } else if (rand > 975 && rand <= 990) {
            tier = 4;
        } else if (rand > 990 && rand <= 999) {
            tier = 5;
        } else if (rand > 999) {
            tier = 6;
        }
        Item newItem = new Item(this);
        newItem.setTier(tier);
        return newItem;
    }


    //Items


}
