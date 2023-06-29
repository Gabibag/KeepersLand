package KeeperLand.Enemies.Bosses;

import KeeperLand.*;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.FinalBoss;
import KeeperLand.Enemies.Common.ItemEntity;
import KeeperLand.Mutations.None;

import java.util.List;

public class TheKeeper2 extends FinalBoss {//stage 2 of finalBoss

    @Override
    public String getDodgeText() {
        return " ignores your attack.";
    }

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
        return false;
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        allies.clear();
        System.out.println(Colors.CLEAR);
        System.out.println("You found a" + Colors.YELLOW + " Keeper Shard" + Colors.RESET + "!");
        p.addInventory(new Item(ItemData.KeeperShard));
        p.setBattleHp( p.getBattleDamage() + ItemData.KeeperShard.getHpIncr());

        if (allies.size() > 0) {
            allies.subList(0, allies.size()).clear();
        }
        for (Item drop : this.getDrops()) {
            Main.player.getInventory().add(drop);
        }
        allies.add(new TheKeeper3());
        ((FinalBoss) allies.get(0)).bossOnSpawn(allies);
    }


    @Override
    public int Attack(Player p, List<Enemy> allies) {
        return super.Attack(p, allies);
    }


    @Override
    public void bossOnSpawn(List<Enemy> enemies) {
//        enemies.add(this);
        this.mutate = new None();
        //for drops in TheKeeper, create a new enemy with the same stats as the drop
        //then add it to the list of enemies
//        List<Item> tempItems = new ArrayList<>();
        List<Item> inventory = Main.player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            ItemEntity temp = new ItemEntity();
            temp.setBaseStats(item.getHpIncr() + item.getHealIncrease(), item.getDmgIncr() + item.getHealVariance(), "Animated " + item.getName());
            if (temp.getBaseHp() != 0 && item.getDmgIncr() != 0) {
                enemies.add(temp);
                Main.player.getInventory().remove(item);
                this.addDrops(item);
            }
        }
        for (int mainEntity = enemies.size() - 1; mainEntity >= 0; mainEntity--) {
            ItemEntity e;
            try { //if item is self, skip
                e = ((ItemEntity) enemies.get(mainEntity));
            } catch (Exception ex) {
                continue;
            }
            for (int j = enemies.size() - 1; j >= 0; j--) {
                if (e.getName().contains(enemies.get(j).getName()) && mainEntity != j) {
                    e.setBaseHp(enemies.get(j).getBaseHp() + e.getBaseHp());
                    e.setBattleHp(e.getBaseHp());
                    e.setDamage((int) (enemies.get(j).getDamage()*0.5 + e.getDamage()));
                    enemies.remove(j);
                    mainEntity--;
                    e.setCount(e.getCount() + 1);


                }
            }
        }
        //add the count of the item to the name
        for (Enemy e : enemies) {
            if (e instanceof ItemEntity temp) {
                if (temp.getCount() > 1) {
                    temp.setName(temp.getName() + " x" + temp.getCount());
                }
            }
        }



    }
}
