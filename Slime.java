public class Slime extends Enemy{

    public Slime(int baseHp, int damage, String name) {
        super(baseHp, damage, name);
        this.baseHp = 25;
        this.damage = 2;
        this.name = "Slime";
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
