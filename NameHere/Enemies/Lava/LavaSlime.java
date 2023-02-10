package NameHere.Enemies.Lava;
import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.LavaZone;

import java.util.*;
public class LavaSlime extends Enemy{
    public void setBaseStats(){
        this.damage = 3;
        this.baseHp = this.battleHp = 8;
        this.coins = 10;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Lava Slime";
    }
    public void onDeath(Player p, List<Enemy> allies, Enemy self){
        super.onDeath(p, allies, self);
        System.out.println("The Lava Slime splits into 3 mini slimes");
        for(int i = 0; i < 3; i++){
            allies.add(new MiniSlime());

        }
    }
    public boolean canSpawn(Player p){
        return Main.currentPlace instanceof LavaZone;
    }
}
