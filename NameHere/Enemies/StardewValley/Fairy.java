package NameHere.Enemies.StardewValley;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.StardewValley;
import NameHere.Main;
import NameHere.Player;

import java.util.List;
import java.util.Random;

public class Fairy extends Enemy {
    Random r = new Random();


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
        return Main.currentPlace instanceof StardewValley&&r.nextBoolean();
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //1 in 10 chance of healing itself, or healing an ally, or dealing 1.2x damage, or delaing 0.8x damge
        int rand = r.nextInt(10);
        if (rand == 0) {
            System.out.println(name + " heals itself for " + damage + "hp! ");
            battleHp += damage;
            return 0;
        } else if (rand == 1) {
            if (allies.size() > 0) {
                int randAlly = r.nextInt(allies.size());
                System.out.println(name + " heals " + allies.get(randAlly).getName() + " for " + damage + " damage");
                allies.get(randAlly).setBattleHp(allies.get(randAlly).getBattleHp() + damage);
                return 0;
            } else {
                System.out.println(name + " deals " + damage + " damage");
                return damage;
            }
        } else if (rand == 2) {
            System.out.println(name + " deals " + (damage * 1.2) + " damage");
            return (int) (damage * 1.2);
        } else if (rand == 3) {
            System.out.println(name + " deals " + (damage * 0.8) + " damage");
            return (int) (damage * 0.8);
        } else {
            System.out.println(name + " deals " + damage + " damage");
            return damage;
        }
    }
}
