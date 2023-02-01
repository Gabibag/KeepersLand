package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Helper;
import NameHere.Player;

public class LevelUp extends Interactable {

    @Override
    public String getName() {
        return "Level Up";
    }

    @Override
    public void onChoose(Player p) {
        if(p.getXp() >= p.getXpToLevel()) {
            while (p.getXp() >= p.getXpToLevel()) {
                p.setLevel(p.getLevel() + 1);
                p.setXp(p.getXp() - p.getXpToLevel());
                p.setXpToLevel((p.getXpToLevel() + 10));
                p.setHp((int) (p.getHp() + p.getHp()*(0.1)));
                p.setDmg((int) (p.getDmg() + p.getDmg()*(0.1)));
                p.setHealAmount((int) (p.getHealAmount() + p.getHealAmount()*(0.1)));
                System.out.println("You leveled up! You are now level " + p.getLevel() + "!");

            }
        }else {
            System.out.println("You don't have enough xp to level up!");
        }
        Helper.contiuePrompt();
    }
}
