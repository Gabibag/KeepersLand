package NameHere.Enemies.StardewValley;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.StardewValley;
import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class StarCreature extends Enemy {
    Random r = new Random();


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
