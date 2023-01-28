package NameHere.Enemies.Toxic;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.ToxicEnv;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class Assassin extends Enemy {
    Random r = new Random();
public void setBaseStats(){
        this.baseHp = 1;
        this.damage = 10;
        this.xp = 10;
        this.name = "Assassin";
        this.battleHp = baseHp;
        this.coins = 3;
        this.dodgeRate = 15;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(10) == 2 && Main.currentPlace instanceof ToxicEnv&&(p.getStageNum() % 10 != 0); //10% spawn chance

    }
}
