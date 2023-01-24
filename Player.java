public class Player {
    private String name;
    private int hp;
    private int dmg;
    private Item[] inventory;

    public Player(String name, int hp, int dmg, Item[] inventory) {
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

    public Item[] getInventory() {
        return inventory;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }
}
