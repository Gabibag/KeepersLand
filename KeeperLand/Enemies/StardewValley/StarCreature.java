package KeeperLand.Enemies.StardewValley;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.StardewValley;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class StarCreature extends Enemy {
    Random r = Main.r;

    public StarCreature() {
        super("A basic monster, what you see is what you get!");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Star Creature";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(ItemData.starDust);
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof StardewValley; //(r.nextInt([spawnchance]) == 2)

    }
}
