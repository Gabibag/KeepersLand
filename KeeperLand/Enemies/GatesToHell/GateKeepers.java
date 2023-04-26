package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Helper;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;
import java.util.Random;

public class GateKeepers extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 0;
        this.xp = 20;
        this.name = "GateKeepers";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        int dmg = (int) Helper.getScaleFactor(1, this) * 5;
        System.out.println("The Gate Keeper" + Colors.RED + " steals" + Colors.RESET + " your health!");
        for (Enemy e : allies) {
            e.setBattleHp((int) (e.getBattleHp() + (dmg * ((allies.size()-1) / 2))));
        }
        p.setBattleHp(p.getBattleHp() - dmg * (allies.size()-1)/2);
        return 0;
    }
}
