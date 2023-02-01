package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Helper;
import NameHere.Main;
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
                p.setXpToLevel((int) (p.getXpToLevel() * 1.2));
                int z = Main.r.nextInt(0, 10);
                int y = Main.r.nextInt(0, 10);
                int x = Main.r.nextInt(0, 10);
                p.setHp((int) (p.getHp() + ((z / 10d) + 1)));
                p.setDmg((int) (p.getDmg() + ((y / 10d) + 1)));
                p.setHealAmount((int) (p.getHealAmount() + ((y / 10d) + 1)));
                System.out.println("You leveled up! You are now level " + p.getLevel() + "!");

            }
        }else {
            System.out.println("You don't have enough xp to level up!");
        }
        Helper.contiuePrompt();
    }
}
