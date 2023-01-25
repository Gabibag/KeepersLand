package NameHere;
import java.util.function.Function;

public class Item {
    private int dmgIncr;
    private int hpIncr;
    private String name;
    private String description;
    private int rarity;
    /*make this a number from 1-1000, for drop chance, also doubles as epic, common, etc.
    1-750  - common
    751-850 - uncommon
    851-925 - rare
    926-975 - not epic but still cool
    976-999 - super cool
    1000 - legendary
     */
    private int cost;
    public Function<Player, Void> use;
    public Item(int dmgIncr, int hpIncr, String name, String description, int rarity, int cost) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.cost = cost;
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

    //Items
    public static Item woodenSword = new Item(1,0,"Wooden Sword", "I mean it kinda helps?", 1, 10);
    public static Item warriorSword = new Item(3, 0, "Warrior's Sword", "He's not really a warrior.", 10, 125);
    public static Item bountyHunterSword = new Item(2,0,"Bounty Hunter's Sword", "Why was he hunting you? Don't ask me.", 5, 100);
    public static Item slimeShield = new Item(0,5,"Slime Shield", "Is it really a shield if it's permeable?", 5, 100);
}
