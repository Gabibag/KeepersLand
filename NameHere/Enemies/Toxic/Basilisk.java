package NameHere.Enemies.Toxic;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.ToxicEnv;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class Basilisk extends Enemy {
 public void setBaseStats(){
        this.baseHp = 30;
        this.damage = 4;
        this.xp = 10;
        this.name = "Basilisk";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof ToxicEnv; //(r.nextInt([spawnchance]) == 2)

    }
}
