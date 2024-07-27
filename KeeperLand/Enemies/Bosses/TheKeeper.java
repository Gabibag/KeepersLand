package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.FinalBoss;
import KeeperLand.*;
import KeeperLand.Mutations.None;

import java.util.List;

public class TheKeeper extends FinalBoss {


    public void setBaseStats() {
        this.baseHp = 500;
        this.damage = 100;
        this.xp = 1200;
        this.name = "Keeper";
        this.coins = 5000;
        this.tokens = 20;
        this.bossStage = 1;
    }

    @Override
    public boolean canSpawn() {
        return false; //75% spawn chance
    }

    @Override
    public void scaleStats() {
        //do nothing
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        for (Enemy e : allies) {
            if (e.getName().equalsIgnoreCase("The Keeper (Stage 2)")) {
                return;
            }
        }

        System.out.println("The Keeper has been defeated!");
        System.out.println("The Keeper has ascended to stage two!");
        Helper.continuePrompt();
        List<Item> items = allies.get(0).getDrops();
        allies.clear();
        TheKeeper2 keeper2 = new TheKeeper2();
        keeper2.setMutate(new None());
        keeper2.setDrops(items);
        allies.add(keeper2);
        (keeper2).bossOnSpawn(allies);


        //tell the user that the keeper has ascended to stage two
    }

    @Override
    public String getDodgeText() {
        return " is not affected by your attack.";
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println(Colors.CLEAR + Colors.RED);
        flashText();
        this.mutate = new None();
    }

    private void flashText() {
        for (int i = 0; i < 3; i++) {
            bossText();
            Helper.Sleep(0.5);
            System.out.println(Colors.CLEAR);
            Helper.Sleep(0.5);
        }
    }

    private void bossText() {
        System.out.println("""
                ██████╗  ██████╗ ███████╗███████╗    ███████╗██╗ ██████╗ ██╗  ██╗████████╗██╗
                ██╔══██╗██╔═══██╗██╔════╝██╔════╝    ██╔════╝██║██╔════╝ ██║  ██║╚══██╔══╝██║
                ██████╔╝██║   ██║███████╗███████╗    █████╗  ██║██║  ███╗███████║   ██║   ██║
                ██╔══██╗██║   ██║╚════██║╚════██║    ██╔══╝  ██║██║   ██║██╔══██║   ██║   ╚═╝
                ██████╔╝╚██████╔╝███████║███████║    ██║     ██║╚██████╔╝██║  ██║   ██║   ██╗
                ╚═════╝  ╚═════╝ ╚══════╝╚══════╝    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝""");

    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        int damage = this.damage;
        //1 in 10 chance to make all items that contian the name shard in the player's inventory deal 10 damage to the player
        int counter = 0;
        int rand = Main.r.nextInt(5);
        if (rand == 1) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("Shard")) {
                    counter++;
                }
            }
            System.out.println("The keeper uses the shards against you to deal " + counter * 10 + " damage.");
            return counter * 10;
        } else if (rand == 2) {
            return damage << 1;
        } else if (rand == 3) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("Shard")) {
                    counter++;
                }
            }
            System.out.println("The shards power the Keeper. It deals " + (damage * ((counter / 8) + 1)) + " damage.");
            return damage * ((counter / 8) + 1);
        } else if (rand == 4) {
            for (int i = 0; i < p.getInventory().size(); i++) {
                if (p.getInventory().get(i).getName().contains("Shard")) {
                    counter++;
                }
            }
            if (counter == 0) {
                //System.out.println("No shards left");
                return this.Attack(p, allies);
            } else {
                for (int i = 0; i < p.getInventory().size(); i++) {

                    Item item = p.getInventory().get(i);
                    if (item.getName().contains("Shard")) {
                        p.getInventory().remove(i);
                        System.out.println("The " + item.getName() + " shatters in your inventory...");
                        p.setBattleHp(p.getBattleHp() - item.getHpIncr());
                        p.setDamage(p.getDamage() - item.getDmgIncr());
                        break;
                    }

                }
                //   System.out.println("A shard shatters in your inventory...");
            }
        } else {
            System.out.println(name + " deals " + damage + " damage ");
            return damage;
        }
        return damage;
    }
}
