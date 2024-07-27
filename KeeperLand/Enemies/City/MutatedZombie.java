package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.ItemData;
import KeeperLand.Player;

import java.util.Collections;
import java.util.List;

public class MutatedZombie extends Enemy {

    public MutatedZombie() {
        super("Increases its damage each turn", false);
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 2;
        this.xp = 10;
        this.name = "Mutated Zombie";
        this.coins = 4;
        this.drops = Collections.singletonList(ItemData.RadiationSuit);
    }

    public int Attack(Player p, List<Enemy> allies) {
        this.damage += this.damage * 0.1 + 1;
        System.out.println("The Mutated Zombie's " + Colors.YELLOW + "radiation" + Colors.RESET + Colors.RED + " burns " + Colors.RESET + "the player and grows" + Colors.RED + " stronger" + Colors.RESET + " next turn " + "[" + Colors.RED + this.damage + Colors.RESET + "]");
        return this.damage;
    }

}
