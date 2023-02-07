package NameHere.Enemies.GatesToHell;

import NameHere.Abstracts.Enemy;
import NameHere.Player;

import java.util.Random;

public class GateKeepers extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 1;
        this.damage = 0;
        this.xp = 20;
        this.name = "GateKeepers";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof GatesToHell; //(r.nextInt([spawnchance]) == 2)

    }

}
