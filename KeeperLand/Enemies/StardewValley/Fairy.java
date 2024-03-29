package KeeperLand.Enemies.StardewValley;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.StardewValley;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;
import java.util.Random;

public class Fairy extends Enemy {
    final Random r = Main.r;

    public Fairy() {
        super("Has some spells to help its friends.");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 2;
        this.xp = 25;
        this.name = "Fairy";
        this.battleHp = baseHp;
        this.coins = 5;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof StardewValley && r.nextBoolean();
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //1 in 10 chance of healing itself, or healing an ally, or dealing 1.2x damage, or delaing 0.8x damge
        int rand = r.nextInt(10);
        if (rand == 0) {
            System.out.println(name + " heals itself for " + Colors.GREEN + damage + Colors.RESET + "hp! ");
            battleHp += damage;
            return 0;
        } else if (rand == 1) {
            if (allies.size() > 0) {
                int randAlly = r.nextInt(allies.size());
                System.out.println(name + " heals " + allies.get(randAlly).getName() + " for " + Colors.GREEN + damage + Colors.RESET + " damage");
                allies.get(randAlly).setBattleHp(allies.get(randAlly).getBattleHp() + damage);
                return 0;
            } else {
                return damage;
            }
        } else if (rand == 2) {
            return (int) (damage * 1.2);
        } else if (rand == 3) {
            return (int) (damage * 0.8);
        } else {
            return damage;
        }
    }
}
