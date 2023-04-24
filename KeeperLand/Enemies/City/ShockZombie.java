package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.AbandonedCity;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class ShockZombie extends Enemy {

    @Override
    public void setBaseStats() {
        this.baseHp = 15;
        this.damage = 6;
        this.coins = 4;
        this.xp = 4;
        this.name = "Electric Mutant";

    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }

    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("The mutant " + Colors.YELLOW + "shocks " + Colors.RESET + "everything around it");
        for (Enemy e : allies) {
            if (e != this) {
                e.setBattleHp(e.getBattleHp() - (this.damage / 3));
            }

        }
        return this.damage;
    }

}
