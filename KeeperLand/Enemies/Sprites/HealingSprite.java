package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class HealingSprite extends Sprite {
    public HealingSprite() {
        super("Heals its friends.");
    }

    public void setBaseStats() {
        this.baseHp = 7;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Healing Sprite";
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {

        for (Enemy target : allies) {
            target.setBattleHp(target.getBattleHp() + (int) (target.getBaseHp() * 0.1));
        }
        System.out.println("The Healing Sprite heals heals its party for " + Colors.GREEN + "10% of it's hp" + Colors.RESET);
        return 0;
    }
}
