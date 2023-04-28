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
            return ((Main.player.getXp() >= Main.player.getXpToLevel() ? Colors.GREEN : Colors.PURPLE) + "Level Up" + Colors.PURPLE);
        } catch (Exception e) {
            return ("Level Up");
        }
    }

    @Override
    public void onChoose(Player p) {
        if (p.getXp() >= p.getXpToLevel()) {
            int hp = p.getHp();
            int damage = p.getDamage();
            int healAmount = p.getHealAmount();
            while (p.getXp() >= p.getXpToLevel()) {
                p.setLevel(p.getLevel() + 1);
                p.setXp(p.getXp() - p.getXpToLevel());
                p.setXpToLevel((int) (p.getXpToLevel() + Math.max(p.getXpToLevel() * 0.1, 20)));

                p.setHp(p.getHp() + Math.max(p.getLevel() << 1, 3));
                p.setDamage((int) (p.getDamage() + Math.max(p.getLevel() * 0.5f, 2)));
                p.setHealAmount(Math.max(p.getLevel(), 1));

            }
            System.out.println("You leveled up! You are now level " + p.getLevel() + "!");
            System.out.println("Base Health: " + hp + " -> " + p.getHp());
            System.out.println("Base Damage: " + damage + " -> " + p.getDamage());
            System.out.println("Base Healing: " + healAmount + " -> " + p.getHealAmount());
        } else {
            System.out.println("You don't have enough xp to level up!");
        }
        Helper.continuePrompt();
    }
}
