package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;

public class Basilisk extends Enemy {
    public Basilisk() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 4;
        this.xp = 10;
        this.name = "Basilisk";
        this.battleHp = baseHp;
        this.coins = 3;
    }


}
