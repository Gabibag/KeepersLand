package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Enemies.Graveyard.SkeletonHand;
import NameHere.Colors;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;

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

    }

    @Override
    public void turnEnd(Player p, List<Enemy> enemies) {
        if(Main.r.nextFloat() > 0.90f &&  !enemies.isEmpty()){
            System.out.println(Colors.RED_BRIGHT + "A hand crawls from a grave and joins the battle!");
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
