package NameHere.Enemies.Lava;
import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.LavaZone;

import java.util.*;
public class LavaSlime extends Enemy{
    public LavaSlime(){
        super();
        this.damage = 4;
        this.baseHp = this.battleHp = 20;
        this.coins = 10;
        this.dodgeRate = 1;
        this.xp = 5;
        this.name = "Lava Slime";
    }
    public void onDeath(Player p, List<Enemy> allies){
        System.out.println("The Slime splits into 3 mini slimes");
        for(int i = 0; i < 3; i++){
            allies.add(new MiniSlime());

        }
    }
    public boolean canSpawn(Player p){
        return Main.currentPlace instanceof LavaZone;
    }
}
