package KeeperLand.StatusEffects;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.StatusEffects;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

import static KeeperLand.Main.r;

public class Healing extends StatusEffects {
    public Healing(String col, int duration, boolean isBad) {
        super(Colors.YELLOW, 5, true);
    }

    @Override
    public void tickEffect(Player p, Enemy e, List<Enemy> enemies, String turnType, int num) {
        if (turnType.equals("turnEnd")) {
            p.setBattleHp((int) (p.getHealAmount() + 0.1 * p.getBattleHp()));
            this.duration--;
        } else if (turnType.equals("playerHeal")) {
            double rand = r.nextDouble(0.1, 0.3);
            System.out.println("Your Healing effect increases your healing by " + (int) (rand * 100) + "% (" + (num + "->" + (int) (num + (num * rand))) + ")");
            p.setBattleHp((int) (p.getBattleHp() + num * rand));
        }
        super.tickEffect(p, e, enemies, turnType, num);
    }
}
