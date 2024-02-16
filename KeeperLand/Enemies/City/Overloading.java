package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.AbandonedCity;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Overloading extends Enemy {

    int chargeLeft = 3;

    public Overloading() {
        super("Charges up and deals a lot of damage, killing itself in the process.");
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 30;
        this.name = "OverLoaded Zombie";
        this.coins = 7;
        this.xp = 4;

    }

    public int Attack(Player p, List<Enemy> a) {
        if (chargeLeft == 0) {
            System.out.println("The OverLoaded Zombie " + Colors.RED + " explodes" + Colors.RESET + " and kills itself");
            this.battleHp = 0;
            return this.damage;
        }
        chargeLeft--;
        System.out.println("The OverLoaded Zombie " + Colors.YELLOW + "charges" + Colors.RESET + " up");
        return 0;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }

}
