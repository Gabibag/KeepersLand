package KeeperLand.Enemies.Common;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class ItemEntity extends Enemy {
    Random r = Main.r;
    private int count = 1;

    public ItemEntity() {
        super("A reanimated weapon");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 20;
        this.name = "invalid";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    public void setBaseStats(int baseHp, int damage, String ItemName) {
        this.baseHp = baseHp;
        this.damage = damage;
        this.xp = 1;
        this.name = ItemName;
        this.battleHp = baseHp;
        this.coins = 1;
    }

    @Override
    public boolean canSpawn(Player p) {

        return false; //(r.nextInt([spawnchance]) == 2)

    }
}
