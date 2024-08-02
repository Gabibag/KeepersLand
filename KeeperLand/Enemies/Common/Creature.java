package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;

public class Creature extends Enemy {

    public Creature() {
        super("Has no extra abilities, what you see is what you get!", true);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 8;
        this.xp = 20;
        this.name = "Creature";
        this.coins = 3;
    }

}
