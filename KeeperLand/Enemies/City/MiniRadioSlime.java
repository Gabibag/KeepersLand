package KeeperLand.Enemies.City;

import KeeperLand.Abstracts.Enemy;

public class MiniRadioSlime extends Enemy {

    public MiniRadioSlime() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 5;
        this.damage = 2;
        this.coins = 0;
        this.xp = 0;
        this.name = "Mini Radioactive Slime";

    }

    @Override
    public boolean canSpawn() {
        return false;
    }

}
