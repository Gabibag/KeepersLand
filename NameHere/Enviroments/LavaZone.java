package NameHere.Enviroments;

import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;

import java.util.*;

public class LavaZone extends Enviorment {
    int turnsTillLava = 5;
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.LavaVial, ItemData.TougherTimes, ItemData.MoltenGem, ItemData.ShatteredShard);
    }
    public String getName() {
        return "Lava Zone";
    }

    public void playerAction(Player p){

    }
    public void turnEnd(Player p, List<Enemy> enemies){
        turnsTillLava--;
        if(turnsTillLava == 0){
            System.out.println("The volcano erupts, and everyone takes 5 damage!");
            for(Enemy e : enemies){
                e.setBattleHp(-5 + e.getBattleHp());
            }
            p.setBattleHp(-5 + p.getBattleHp());
            turnsTillLava = 5;
            Helper.contiuePrompt();
            return;
        }
        System.out.println(Colors.YELLOW + "The volcano is about to erupt in " + turnsTillLava + " turns" + Colors.RESET);
    }

    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }

    public int modifyPlayerDamage(int preChange) {
        return preChange;
    }

    public String getDescription() {
        return "A large volcano surroned by pools of lava and moutans of ash. The volcano is rumbling";
    }

}
