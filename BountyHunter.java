public class BountyHunter extends Enemy{

    public BountyHunter(int baseHp, int damage, String name) {
        super(baseHp, damage, name);
        this.baseHp = 20;
        this.damage = 3;
        this.name = "Bounty Hunter";
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
