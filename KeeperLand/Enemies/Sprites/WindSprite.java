package KeeperLand.Enemies.Sprites;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Sprite;
import KeeperLand.Colors;
import KeeperLand.Player;

import java.util.List;

public class WindSprite extends Sprite {
    public WindSprite() {
        super("Increases the dodge rate of its friends.");
    }

    public void setBaseStats() {
        this.baseHp = 5;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Wind Sprite";
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //for each enemy in the list allies, increase their dodge rate by 1
        for (Enemy target : allies) {
            target.setDodgeRate(target.getDodgeRate() + 1);
        }
        System.out.println("The Wind Sprite increases its party's " + Colors.BLACK + "dodge rate" + Colors.RESET + " by 1");
        return 0;
    }
}
