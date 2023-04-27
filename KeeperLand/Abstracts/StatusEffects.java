package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public abstract class StatusEffects { //basically does something each turn
    protected String effectColor;
    protected int duration = 0;

    public StatusEffects(String col, int duration) {
        Main.allStatusEffects.add(this);
        this.duration = duration;
        effectColor = col;
    }

    /**
     * @param p        the player
     * @param e        the enemy (can be null)
     * @param enemies  the list of enemies
     * @param turnType the type of turn (playerAttack, enemyAttack, playerHeal, turnEnd
     * @param num      is either the player's attack damage, enemy's attack damage, or the amount healed
     */
    public void tickEffect(Player p, Enemy e, List<Enemy> enemies, String turnType, int num) {
        if (this.duration == 0) {
            p.removeStatusEffects(this);
        }
    }

}
