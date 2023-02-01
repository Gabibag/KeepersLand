package NameHere.Enviroments;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Item;
import NameHere.Main;
import NameHere.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Luck extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList();
    }

    public String getDescription() {
        return "Randomly changes your health, damage, and enemy damage";
    }

    public String getName() {
        return "Luck Land";

    }

    public int modifyPlayerDamage(int preChange) {
        // 1/3 chance of doubling damage, and 1/3 chance of halving damage
        int chance = Main.r.nextInt(3);
        if (chance == 0) {
            System.out.println("Your damage has doubled!");
            return preChange * 2;

        } else if (chance == 1) {
            System.out.println("Your damage has halved!");
            return preChange / 2;
        } else {
            return preChange;
        }

    }

    public void playerAction(Player p) {
        //1 in 5 chance of healing player for it's heal amount, 1 in 5 chance of dealing 5% of max hp as damage to itself
        int chance = Main.r.nextInt(5);
        if (chance == 0) {
            p.setBattleHp(p.getBattleHp() + p.getHealAmount());
            System.out.println("You healed for " + p.getHealAmount() + " health!");
        } else if (chance == 1) {
            p.takeDamage((int) (p.getHp() * 0.05));
            System.out.println("You took " + (int) (p.getHp() * 0.05) + " damage!");
        }
        //1 in 5 chance of removing 10% of player's damage, 1in 5 chance of adding 10% of player's damage
        chance = Main.r.nextInt(5);
        if (chance == 0) {
            p.setDamage((int) (p.getDamage() * 0.9));
            System.out.println("Your damage has decreased by 10%!");
        } else if (chance == 1) {
            p.setDamage((int) (p.getDamage() * 1.1));
            System.out.println("Your damage has increased by 10%!");
        }
    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //1 in 5 chance of adding a spirit in enemies
        int chance = Main.r.nextInt(5);
        if (chance == 0){
            try {
                enemies.add(Main.allSpirits.get(Main.r.nextInt(0, Main.allSpirits.size() - 1)).getClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A spirit has appeared!");
        } //if chance is 1 turn one enemy into a random enemy in the game
        else if (chance == 1) {
            try {
                enemies.set(Main.r.nextInt(0, enemies.size() - 1), Main.allEnemies.get(Main.r.nextInt(0, Main.allEnemies.size() - 1)).getClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println("An enemy has changed!");
        }
        // if chance is 2, double everything's battle health
        else if (chance == 2) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp() * 2);
            }
            p.setBattleHp(p.getBattleHp() * 2);
            System.out.println("All health has doubled!");
        }
        //if chance is 3 deal 20 damage to everything
        else if (chance == 3) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp()-20);
            }
            p.takeDamage(20);
            System.out.println("Everything took 20 damage!");
        }
        //if chance is 4, heal everything for 20
        else if (chance == 4) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp()+20);
            }
            p.setBattleHp(p.getBattleHp()+20);
            System.out.println("Everything healed for 20!");
        }
        //if chance is 5, do nothing
    }

    public int modifyEnemyDamage(int preChange) {
        int chance = Main.r.nextInt(3);
        if (chance == 0) {
            return preChange * 2;
        } else if (chance == 1) {
            return preChange / 2;
        } else {
            return preChange;
        }
    }
}
