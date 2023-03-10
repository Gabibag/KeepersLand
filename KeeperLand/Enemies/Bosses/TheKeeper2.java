package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.FinalBoss;
import KeeperLand.Colors;
import KeeperLand.Enemies.Common.ItemEntity;
import KeeperLand.Item;
import KeeperLand.Player;

import java.util.ArrayList;
import java.util.List;

public class TheKeeper2 extends FinalBoss {//stage 2 of finalBoss
    public void setBaseStats() {
        this.baseHp = 1;
        this.damage = 0;
        this.xp = 20;
        this.name = "The Keeper (Stage 2)";
        this.coins = 50;
        this.tokens = 1;
        this.bossStage = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return false; //75% spawn chance
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        allies.clear();
        System.out.println(Colors.CLEAR);
        System.out.println("You found a Mystical Crystal!");
        if (allies.size() > 0) {
            allies.subList(0, allies.size()).clear();
        }
        allies.add(new TheKeeper3());
        ((FinalBoss)allies.get(0)).bossOnSpawn(allies);
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        return super.Attack(p, allies);
    }



    @Override
    public void bossOnSpawn(List<Enemy> enemies) {
        //for drops in TheKeeper, create a new enemy with the same stats as the drop
        //then add it to the list of enemies
        List<Item> tempItems = new ArrayList<>();
        for (Item item : this.drops) {
            ItemEntity temp = new ItemEntity();
            temp.setBaseStats(item.getHpIncr()+item.getHealIncrease()+item.getCost(), item.getDmgIncr()+ item.getHealVariance(), "Animated " + item.getName());
            //if item's hpIncr is 0, don't add it to the list
            //check if the temp's base health is over 100. if it is, set it to 100.
            if (temp.getBaseHp() > 100) {
                temp.setBaseHp(100);
            }//do the same with damage but set the cap to 10
            if (temp.getDamage() > 10) {
                temp.setDamage(10);
            }
            if (temp.getBaseHp() != 0&&item.getDmgIncr()!=0) {
                enemies.add(temp);
            }
        }
        for (int mainEntity = enemies.size()-1; mainEntity >= 0; mainEntity--) {
            ItemEntity e = null;
            try {
                e = ((ItemEntity) enemies.get(mainEntity));
            } catch (Exception ex) {
                continue;
            }
            for (int j = enemies.size() - 1; j >= 0; j--) {
                if (e.getName().equals(enemies.get(j).getName()) && mainEntity != j) {
                    e.setBaseHp(enemies.get(j).getBaseHp() + e.getBaseHp());
                    enemies.remove(j);
                    mainEntity--;
                    e.setCount(e.getCount() + 1);
//                  e.setName(e.getName() + " x" + e.getCount());
                }
            }
        }
    }
}
