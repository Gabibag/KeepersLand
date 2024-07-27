package KeeperLand.Abstracts;

import KeeperLand.Player;

import java.util.List;

import static KeeperLand.Main.allBosses;

public abstract class Boss extends Enemy {
    protected int tokens;

    public Boss(String description) {
        super(description, false);
        if (!allBosses.contains(this)) {
            allBosses.add((this));
        }
    }

    @Override
    public boolean isSpecial() {
        return true;
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

