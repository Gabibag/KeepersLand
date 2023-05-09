package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class DebuffSprite extends Sprite {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Debuff Sprite";
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove an action from the player
        p.setActionAmount(1);
        System.out.println("Debuff Sprite " + Colors.RED + "removes" + Colors.RESET + " an action from you");
        return 0;
    }
}
