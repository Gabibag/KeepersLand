package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enemies.Lava.MiniSlime;
import KeeperLand.Enviroments.AbandonedCity;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Radioactive extends Enemy {
    public Radioactive() {
        super("Spawns a Mini Radioactive Slime");
    }

    public void setBaseStats() {
        this.baseHp = 17;
        this.damage = 3;
        this.coins = 4;
        this.xp = 4;
        this.dodgeRate = 1;
        this.drops = List.of(ItemData.RadiationSuit);
        this.name = "Radioactive Slime";
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }

    public int Attack(Player p, List<Enemy> allies) {
        if (Main.r.nextFloat() > 0.5f) {
            System.out.println("The Radioactive Slime creates a smaller slime to join the fight");
            allies.add(new MiniSlime());
            return 0;
        }
        return super.Attack(p, allies);
    }
}
