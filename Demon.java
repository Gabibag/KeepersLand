import java.util.Random;

public class Demon extends Enemy{
    Random r = new Random();
    public Demon(int baseHp, int damage, String name, int xp) {
        super(baseHp, damage, name,xp);
        this.baseHp = 10;
        this.damage = 7;
        this.xp = 20;
        this.name = "Demon";
    }

    @Override
    public boolean canSpawn(Player p) {

        return (Main.currentPlace instanceof LavaEnv)&&(r.nextInt(0,5) == 2); //only spawns in lava Environments with a 20% chance

    }
}
