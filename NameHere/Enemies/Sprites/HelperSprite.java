package NameHere.Enemies.Sprites;
import NameHere.Abstracts.Spirit;
import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class HelperSprite extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Helper Sprite";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 20) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove 1 health from each item in the list allies, if it is the only element in the list, deal 100000 damage to the player
        if (allies.size() == 1) {
            p.setBattleHp(p.getBattleHp() - 100000);
            System.out.println("The Helper Spirit deals 100000 damage to the player");
        } else{
            for (Enemy target : allies) {
                if (allies != (Enemy)this) {
                    target.setBattleHp(target.getBattleHp() - 1);
                }
            }
            System.out.println("The Helper Spirit removes 1 health from each enemy in the party");
        }

        return 0;
    }
}
