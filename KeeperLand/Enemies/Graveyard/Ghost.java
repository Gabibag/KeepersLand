package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

public class Ghost extends Enemy {

    public Ghost() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 4;
        this.xp = 10;
        this.name = "Ghost";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 10;
    }

    @Override
    public boolean canSpawn() {

        return (Main.r.nextInt(10) == 2); //10% spawn chance

    }
}
