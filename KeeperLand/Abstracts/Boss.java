package KeeperLand.Abstracts;

import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

import static KeeperLand.Main.allBosses;

public abstract class Boss extends Enemy {
    protected int tokens;

    public Boss(String description) {
        super(description, false);
        if (!allBosses.contains(this) && !this.name.contains("Keeper")) {
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

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        super.onDeath(p, allies, self);
        int t = r.nextInt(1, 4);
        p.setTokens(p.getTokens() + t);
        System.out.println("You found " + Colors.CYAN + t + "◊" + Colors.RESET + " tokens");
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

