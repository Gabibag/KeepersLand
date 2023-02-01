package NameHere.Enemies.Spirits;
import NameHere.Abstracts.Spirit;
import NameHere.Abstracts.Enemy;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class SwapSpirit extends Spirit {
    public void setBaseStats() {
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "SwapSpirit";
    }

    public boolean canSpawn(Player p) {
        return Main.r.nextInt(1, 10) == 2;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //swap each of the allies' battle hp with eachother
        for (int i = 0; i < allies.size(); i++) {
            for (int j = 0; j < allies.size(); j++) {
                if (i != j) {
                    int temp = allies.get(i).getBattleHp();
                    allies.get(i).setBattleHp(allies.get(j).getBattleHp());
                    allies.get(j).setBattleHp(temp);
                }
            }
        }
        System.out.println("The Swap Spirit swaps the hp of each enemy in the party");
        return 0;
    }
}