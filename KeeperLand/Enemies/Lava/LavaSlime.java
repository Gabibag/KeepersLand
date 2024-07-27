package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Player;

import java.util.List;

public class LavaSlime extends Enemy {
    public LavaSlime() {
        super("Splits into mini slimes on death.", false);
    }

    public void setBaseStats() {
        this.damage = 3;
        this.baseHp = this.battleHp = 8;
        this.coins = 10;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Lava Slime";
    }

    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        super.onDeath(p, allies, self);
        System.out.println("The Lava Slime splits into 3 mini slimes");
        for (int i = 0; i < 3; i++) {
            MiniSlime l = new MiniSlime();
            l.setMutate(self.getMutate());
            allies.add(l);

        }
    }

}
