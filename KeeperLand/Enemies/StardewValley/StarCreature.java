package KeeperLand.Enemies.StardewValley;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

import java.util.Random;

public class StarCreature extends Enemy {
    Random r = Main.r;

    public StarCreature() {
        super("Has no extra abilities, what you see is what you get!", false);
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

}
