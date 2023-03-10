package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Colors;
import KeeperLand.Helper;
import KeeperLand.Main;
import KeeperLand.Player;

public class LevelUp extends Interactable {

    @Override
    public String getName() {
        try {
            return ((Main.player.getXp() >= Main.player.getXpToLevel()? Colors.GREEN : Colors.PURPLE) + "Level Up"  + Colors.PURPLE);
        } catch (Exception e) {
            return ("Level Up");
        }
    }

    @Override
    public void onChoose(Player p) {
        if(p.getXp() >= p.getXpToLevel()) {
            while (p.getXp() >= p.getXpToLevel()) {
                p.setLevel(p.getLevel() + 1);
                p.setXp(p.getXp() - p.getXpToLevel());
                p.setXpToLevel((int)(p.getXpToLevel() + (p.getLevel() >= 20 ? 10 : p.getXpToLevel()*0.1)));

                p.setHp((int) (p.getHp() + ((p.getHp() * (0.1)>3) ? p.getHp() * (0.1) : 3)) );
                p.setDamage((int) (p.getDamage() + ((p.getDamage() * (0.15)>4) ? p.getDamage() * (0.15) : 4) ));
                p.setHealAmount((int) (p.getHealAmount() + p.getHp()*0.07));
                System.out.println("You leveled up! You are now level " + p.getLevel() + "!");

            }
        }else {
            System.out.println("You don't have enough xp to level up!");
        }
        Helper.continuePrompt();
    }
}
