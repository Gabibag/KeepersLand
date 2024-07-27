package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class ElectricMutant extends Enemy {

    public ElectricMutant() {
        super("Deals damage to everything, including its friends.", false);
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 6;
        this.coins = 4;
        this.xp = 4;
        this.name = "Electric Mutant";

    }


    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The mutant " + Colors.YELLOW + "shocks " + Colors.RESET + "everything around it" + "[" + Colors.RED + (this.damage / 3) + Colors.RESET + "]");
        for (Enemy e : allies) {
            if (e != this) {
                e.setBattleHp(e.getBattleHp() - (this.damage / 3));
            }

        }
        return this.damage;
    }

}
