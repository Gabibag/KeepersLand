package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.*;
import NameHere.Enemies.Graveyard.SkeletonHand;

import java.util.Arrays;
import java.util.List;

public class Graveyard extends Enviorment {

    @Override
    public String getDescription() {
        return "A spooky Graveyard full of enemies and graves \nSometimes the dead join in on ongoing battles";
    }

    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.tombStone,
                             ItemData.GraveFlower,
                             ItemData.GhostSpirit);
    }

    @Override
    public String getName() {
        return "Graveyard";
    }

    @Override
    public void playerAction(Player p) {
        //if the list of enemies is less than three, spawn a zombie
    }

    @Override
    public void turnEnd(Player p, List<Enemy> enemies) {
        if (Main.r.nextInt(10) == 1 && !enemies.isEmpty()) {
            System.out.println(Colors.RED_BRIGHT + "A hand crawls from a grave and joins the battle!" + Colors.RESET);
            enemies.add(new SkeletonHand());
        }
    }

    @Override
    public int modifyPlayerDamage(int preChange) {

        return preChange;
    }

    @Override
    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
}
