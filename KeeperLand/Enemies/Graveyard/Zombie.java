package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class Zombie extends Enemy {
    final Random r = Main.r;

    public Zombie() {
        super("A basic monster, what you see is what you get!");
    }

    public void setBaseStats() {
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
