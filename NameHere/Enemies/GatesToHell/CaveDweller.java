package NameHere.Enemies.GatesToHell;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class CaveDweller extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 2;
        this.xp = 20;
        this.name = "Cave Dweller";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return true; //(r.nextInt([spawnchance]) == 2)

    }

}
