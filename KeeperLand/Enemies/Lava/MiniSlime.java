package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;

public class MiniSlime extends Enemy {
    public MiniSlime() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public boolean canSpawn() {
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
