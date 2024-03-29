package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public abstract class Boss extends Enemy {
    protected int tokens;


    public Boss() {
        super("A basic monster, what you see is what you get!");
        if (!Main.allBosses.contains(this))
            Main.allBosses.add((this));
    }

    @Override
    public String getType() {
        return "Boss";
    }

    public abstract void bossOnSpawn(List<Enemy> enemies);

    @Override
    public boolean isBoss() {
        return true;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int BossAttack(Player p, List<Enemy> enemies) {
        return Attack(p, enemies);
    }


}

