public abstract class Enemy{
    protected int baseHp;
    protected int damage;
    protected String name;

    public Enemy(int baseHp, int damage, String name) {
        this.baseHp = baseHp;
        this.damage = damage;
        this.name = name;
        Main.allEnemies.add((this)); //adds all enemies to a list
    }

    public abstract boolean canSpawn(Player p);
}