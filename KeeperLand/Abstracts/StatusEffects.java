package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public abstract class StatusEffects {
    protected String effectColor;
    protected String effectName;
    protected int duration = 0;
    private boolean isBad = true; //basically does something each turn


    public StatusEffects(String col, int duration, boolean isBad, String name) {
        Main.allStatusEffects.add(this);
        this.duration = duration;
        effectColor = col;
        this.isBad = isBad;
        this.effectName = name;
    }


    public boolean isBad() {
        return isBad;
    }

    public void setBad(boolean bad) {
        isBad = bad;
    }

    public String getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(String effectColor) {
        this.effectColor = effectColor;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }

    /**
     * @param p        the player
     * @param e        the enemy (can be null)
     * @param enemies  the list of enemies
     * @param turnType the type of turn (playerAttack, enemyAttack, playerHeal, turnEnd)
     * @param num      is either the player's attack damage, enemy's attack damage, or the amount healed
     * @implNote Tick duration is not decreased in super.
     */
    public void tickEffect(Player p, Enemy e, List<Enemy> enemies, String turnType, int num) {
        if (this.duration == 0) {
            p.removeStatusEffects(this);
        }
    }

}
