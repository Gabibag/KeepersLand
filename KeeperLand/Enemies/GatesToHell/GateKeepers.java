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
    Random r = Main.r;

    public GateKeepers() {
        super("Steals some of your health and redistributes it to its friends.");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 5;
        this.xp = 20;
        this.name = "Gate Defenders";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        int dmg = (int) Helper.getScaleFactor(1, this.level);
        System.out.println("The Gate Defender" + Colors.RED + " steals" + Colors.RESET + " your health!");
        for (Enemy e : allies) {
            e.setBattleHp(e.getBattleHp() + dmg / 3);
        }
        p.setBattleHp(p.getBattleHp() - dmg);
        return 0;
    }
}
