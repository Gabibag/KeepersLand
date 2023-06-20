package KeeperLand.Interacts;

import KeeperLand.*;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Interactable;
import KeeperLand.Enemies.Bosses.TheKeeper;
import KeeperLand.Enviroments.NullZone;

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
            for (int i = inventoryTrunk.size() - 1; i >= 0; i--) {
                if (!inventoryTrunk.get(i).getName().contains("Shard")) {
                    inventoryTrunk.remove(inventoryTrunk.get(i));
                }
            }
            int shardCounter = 0;
            for (int j = inventoryTrunk.size() - 1; j >= 0; ) {
                Item item = inventoryTrunk.get(j);
                for (int i = inventoryTrunk.size() - 1; i >= 0; i--) {
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



    @Override
    public void onChoose(Player p) { //yeah same exact thing. Just some sliiiight tweaks.
        p.setBattleHp(p.getHp());
        p.setBattleDamage(p.getDamage());
        updateItems(p, 1);
        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new TheKeeper());
        System.out.println(Colors.RED + "BOSS FIGHT" + Colors.RESET);
        Helper.Sleep(1);
        System.out.print(Colors.CLEAR);
        Main.currentPlace = new NullZone();
        whileAlive(enemies);
        battleEnd(enemies);
    }


}
