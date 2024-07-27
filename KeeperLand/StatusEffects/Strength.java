package KeeperLand.StatusEffects;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.StatusEffects;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;


public class Strength extends StatusEffects {
    private int initialDamage;


    public Strength(String col, int duration, boolean isBad) {
        super(Colors.RED, 5, false, "strength");
    }

    @Override
    public void tickEffect(Player p, Enemy e, List<Enemy> enemies, String turnType, int num) {
        //each tick increases damage by 5%, if the duration is 1, then reset the damage
        if (turnType.equals("turnEnd")) {
            this.duration--;
        }
        if (duration == 5) {
            initialDamage = p.getDamage();
        } else if (duration == 0) {
            p.setDamage(initialDamage);
            super.tickEffect(p, e, enemies, turnType, num);
            return;
        }
        if (turnType.equals("playerHeal")) {
            p.setDamage((int) (p.getDamage() - 0.05 * p.getDamage()));
            System.out.printf("Your strength effect reduces your damage by 5%% (%d->%d) due to your healing%n", p.getDamage(), (int) (p.getDamage() - 0.05 * p.getDamage()));
        } else {
            p.setDamage((int) (p.getDamage() + 0.05 * p.getDamage()));
        }
        super.tickEffect(p, e, enemies, turnType, num);
    }

}
