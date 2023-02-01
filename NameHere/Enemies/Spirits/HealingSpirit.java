package NameHere.Enemies.Spirits;
import NameHere.Abstracts.Spirit;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enviroments.ToxicEnv;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class HealingSpirit extends Spirit {
    public void setBaseStats(){
        this.baseHp = 10;
        this.coins = 5;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Healing Spirit";
    }

    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof ToxicEnv;
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {

        for(Enemy target: allies){
            target.setBattleHp(target.getBattleHp()+ (int) (target.getBaseHp()*0.1));
        }
        System.out.println(Colors.GREEN + "The Healing Spirit heals heals its party for 10% of it's hp");
        return 0;
    }
}
