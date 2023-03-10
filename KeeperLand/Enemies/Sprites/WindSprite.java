package KeeperLand.Enemies.Sprites;
import KeeperLand.Abstracts.Spirit;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class WindSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 5;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Wind Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 40) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //for each enemy in the list allies, increase their dodge rate by 1
        for (Enemy target : allies) {
            target.setDodgeRate(target.getDodgeRate() + 1);
        }
        System.out.println("The Wind Spirit increases its party's " + Colors.BLACK + "dodge rate"+ Colors.RESET + " by 1");
        return 0;
    }
}
