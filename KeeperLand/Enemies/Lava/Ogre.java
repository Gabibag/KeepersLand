package KeeperLand.Enemies.Lava;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.LavaZone;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Ogre extends Enemy {
    Random r = new Random();
public void setBaseStats(){
        this.baseHp = 40;
        this.damage = 2;
        this.xp = 10;
        this.name = "Ogre";
        this.coins = 5;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof LavaZone; //(r.nextInt([spawnchance]) == 2)

    }
}
