package NameHere.Enemies.Toxic;

import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enviroments.ToxicEnv;
import NameHere.Helper;
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
        return Main.currentPlace instanceof ToxicEnv&&(p.getStageNum() % 10 != 0);
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        Enemy target = Helper.getRandomElements(allies, 1).get(0);
        System.out.println(Colors.GREEN + "The Healing Spirit heals " + target.getName() + " for 3 Hp");
        target.setBattleHp(target.getBattleHp() + 3);
        return 0;
    }
}
