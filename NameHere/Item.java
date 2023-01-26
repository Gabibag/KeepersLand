package NameHere;

import java.util.function.Function;

public class Item {
    private int healIncrease = 0;
    private int dmgIncr;
    private int hpIncr;
    private int HealVariance = 0;
    private String name;
    private String description;
    private int rarity;
    /*make this a number from 1-1000, for drop chance, also doubles as epic, common, etc.
    1-10  - common
    10-20 - uncommon
    20-30 - rare
    30-40 - not epic but still cool
    40-60 - super cool
    60-100 - legendary
     */
    private int cost;
    public String toString(){
        String r =  this.getName() + ":" + "\n" + this.getDescription() + "\n" + "Damage Increase: " + this.getDmgIncr() + "\n" + "Health Increase: " + this.getHpIncr() + "\nHeal Increase: "+ this.healIncrease + "\nHeal Vairences Increase: "+ HealVariance + "\n" + "Rarity: "+ Helper.getWordRarity(this);
        return r;
    }
    public Item(int dmgIncr, int hpIncr, String name, String description, int rarity, int cost) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.cost = cost;
    }
    public int getHealVariance(){return HealVariance;}
    public void setHealVariance(int v){HealVariance = v;}
    public void setHealIncrease(int s){
        this.healIncrease = s;
    }
    public int getHealIncrease(){return this.healIncrease;}
    public static Item getWoodenSword() {
        return woodenSword;
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
    public static Item woodenSword = new Item(1,0,"Wooden Sword", "I mean it kinda helps?", 3, 10);
    public static Item warriorSword = new Item(4, 0, "Warrior's Sword", "He's not really a warrior.", 6, 20);
    public static Item bountyHunterSword = new Item(2,0,"Bounty Hunter's Sword", "Why was he hunting you? Don't ask me.", 4, 7);
    public static Item slimeShield = new Item(0,5,"Slime Shield", "Is it really a shield if it's permeable?", 6, 20);
    public static Item giantSkin = new Item(0,5,"Giant Skin", "Wait but aren't giants just large humans?", 6, 20);
    public static Item bloodStone = new Item(0,5,"Blood Stone", "Not made of blood.", 10, 80);
    public static Item demonSword = new Item(10,0,"Demon Sword", "Where did it come from?", 10, 90);
    public static Item skeletonBone = new Item(5,0,"Skeleton Bone", "Why does a ribcage deal more damage than a sword?", 7, 35);
    public static Item soul = new Item(1,20,"Soul", "I-it's just a soul why does it give you health?", 10, 90);


}
