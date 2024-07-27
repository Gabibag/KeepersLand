package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class BuringMutant extends Enemy {

    public BuringMutant() {
        super("Burns itself when attacking you.", false);
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 20;
        this.coins = 10;
        this.xp = 4;
        this.name = "Radioactive Mutant";

    }

    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The mutant " + Colors.RED + "burns" + Colors.RESET + " the player and itself for " + Colors.RED + this.damage / 2 + " damage");
        this.battleHp -= this.damage / 2;
        return this.damage;
    }


}
