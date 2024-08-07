package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

import java.util.Random;

public class Skeleton extends Enemy {
    Random r = Main.r;

    public Skeleton() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 2;
        this.xp = 10;
        this.name = "Skeleton";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(ItemData.skeletonBone);
    }


}
