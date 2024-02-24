package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.Item;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Collections;
import java.util.List;

public class KeepersLand extends Environment {

    @Override
    public String getDescription() {
        return "Focus on the battle, not the environment.";
    }

    @Override
    public List<Item> getShopItems() {
        return Collections.singletonList(ItemData.empty);
    }

    @Override
    public String getName() {
        return "Keeper's Land";
    }

    @Override
    public void playerAction(Player p, List<Enemy> enemies) {
        //if the list of enemies is less than three, spawn a zombie
    }

    @Override
    public void turnEnd(Player p, List<Enemy> enemies) {
        p.setBattleHp(p.getBattleHp() + p.getStageNum());
        System.out.println("You have gained " + p.getStageNum() + " hp");
    }

    @Override
    public int modifyPlayerDamage(int preChange) {

        return preChange;
    }

    @Override
    public int modifyEnemyDamage(int preChange) {
        Player p = Main.player;
        //check if player will die from the attack. if it can, check if the damage doubles the overkill. if it does, kill the player, if not, set their health to 1
        if (p.getBattleHp() << 1 >= preChange && preChange >= p.getBattleHp()) {
            preChange = p.getBattleHp() - 1;
        }

        return preChange;
    }
}
