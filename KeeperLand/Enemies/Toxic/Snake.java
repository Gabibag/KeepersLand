package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Enviroments.ToxicEnv;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class Snake extends Enemy {
    public void setBaseStats(){
        this.baseHp = 15;
        this.damage = 8;
        this.xp = 10;
        this.name = "Snake";
        this.coins = 3;
    }
    public int Attack(Player p, List<Enemy> allies) {
        int dmg = (int)(p.getHp() * (8f / 100));
        System.out.println("The snake " + Colors.RED + "bites "+ Colors.RESET + "the player for 8% of their hp.");
        return dmg;
    }

    @Override
    public int getDamage() {
        return (int) (Main.player.getHp() * (8f / 100));
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof ToxicEnv; //(r.nextInt([spawnchance]) == 2)

    }
    
}
