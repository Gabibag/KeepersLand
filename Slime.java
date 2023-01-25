public class Slime extends Enemy{

    public Slime(int baseHp, int damage, String name, int xp) {
        super(baseHp, damage, name,xp);
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
