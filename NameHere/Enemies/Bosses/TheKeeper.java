package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.FinalBoss;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class TheKeeper extends FinalBoss {

    @Override
    public void finalBossOnSpawn(List<Enemy> enemies) {
      //do nothing
    }

    public void setBaseStats() {
        this.baseHp = 7500;
        this.damage = 1000;
        this.xp = 12000;
        this.name = "Keeper";
        this.coins = 5000;
        this.tokens = 100;
        this.bossStage = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return false; //75% spawn chance
    }

    @Override
    public void scaleStats() {
        //do nothing
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies) {

    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //1 in 10 chance to make all items that contian the name shard in the player's inventory deal 10 damage to the player
        int counter = 0;
        int rand = Main.r.nextInt(10);
        if (rand == 1) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("Shard")) {
                    counter++;
                }
            }
            System.out.println("The Keeper's eyes glow with a bright light. You feel a sharp pain in your chest.");
            return counter*10;
        }
        else if (rand==2) {
            System.out.println(name + " deals " + damage + " damage (DOUBLE)");
            return damage*2;
        } else if(rand == 3){
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().equalsIgnoreCase("Omega Shard")) {
                    counter++;
                }
            }
            System.out.println("The shards power the Keeper. It deals " + damage * (counter/10) + " damage.");
            return damage * (counter/10);
        }

        return damage;
    }
}
