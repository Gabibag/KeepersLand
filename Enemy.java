import java.util.List;

public abstract class Enemy{
    protected int baseHp;
    protected int damage;
    protected String name;
    protected int xp;
    protected List<Item> drops;

    public Enemy(int baseHp, int damage, String name, int xp) {
        this.baseHp = baseHp;
        this.damage = damage;
        this.name = name;
        this.xp = xp;
        this.drops = null;
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public Enemy() {
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public abstract boolean canSpawn(Player p);
    public  void Attack(Player p){
        //by default, just hits for its damage
        System.out.println("A " + name + " attacks for " +damage  );
        p.setHp( p.getHp() - this.damage);
    }
}