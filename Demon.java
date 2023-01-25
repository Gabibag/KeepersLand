import java.util.Random;

public class Demon extends Enemy{
    Random r = new Random();
    public Demon() {
        super();
        this.baseHp = 10;
        this.damage = 7;
        this.xp = 20;
        this.name = "Demon";
        this.battleHp = baseHp;
    }

    @Override
    public boolean canSpawn(Player p) {

        return (Main.currentPlace instanceof LavaEnv)&&(r.nextInt(0,5) == 2); //only spawns in lava Environments with a 20% chance

    }
}
