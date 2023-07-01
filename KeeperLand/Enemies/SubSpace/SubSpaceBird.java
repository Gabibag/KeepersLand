package KeeperLand.Enemies.SubSpace;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.SubSpace;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class SubSpaceBird extends Enemy {
    Random r = Main.r;


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 8;
        this.xp = 20;
        this.name = "Sub Space Bird";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof SubSpace;

    }
}
