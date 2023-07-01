package KeeperLand.Enemies.WindyHeights;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.WindyHeights;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Vulture extends Enemy {
    Random r = Main.r;


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

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof WindyHeights; //(r.nextInt([spawnchance]) == 2)
    }
}
