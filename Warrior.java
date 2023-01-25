import java.util.Random;
public class Warrior extends Enemy{
    Random r = new Random();
    public Warrior(int baseHp, int damage, String name) {
        super(baseHp, damage, name);
        this.baseHp = 15;
        this.damage = 5;
        this.name = "Warrior";
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextBoolean()||r.nextBoolean()); //25% chance of not spawning, kinda rare as it deals quite a bit damage

    }
}
