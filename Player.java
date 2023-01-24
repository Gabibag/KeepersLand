public class Player {
    private String name;
    private int hp;
    private int dmg;
    private String[] inventory;

    public Player(String name, int hp, int dmg, String[] inventory) {
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

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }
}
