package KeeperLand.StatusEffects;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.StatusEffects;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

import static KeeperLand.Main.r;

public class Confusion extends StatusEffects {
    public Confusion() {
        super(Colors.CYAN, 3, true, "confusion");
    }

    @Override
    public void tickEffect(Player p, Enemy e, List<Enemy> enemies, String turnType, int num) {
        if (turnType.equals("turnEnd")) {
            this.duration--;
        }

        if (turnType.equals("playerAttack")) {
            if (r.nextBoolean()) {
                super.tickEffect(p, e, enemies, turnType, num);
                return;
            }
            System.out.println("You accidentally hit the wrong enemy!");
            e.setBattleHp(e.getBattleHp() - p.getDamage());
            //get random enemy that isn't the current enemy
            Enemy randEnemy = enemies.get((int) (Math.random() * enemies.size()));
            while (randEnemy.equals(e)) {
                randEnemy = enemies.get((int) (Math.random() * enemies.size()));
            }
            randEnemy.setBattleHp(randEnemy.getBattleHp() - p.getDamage());
        } else if (turnType.equals("playerHeal")) {
            if (r.nextBoolean()) {
                super.tickEffect(p, e, enemies, turnType, num);
                return;
            }
            System.out.println("You accidentally healed an enemy!");
            e.setBattleHp(e.getBattleHp() + p.getHealAmount());
            Enemy randEnemy = enemies.get((int) (Math.random() * enemies.size()));
            randEnemy.setBattleHp(randEnemy.getBattleHp() + p.getHealAmount());
            p.setBattleHp(p.getBattleHp() - num);
        }

        super.tickEffect(p, e, enemies, turnType, num);

    }
}
