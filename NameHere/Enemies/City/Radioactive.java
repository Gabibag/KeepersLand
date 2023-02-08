package NameHere.Enemies.City;

import java.util.Arrays;
import java.util.List;

import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enemies.Lava.MiniSlime;
import NameHere.Enviroments.AbandonedCity;

public abstract class Radioactive extends Enemy{
    public void setBaseStats(){
        this.baseHp = 17;
        this.damage = 3;
        this.coins = 4;
        this.xp = 4;
        this.dodgeRate = 1;
        this.drops = Arrays.asList(ItemData.RadiationSuit);
        this.name = "Radioactive Slime";
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof AbandonedCity;
    }
    public int Attack(Player p, List<Enemy> allies) {
        if(Main.r.nextFloat() > 0.5f){
            System.out.println("The Radioactive Slime creates a smaller slime to join the fight");
            allies.add((new MiniRadioSlime()));
            return 0;
        }
        return super.Attack(p, allies);
    }
}
