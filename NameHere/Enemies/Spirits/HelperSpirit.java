package NameHere.Enemies.Spirits;
import NameHere.Abstracts.Spirit;
import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class HelperSpirit extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Helper Spirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //remove 1 health from each item in the list allies, if it is the only element in the list, deal 100000 damage to the player
        if (allies.size() == 1) {
            p.setBattleHp(p.getBattleHp() - 100000);
            System.out.println("The Helper Spirit deals 100000 damage to the player");
        } else if (allies != (Enemy)this){
            for (Enemy target : allies) {
                target.setBattleHp(target.getBattleHp() - 1);
            }
            System.out.println("The Helper Spirit removes 1 health from each enemy in the party");
        }

        return 0;
    }
}
