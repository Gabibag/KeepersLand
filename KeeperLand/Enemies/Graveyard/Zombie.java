package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

public class Zombie extends Enemy {

    public Zombie() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 10;
        this.name = "Zombie";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn() {

        return Main.r.nextInt(4) == 2;

    }
}
