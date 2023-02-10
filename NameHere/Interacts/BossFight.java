package NameHere.Interacts;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;
import NameHere.*;
import NameHere.Enemies.Bosses.TheKeeper;
import NameHere.Enviroments.NullZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

import static NameHere.Interacts.Battle.removeDead;
import static NameHere.Interacts.Battle.updateItems;

public class BossFight extends Interactable {
    @Override
    public String getName() {
        //check if player's inventory contains the items "Healing Shard", "Glitched Shard", "Shattered Shard", "Sprite Shard", "Death Shard", "Hell Shard", "Omega Shard", "God Shard"
        //if it does, return "Boss Fight"
        //if it doesn't, return "Locked"
       if(Main.player!= null){
           ArrayList<Item> inventoryTrunk = new ArrayList<>(Main.player.getInventory());
           for (int i = inventoryTrunk.size()-1; i >=0; i--) {
               if (!inventoryTrunk.get(i).getName().contains("Shard")) {
                   inventoryTrunk.remove(inventoryTrunk.get(i));
               }
           }
            int shardCounter = 0;
               for (int j = inventoryTrunk.size()-1; j >= 0; ) {
                   Item item = inventoryTrunk.get(j);
                   for (int i = inventoryTrunk.size() - 1; i >=0; i--) {
                       //check if item1's name is the same as item's name, if it is, remove it
                       if (inventoryTrunk.get(i).getName().equals(item.getName())) {
                           inventoryTrunk.remove(inventoryTrunk.get(i));
                           j--;
                       }
                   }
                   shardCounter++;

               }
        return shardCounter >= 6 ? "Boss Fight" : "Locked";
    }
    return "Locked";
    }
    private void printHealth(List<Enemy> enemies, Player p) {
        StringBuilder Names = new StringBuilder();
        StringBuilder HpAmounts = new StringBuilder();
        StringBuilder hpBars = new StringBuilder();
        for (Enemy enemy : enemies) {
            StringBuilder nameAdd = new StringBuilder(enemy.getName());
            StringBuilder hpAdd = new StringBuilder(enemy.getBattleHp() + "hp");
            if (nameAdd.length() > hpAdd.length()) {
                //find the difference
                int diff = nameAdd.length() - hpAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) {
                    hpAdd = new StringBuilder(" " + hpAdd + " ");
                }
                if (diff % 2 != 0) {
                    hpAdd.append(" ");
                }
            }
            else if (nameAdd.length() < hpAdd.length()) {
                int diff = hpAdd.length() - nameAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) {
                    nameAdd = new StringBuilder(" " + nameAdd + " ");
                }
                if (diff % 2 != 0) {
                    nameAdd.append(" ");
                }
            }
            int barLength = Math.max(nameAdd.length(), hpAdd.length());
            StringBuilder bar = new StringBuilder("[=]");
            if (!(barLength < 3)) {
                bar = new StringBuilder("[");
                for (int i = 0; i < barLength - 2; i++) {
                    double percentHp = enemy.getBattleHp() / (double) enemy.getBaseHp();
                    double barPercent = i / (double) (barLength - 2);
                    if (barPercent < percentHp) {
                        bar.append("=");
                    }
                    else {
                        bar.append(" ");
                    }
                }
                bar.append("]");
            }
            hpBars.append(bar).append("  ");
            Names.append(nameAdd).append("  ");
            HpAmounts.append(hpAdd).append("  ");
        }
        System.out.println(Colors.RED + Names );
        System.out.println(hpBars);
        System.out.println(HpAmounts + Colors.RESET);
    }
    static void updateBossItems(Enemy e, boolean battleEnd) {
        if (!battleEnd) {
            for (Item i : e.getDrops()) {
                e.setBattleHp(e.getBattleHp() + i.getHpIncr());
                e.setDamage(e.getDamage() + i.getDmgIncr());
            }
        }
        else {
            for (Item i : e.getDrops()) {
                e.setBattleHp(e.getBattleHp() - i.getHpIncr());
                e.setDamage(e.getDamage() - i.getDmgIncr());

            }
        }
    }
    @Override
    public void onChoose(Player p) { //yeah same exact thing. Just some sliiiight tweaks.
        if(getName().equalsIgnoreCase("Locked")){
            System.out.println("You need all 6 shards to fight enter this area.");
            Helper.contiuePrompt();
            return;
        }
        int tempMaxHp = p.getHp();
        for (Item i : p.getInventory()) {
            tempMaxHp += i.getHpIncr();
        }
        p.setBattleHp(p.getHp());

        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new TheKeeper());

        //tell the user that all their items (except those with shards in their name) have been taken from them and given to the boss. then do so
        System.out.println("You have entered the boss fight. All your items have been taken from you and given to the boss.\nIf you die, you will lose 20% of them.");
        for (int i =  p.getInventory().size()-1; i >=0; i--) {
            if (!p.getInventory().get(i).getName().toLowerCase().contains("shard")) {
                enemies.get(0).addDrops(p.getInventory().get(i));
                p.removeInventory(p.getInventory().get(i));
            }
        }
        Helper.contiuePrompt();
        updateItems(p, 3);

        Main.currentPlace = new NullZone();
        System.out.println(Colors.CLEAR);
        try {
            ((Boss) (enemies.get(0))).bossOnSpawn(enemies);
        } catch (Exception e) {
            //do nothing
        }
        while (enemies.size() > 0) {
            removeDead(enemies);
            //tell user their stage number and environment

            while (Actions > 0) {
                updateBossItems(enemies.get(0), true);
                updateBossItems(enemies.get(0), false);

                //TODO: add a check if the health exceeds the text length of the char so the names spread out
                printHealth(enemies, p);
               // updateItems(p, 3);
                System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Heal");
                System.out.println("[3] Info");
                System.out.println("[4] Inventory" + Colors.RESET);
                int choice = Helper.getInput(Colors.RESET + "Current Health: " + p.getBattleHp(), 4);
                switch (choice) {
                    //#region case1
                    case 1 -> {//attack
                        System.out.println(Colors.CLEAR);
                        if (enemies.size() > 1) {
                            for (int i = 0; i < enemies.size(); i++) {
                                System.out.println(Colors.PURPLE + "[" + (i + 1) + "] " + enemies.get(i).getName());
                                System.out.print(Colors.RESET);
                            }
                            choice = Helper.getInput("\nPlayer " + p.getBattleHp() + "hp: ", enemies.size());
                        }
                        System.out.println(Colors.CLEAR);
                        if (r.nextInt(25 / enemies.get(choice - 1).getDodgeRate()) != 0) {
                            int pDamage = Main.currentPlace.modifyPlayerDamage(p.getBattleDamage());
                            if (((enemies.get(choice - 1).getType().equals("The Keeper"))||(enemies.get(choice - 1).getType().equals("The Keeper (Final Stage)"))) && (enemies.size() != 1) &&
                                !Objects.equals(p.getName(), "btest")) {
                                System.out.println("You must kill everything else before you can attack the boss");
                                Actions++;
                            }
                            else {
                                enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() - pDamage);
                                System.out.println("Dealt " + Colors.RED_BOLD + pDamage + Colors.RESET + " damage to " +
                                                   enemies.get(choice - 1).getName());

                            }
                            //have the boss take a random item from its drops and give it to the player's inventory each time they atatck

                            if (enemies.get(choice - 1).getBattleHp() <= 0) {
                                enemies.get(choice - 1).onDeath(p, enemies, enemies.get(choice - 1));
                            }
                            Helper.contiuePrompt();
                        }
                        else {
                            System.out.println(enemies.get(choice - 1).getName() + " dodged your attack!");
                            Helper.Sleep(1);
                        }
                    }

                    case 2 -> {
                        try {
                            healPlayer(p, tempMaxHp, r);
                        } catch (Exception e) {
                            System.out.println("You are at your max health");
                        }
                        Helper.Sleep(0.4);
                    }

                    case 3 -> {
                        Battle.inv(enemies);
                        continue;
                    }
                    case 4 -> {
                        Inventory a = new Inventory();
                        a.onChoose(p);
                    }
                }
                Main.currentPlace.playerAction(p);
                if (enemies.size() > 0) {
                    Actions--;
                }
                else {
                    p.setActionAmount(2);
                    break;
                }
                System.out.println(Colors.CLEAR);
            }

            System.out.println(Colors.CLEAR + Colors.RED);
            int damage;
            for (Enemy enemy : enemies) {
                damage = enemy.Attack(p, enemies);
                p.takeDamage(damage);
            }
            Helper.contiuePrompt();
            if (p.getBattleHp() <= 0) {
                System.out.println("You lost!");
                //drop 80% of the items in drops for enemy
                for (Enemy enemy : enemies) {
                    for (int i = enemy.getDrops().size()-1; i >=0; i--) {
                        if (r.nextInt(0, 10) > 2) {
                            p.addInventory(enemy.getDrops().get(i));
                        }
                        enemy.getDrops().remove(i);
                    }
                }
                for (int i = p.getInventory().size()-1; i >=0; i--) {
                    if (p.getInventory().get(i).getName().equalsIgnoreCase("KeeperCrystal")) {
                        p.getInventory().remove(i);
                    }
                }

                IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                        enemies::remove); //the magic of intellij
                Helper.Sleep(1);
            }
            System.out.println(Colors.RESET + Colors.CLEAR);
            Main.currentPlace.turnEnd(p, enemies);
            Actions = p.getActionAmount();
        }
        if (p.getBattleHp() > 0) {
            //TODO drops
            System.out.println("You won!");
            p.incStageNum(1);

        }
        updateItems(p, 2);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Helper.Sleep(1);
    }

    static void healPlayer(Player p, int tempMaxHp, Random r) {
        int healAmount =
                (int)((p.getHealAmount()) /
                      (((double) p.getBattleHp() / (double) tempMaxHp < 0.5) ? 1 : ((double) p.getBattleHp() / (double) tempMaxHp)));
        healAmount += r.nextInt((p.getHealVariance() << 1)) - p.getHealVariance();
        if (healAmount < 0) {
            healAmount = 0;
        }
        else if (healAmount + p.getBattleHp() >= tempMaxHp) {
            healAmount = tempMaxHp - p.getBattleHp();
        }
        p.setBattleHp(p.getBattleHp() + healAmount);
        if (healAmount == 0){
            System.out.println("Your heal variance negated your heal..."); //occurs when heal variance is large enough in a negative value
        }else {
            System.out.print(Colors.RED + ((healAmount + p.getBattleHp() ==
                                            tempMaxHp) ? "You healed to full health" :
                    "You healed " + healAmount + " health"));
        }
    }

}
