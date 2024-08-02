package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.StatusEffects;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Witch extends Enemy {

    public Witch() {
        super("Gives you a random negative potion effect.", true);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 5;
        this.xp = 10;
        this.name = "Witch";
        this.coins = 4;
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(4) == 0;

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        int rand = Main.r.nextInt(3);
        if (rand == 0) {
            StatusEffects effect = Main.allStatusEffects.get(Main.r.nextInt(Main.allStatusEffects.size()));
            //if the effect is bad, apply it to the player
            if (effect.isBad()) {
                p.addStatusEffects(effect);
                System.out.println("The " + this.name + " casted " + effect.getEffectColor() + effect.getEffectName() + Colors.RESET + " on you!");
            }
            return 0;
        }

        return super.Attack(p, allies);
    }
}
