package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.Player;
import KeeperLand.Main;

import java.util.Random;

public class Zombie extends Enemy {
    Random r = new Random();
public void setBaseStats(){
        this.baseHp = 20;
        this.damage = 4;
        this.xp = 10;
        this.name = "Zombie";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(4) == 2 && Main.currentPlace instanceof Graveyard; //(r.nextInt([spawnchance]) == 2)

    }
}
