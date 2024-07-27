package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Ogre extends Enemy {
    Random r = Main.r;

    public Ogre() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 40;
        this.damage = 5;
        this.xp = 10;
        this.name = "Ogre";
        this.coins = 5;
    }


}
