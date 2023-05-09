package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class BuffSprite extends Sprite {
    public void setBaseStats() {
        this.baseHp = 40;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Buff Sprite";
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        for (Enemy target : allies) {
            target.setDamage(target.getDamage() + (int) (target.getDamage() * 0.1));
        }
        System.out.println("The Buff Sprite" + Colors.GREEN + " buffs" + Colors.RESET + " it's party for" + Colors.RED + " 10% " + Colors.RESET + "of their damage");
        return 0;
    }
}
