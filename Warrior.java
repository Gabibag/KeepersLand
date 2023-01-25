import java.util.Random;
public class Warrior extends Enemy{
    Random r = new Random();
    public Warrior() {
        super();
        this.baseHp = 15;
        this.damage = 5;
        this.xp = 20;
        this.name = "Warrior";
        this.battleHp = baseHp;
    }

    @Override
    public boolean canSpawn(Player p) {

        return (r.nextBoolean()||r.nextBoolean()); //25% chance of not spawning, kinda rare as it deals quite a bit damage

    }
}
