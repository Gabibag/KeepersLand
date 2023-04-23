package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.AbandonedCity;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Collections;
import java.util.List;
public class MutatedZombie extends Enemy{

    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 2;
        this.xp = 10;
        this.name = "Mutated Zombie";
        this.coins = 4;
        this.drops = Collections.singletonList(ItemData.RadiationSuit);
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    public int Attack(Player p, List<Enemy> allies) {
        this.damage += this.damage*0.1 + 1;
        System.out.println("The Mutated Zombie's " + Colors.YELLOW + "radiation" + Colors.RESET + Colors.RED + " burns " + Colors.RESET + "the player and grows"  + Colors.RED + " stronger"+ Colors.RESET + " next turn");
        return this.damage;
    }
    
}
