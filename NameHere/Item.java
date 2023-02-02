package NameHere;


public class Item {
    private int healIncrease = 0;
    private int dmgIncr;
    private int hpIncr;
    private int HealVariance = 0;
    private String name;
    private String description;
    private int rarity;
    private int count = 1;
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
        this.cost = (dmgIncr *30) + (hpIncr * 25);
        if (this.cost<50){
            this.rarity = 2;
        }
        else {
            this.rarity = (this.cost/100) + 1;
        }
    }
    public Item(int dmgIncr, int hpIncr, String name, String description, int heal, int healvair) {
        this.dmgIncr = dmgIncr;
        this.hpIncr = hpIncr;
        this.name = name;
        this.description = description;

        int cost = (dmgIncr * 30) + (hpIncr * 25) + (heal * 20) + (healvair * 15);
        if (cost < 50){
            this.rarity = 2;
            this.cost = cost;
        }
        else {
            this.rarity = (cost/100) + 1;
            this.cost = (int)(cost * 0.95);
        }
        this.healIncrease = heal;
        this.HealVariance = healvair;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void addCount() {
        this.count += 1;
    }

    public String toString() {
        return this.getName() + ":" + "\n" + this.getDescription() + Colors.RED + "\nDamage Increase: " +
               this.getDmgIncr() +
               "\nHealth Increase: " + this.getHpIncr() + "\nHealing Increase: " + this.healIncrease +
               "\nHeal Variance: " + this.HealVariance + "\n" + Colors.RESET + "Rarity: " +
               Helper.getWordRarity(this) + "\nAmount: " + this.getCount() + "\nCost: " + this.getCost();
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

    //Items


}
