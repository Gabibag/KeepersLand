package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.LavaZone;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Demon extends Enemy {
    Random r = Main.r;

    public Demon() {
        super("A basic monster, what you see is what you get!");
    }

    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 10;
        this.xp = 20;
        this.name = "Demon";
        this.battleHp = baseHp;
        this.coins = 10;
        this.drops.add(ItemData.demonSword);
    }

    @Override
    public boolean canSpawn(Player p) {

        return (Main.currentPlace instanceof LavaZone); //only spawns in lava Environments with a 20% chance

    }
}
