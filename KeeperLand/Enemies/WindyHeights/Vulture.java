package KeeperLand.Enemies.WindyHeights;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Vulture extends Enemy {
    Random r = Main.r;

    public Vulture() {
        super("Has no extra abilities, what you see is what you get!", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 18;
        this.damage = 7;
        this.xp = 22;
        this.name = "Vulture";
        this.battleHp = baseHp;
        this.coins = 4;
        this.dodgeRate = 5;
    }


}
