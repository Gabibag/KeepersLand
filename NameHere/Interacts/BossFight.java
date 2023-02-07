package NameHere.Interacts;
import NameHere.*;
import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;
import NameHere.Enemies.Bosses.FinalBoss;
import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;
import NameHere.*;
import NameHere.Enemies.Bosses.TheKeeper;
import NameHere.Enemies.Bosses.TheKeeper2;
import NameHere.Enviroments.NullZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import static NameHere.Interacts.Battle.*;
import static NameHere.Interacts.Battle.removeDead;
import static NameHere.Interacts.Battle.updateItems;

public class BossFight extends Interactable {
    @Override
    public String getName() {
        //check if player's inventory contains the items "Healing Shard", "Glitched Shard", "Shattered Shard", "Sprite Shard", "Death Shard", "Hell Shard", "Omega Shard", "God Shard"
        //if it does, return "Boss Fight"
        //if it doesn't, return "Locked"
       if(Main.player!= null){
        ArrayList<Item> inventoryTrunk = new ArrayList<>();
        for(Item i : Main.player.getInventory()){
            inventoryTrunk.add(i);
        }
        int shardCounter = 0;
        for (int i = 0; i < inventoryTrunk.size(); i++) {
            if (inventoryTrunk.get(i).getName().contains("Shard")) {
                shardCounter++;
            }
        }
        return shardCounter == 7 ? "Boss Fight" : "Locked";
    }
    return "Locked";
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
        int shardCounter = 0;
        for (Item i :p.getInventory()) {
            if(i.getName().contains("Shard")){
                shardCounter++;
            }
        }
        if(shardCounter < 7){
            System.out.println("You need all 7 shards to fight enter this area.");
            Helper.contiuePrompt();
            return;
        }
        int tempMaxHp = 0;
        for (Item i : p.getInventory()) {
            tempMaxHp = p.getHp() + i.getHpIncr();
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
        updateItems(p, false);
        updateItems(p, true);
        updateBossItems(enemies.get(0), true);
        updateBossItems(enemies.get(0), false);
        Main.currentPlace = new NullZone();
        System.out.println(Colors.CLEAR);
        try {
            ((Boss) (enemies.get(0))).bossOnSpawn(enemies);
        } catch (Exception e) {
            //do nothing
        }
        while (enemies.size() > 0) {
            removeDead(enemies);
            //tell user their stage number and enviorment

            while (Actions > 0) {

                updateBossItems(enemies.get(0), true);
                updateBossItems(enemies.get(0), false);
                for (Enemy enemy : enemies) {
                    System.out.print(Colors.RED + enemy.getName() + "  ");
                }

                System.out.println();
                //TODO: add a check if the health exceeds the text length of the char so the names spread out
                for (Enemy enemy : enemies) {
                    for (int i = 0; i < enemy.getName().length(); i++) {
                        if ((enemy.getName().length() <= 4)) {
                            System.out.print(" " + enemy.displayBattleHp());
                            for (int j = 0; j < enemy.getName().length() - 4; ) {
                                System.out.print(" ");
                            }
                            break;
                        }
                        else if ((enemy.getName().length() - (enemy.displayBattleHp().length() + 2)) / 2 <
                                 1) {
                            System.out.print(enemy.displayBattleHp() + " ");
                            break;
                        }
                        else if (i ==
                                 (((enemy.getName().length()) - (enemy.displayBattleHp()).length() + 2)) / 2) {
                            System.out.print(enemy.displayBattleHp());
                            i += 4;
                        }
                        System.out.print(" ");
                    }
                    System.out.print("  ");
                }
                updateItems(p, true);
                updateItems(p, false);
                System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Heal");
                System.out.println("");
                System.out.println("[3] Info" + Colors.RESET);
                int choice = Helper.getInput(Colors.RESET + "Current Health: " + p.getBattleHp(), 3);
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
                            if ((enemies.get(choice - 1).getType().equals("FinalBoss")) && (enemies.size() != 1)) {
                                System.out.println("You must kill everything else before you can attack the boss");
                                Actions++;
                            }
                            else {
                                enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() - pDamage);
                                System.out.println("Dealt " + Colors.RED_BOLD + pDamage + Colors.RESET + " damage to " +
                                                   enemies.get(choice - 1).getName());

                            }

                            //have the boss take a random item from its drops and give it to the player's inventory each time they atatck
                            KeeperDrop(p, r, enemies, choice);
                            if (enemies.get(choice - 1).getBattleHp() <= 0) {
                                enemies.get(choice - 1).onDeath(p, enemies);
                                if (enemies.get(choice - 1).getName().equals("Keeper")) {
                                    //if the keeper dies, check the bossStage located in Keeper
                                    if(((FinalBoss)enemies.get(choice - 1)).getBossStage()==1){

                                        System.out.println("The Keeper has been defeated!");
                                        enemies.add(new TheKeeper2());
                                        enemies.get(enemies.size()-1).setDrops(enemies.get(choice-1).getDrops());
                                        System.out.println("The Keeper has ascended to stage two!");
                                        ((TheKeeper)enemies.get(enemies.size()-1)).finalBossOnSpawn(enemies);
                                        enemies.remove(choice-1);
                                        //tell the user that the keeper has ascended to stage two

                                    }
                                }else{
                                    System.out.println(enemies.get(choice - 1).getName() + " has been killed!");
                                    p.addMoney(enemies.get(choice - 1).getCoins());
                                    System.out.println(
                                            "You gained " + enemies.get(choice - 1).getCoins() + Colors.CYAN + "◊" +
                                            Colors.RESET);
                                    enemies.remove(choice - 1);
                                }
                                for (int i = 0; i < enemies.get(choice-1).getDrops().size(); i++) {
                                    p.addInventory(enemies.get(choice-1).getDrops().get(i));
                                }

                            }
                            Helper.contiuePrompt();
                        }else {
                            System.out.println(enemies.get(choice - 1).getName() + " dodged your attack!");
                            Helper.Sleep(1);
                        }
                    }

                    case 2 -> {
                        try {
                            if (r.nextInt(p.getBattleHp(), tempMaxHp) == p.getBattleHp() + 1) {
                                int healAmount =
                                        p.getHealAmount() + (r.nextInt(p.getHealVariance() << 1) - p.getHealVariance());
                                if (healAmount + p.getBattleHp() >= tempMaxHp) {
                                    healAmount = tempMaxHp - p.getBattleHp();
                                }
                                p.setBattleHp(p.getBattleHp() + healAmount);
                                System.out.print(Colors.RED + ((healAmount + p.getBattleHp() ==
                                                                tempMaxHp) ? "You healed to full health" :
                                        "You healed " + healAmount + " health"));
                            }
                            else {
                                System.out.println(Colors.RED + "You failed to heal.");
                            }
                        } catch (Exception e) {
                            System.out.println("You are at your max health");
                        }
                        Helper.Sleep(0.4);
                    }

                    case 3 -> {
                        Battle.inv(enemies);
                        continue;
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
//                Helper.Sleep(enemies.size()>=4 ? 0.5 : 1);

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
        updateItems(p, true);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Helper.Sleep(1);
    }

    private static void KeeperDrop(Player p, Random r, List<Enemy> enemies, int choice) {
        if (enemies.get(choice - 1).getClass() == FinalBoss.class) {
            if (enemies.get(choice - 1).getDrops().size() > 0) {
                //drops items and prints out the names of the items
                String tempItems = "";
                for (int i = 0; i < r.nextInt(1, enemies.get(choice - 1).getDrops().size() / 2); i++) {
                    Item temp = enemies.get(choice - 1).getDrops().get(r.nextInt(enemies.get(choice - 1).getDrops().size()));
                    p.addInventory(temp);
                    if (!tempItems.contains(temp.getName())) {
                        tempItems = tempItems.concat( temp.getName() + ", ");
                    }
                    enemies.get(choice - 1).getDrops().remove(temp);
                }
                int counter = 0;
                for (int i = tempItems.length()-1; i >=0; i--) {
                    //add a check to replace the second to last comma with an and
                    //manipulating strings is hard
                    if (tempItems.charAt(i) == ','&& counter == 1) {
                        tempItems = tempItems.substring(0, i+1) + " and a " + tempItems.substring(i+2);
                        break;
                    }else if(tempItems.charAt(i) == ','){
                        counter++;
                    }
                }
                System.out.println("The Keeper dropped " + tempItems.substring(0, tempItems.length()-2) + "!");
            }
        }
    }
}
