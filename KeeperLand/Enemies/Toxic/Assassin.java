package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

public class Assassin extends Enemy {

    public Assassin() {
        super("Dodges your attacks.", false);
    }

    public void setBaseStats() {
        this.baseHp = 1;
        this.damage = 10;
        this.xp = 10;
        this.name = "Assassin";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 10;
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(10) == 2; //10% spawn chance

    }
}
