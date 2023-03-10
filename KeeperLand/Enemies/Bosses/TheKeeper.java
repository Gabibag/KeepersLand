package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.FinalBoss;
import KeeperLand.Item;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class TheKeeper extends FinalBoss {



    public void setBaseStats() {
        this.baseHp = 500;
        this.damage = 50;
        this.xp = 1200;
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
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        System.out.println("The Keeper has been defeated!");
        System.out.println("The Keeper has ascended to stage two!");
        List <Item> items = allies.get(0).getDrops();
        allies.clear();
        allies.add(new TheKeeper2());
        (allies.get(0)).setDrops(items);
        ((FinalBoss)allies.get(0)).bossOnSpawn(allies);


        //tell the user that the keeper has ascended to stage two
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        int damage =this.damage;
        //1 in 10 chance to make all items that contian the name shard in the player's inventory deal 10 damage to the player
        int counter = 0;
        int rand = Main.r.nextInt(5);
        if (rand == 1) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("shard")) {
                    counter++;
                }
            }
            System.out.println("The Keeper's eyes glow with a bright light. You feel a sharp pain in your chest.");
            return counter*10;
        }
        else if (rand==2) {
            System.out.println(name + " deals " + (damage << 1) + " damage (DOUBLE)");
            return damage << 1;
        } else if (rand == 3) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().equalsIgnoreCase("Omega Shard")) {
                    counter++;
                }
            }
            System.out.println("The shards power the Keeper. It deals " + (damage * ((counter / 2) + 1)) + " damage.");
            return damage * ((counter / 10) + 1);
        }
        else if (rand == 4) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("Shard")) {
                    counter++;
                }
            }
            if (counter == 0){
                //System.out.println("No shards left");
                return this.Attack(p, allies);
            }
            else{
                for (int i = 0; i < p.getInventory().size(); i++) {

                    Item item = p.getInventory().get(i);
                    if(item.getName().contains("Shard")){
                        p.getInventory().remove(i);
                        i--;
                        System.out.println("The " + item.getName() + " shatters in your inventory...");
                        p.setBattleHp(p.getBattleHp() - item.getHpIncr());
                        p.setDamage(p.getDamage() - item.getDmgIncr());
                        break;
                    }

                }
             //   System.out.println("A shard shatters in your inventory...");
            }
        }
        else  {
            System.out.println(name + " deals " + damage + " damage ");
            return damage;
        }
        return damage;
    }
}
