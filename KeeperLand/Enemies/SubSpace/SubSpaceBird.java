package KeeperLand.Enemies.SubSpace;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class SubSpaceBird extends Enemy {
    Random r = Main.r;

    public SubSpaceBird() {
        super("Has no extra abilities, what you see is what you get!", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 8;
        this.xp = 20;
        this.name = "Sub Space Bird";
        this.battleHp = baseHp;
        this.coins = 3;
    }


}
