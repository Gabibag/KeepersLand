package KeeperLand.Interacts;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Interactable;
import KeeperLand.*;
import KeeperLand.Enemies.Bosses.TheKeeper;
import KeeperLand.Enviroments.KeepersLand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static KeeperLand.Interacts.Battle.*;

public class BossFight extends Interactable {


    @Override
    public String getName() {
        //check if player's inventory contains the items "Healing Shard", "Glitched Shard", "Shattered Shard", "Sprite Shard", "Death Shard", "Hell Shard", "Omega Shard", "God Shard"
        //if it does, return "Boss Fight"
        //if it doesn't, return "Locked"
        if (Main.player != null) {
            ArrayList<Item> inventoryTrunk = new ArrayList<>(Main.player.getInventory());
            inventoryTrunk.removeIf(item -> !item.getName().contains("Shard"));
            int shardCounter = 0;
            for (int j = inventoryTrunk.size() - 1; j >= 0; ) {
                Item item = inventoryTrunk.get(j);
                for (int i = 0; i < inventoryTrunk.size(); i++) {
                    //check if item1's name is the same as item's name, if it is, remove it
                    if (inventoryTrunk.get(i).getName().equals(item.getName())) {
                        int count = inventoryTrunk.size();
                        inventoryTrunk.removeIf(item1 -> item1.getName().equals(item.getName()) && item1 != item);
                        count -= inventoryTrunk.size();
                        j -= count;
                    }
                }
                shardCounter++;
                if (shardCounter >= 6) break;
            }
            return shardCounter == 6 ? "Boss Fight" : "Locked";
        }
        return "Locked";
    }



    @Override
    public void onChoose(Player p) { //yeah same exact thing. Just some sliiiight tweaks.
        if(getName() == "Locked") {
            System.out.println("You need 6 shards to enter the boss fight.");
            Helper.continuePrompt();
            return;
        }
        p.setBattleHp(p.getHp());
        p.setBattleDamage(p.getDamage());
        updateItems(p, 1);
        Random r = Main.r;
        int Actions = p.getActionAmount();
        List<Enemy> enemies = new ArrayList<>();
        TheKeeper boss = new TheKeeper();
        enemies.add(boss);
        boss.bossOnSpawn(enemies);
        updateItems(p, 1);
        Helper.Sleep(1);
        System.out.print(Colors.CLEAR);
        Main.currentPlace = new KeepersLand();
        whileAlive(enemies);
        battleEnd(enemies);
        //loop through player's inventory and remove Keeper Shards
        ArrayList<Item> inventoryTrunk = new ArrayList<>(Main.player.getInventory());
        inventoryTrunk.removeIf(item -> !item.getName().equalsIgnoreCase("Keeper Shard"));
        //check if the current boss is a keeper stage 1 or 2. If it is, add 80% of the enemy's drops to the player's inventory
        /*if (enemies.get(0).getName().equalsIgnoreCase("The Keeper") || enemies.get(0).getName().equalsIgnoreCase("The Keeper (Stage 2)")) {
            for (Item item : enemies.get(0).getDrops()) {
                if (r.nextInt(100) < 80) {
                    inventoryTrunk.add(item);
                    continue;
                }
                System.out.println("You didn't get " + item.getName() + "!");
                Helper.Sleep(0.2);
            }
        }*/

    }


}
