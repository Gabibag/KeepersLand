package NameHere.Enemies.Lava;

import NameHere.Player;
import NameHere.Abstracts.Enemy;

public class MiniSlime extends Enemy{
    public boolean canSpawn(Player p){return false;}
    public void setBaseStats(){
        this.damage = 3;
        this.baseHp = this.battleHp = 6;
        this.coins = 3;
        this.dodgeRate = 1;
        this.xp = 3;
        this.name = "Mini Lava Slime";
    }

}
