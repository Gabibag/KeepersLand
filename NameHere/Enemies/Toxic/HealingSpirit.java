package NameHere.Enemies.Toxic;

import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enviroments.ToxicEnv;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class HealingSpirit extends Enemy {
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
        /*Enemy target = Helper.getRandomElements(allies, 1).get(0);
        for (Enemy e : allies){
            if(e.getName().equalsIgnoreCase("angel")){
                target = e;
            }
        } //makes it so that the boss angel always gets healed
        Enemy target2 = Helper.getRandomElements(allies, 1).get(0);
        System.out.println(Colors.GREEN + "The Healing Spirit heals " + target.getName() + " and "  + target2.getName() + " for " + (int)(target.getBaseHp()*0.05) + "hp and " + (int)(target2.getBaseHp()*0.05) + "hp");
        target.setBattleHp(target.getBattleHp()+ (int) (target.getBaseHp()*0.05));
        target2.setBattleHp(target2.getBattleHp() + (int)(target.getBaseHp()*0.05));
        return 0;*/
        for(Enemy target: allies){
            target.setBattleHp(target.getBattleHp()+ (int) (target.getBaseHp()*0.1));
        }
        System.out.println(Colors.GREEN + "The Healing Spirit heals heals its party for 10% of it's hp");
        return 0;
    }
}
