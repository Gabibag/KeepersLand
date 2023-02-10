package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public abstract class Boss extends Enemy {
    protected int tokens;

    public Boss() {
        if(!Main.allBosses.contains(this))
            Main.allBosses.add((this));
    }

    @Override
    public String getType() {
        return "Boss";
    }

    public abstract void bossOnSpawn(List<Enemy> enemies);
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

