package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Skeleton extends Enemy {
    Random r = Main.r;

    public Skeleton() {
        super("A basic monster, what you see is what you get!");
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

    @Override
    public boolean canSpawn(Player p) {

        return (Main.currentPlace instanceof Graveyard);

    }
}
