package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Player;

import javax.swing.*;

import static KeeperLand.Main.mainPanel;

public class LevelUp extends Interactable {

    @Override
    public String getName() {
        return ("Level Up");

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
                JDialog levelUp = new JDialog();
                        levelUp.add(new JLabel("<HTML><h1>You leveled up<h1/><HTML/>"));
                        levelUp.setSize(300, 300);
                        levelUp.setLocationRelativeTo(mainPanel);
                        levelUp.setVisible(true);


            }
        }else {
            JDialog levelUp = new JDialog();
            levelUp.add(new JLabel("<HTML><h1>You need more XP to level up<h1/><HTML/>"));
            levelUp.pack();
            levelUp.setLocationRelativeTo(mainPanel);
            levelUp.setLocation(mainPanel.getX(), mainPanel.getY()-levelUp.getHeight());
            levelUp.setVisible(true);
        }
    }
}
