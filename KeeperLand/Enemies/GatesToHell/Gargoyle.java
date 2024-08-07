package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Gargoyle extends Enemy {

    public Gargoyle() {
        super("Might miss or deal extra damage to you!", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Gargoyle";
        this.battleHp = baseHp;
        this.coins = 3;
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //random chance to deal 20% more damage, random chance to not deal any damage
        if (Main.r.nextInt(5) == 2) {
            return (int) (damage * 1.2);
        }
        if (Main.r.nextInt(5) == 2) {
            System.out.println(name + " misses its attack.");
            return 0;
        }
        return super.Attack(p, allies);
    }
}
