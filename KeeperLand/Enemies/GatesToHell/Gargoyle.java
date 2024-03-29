package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;
import java.util.Random;

public class Gargoyle extends Enemy {
    final Random r = Main.r;

    public Gargoyle() {
        super("He's a clumsy little guy, he might miss or deal extra damage to you!");
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
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //random chance to deal 20% more damage, random chance to not deal any damage
        if (r.nextInt(5) == 2) {
            return (int) (damage * 1.2);
        }
        if (r.nextInt(5) == 2) {
            System.out.println(name + " misses its attack.");
            return 0;
        }
        return super.Attack(p, allies);
    }
}
