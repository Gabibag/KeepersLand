package KeeperLand.Enemies.StardewValley;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;
import java.util.Random;

public class Cloud extends Enemy {
    final Random r = Main.r;

    public Cloud() {
        super("Sometimes deals less damage", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 20;
        this.name = "Cloud";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 2;
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //20% chance to do 20% less damage
        if (r.nextInt(5) == 2) {
            return (int) (damage * 0.8);
        }
        return damage;
    }
}
