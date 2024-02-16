package KeeperLand.Enemies.WindyHeights;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.WindyHeights;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Ninja extends Enemy {
    Random r = Main.r;

    public Ninja() {
        super("Kinda hard to hit.");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 12;
        this.damage = 12;
        this.xp = 22;
        this.name = "Ninja";
        this.battleHp = baseHp;
        this.coins = 4;
        this.dodgeRate = 5;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof WindyHeights; //(r.nextInt([spawnchance]) == 2)
    }
}
