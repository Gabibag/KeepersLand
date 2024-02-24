package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Player;

public class MiniSlime extends Enemy {
    public MiniSlime() {
        super("Has no extra abilities, what you see is what you get!");
    }

    public boolean canSpawn(Player p) {
        return false;
    }

    public void setBaseStats() {
        this.damage = 2;
        this.baseHp = this.battleHp = 2;
        this.coins = 3;
        this.dodgeRate = 1;
        this.xp = 3;
        this.name = "Mini Lava Slime";
    }

}
