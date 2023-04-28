package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class DeathSprite extends Sprite {
    public void setBaseStats() {
        this.baseHp = 5;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Death Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove 10% of the regular player's hp
        System.out.println("The Death Sprite removes 10% of the player's hp");
        return (int) (p.getHp() * 0.1);
    }
}
