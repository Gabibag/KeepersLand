package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Enviorment;
import KeeperLand.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Luck extends Enviorment {
    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Clover, ItemData.Charm, ItemData.Grass);
    }

    public String getDescription() {
        return "Randomly changes your health, damage, and enemy damage. Oh and swaps enemies.";
    }

    public String getName() {
        return "Luck Land";

    }

    public int modifyPlayerDamage(int preChange) {
        // 1/3 chance of doubling damage, and 1/3 chance of halving damage

        int chance = Main.r.nextInt(3);
        if (chance == 0) {
            return preChange << 1;

        } else if (chance == 1) {

            return preChange / 2;
        } else {
            return preChange;
        }

    }

    public void playerAction(Player p, List<Enemy> enemies) {
        //1 in 5 chance of healing player for it's heal amount, 1 in 5 chance of dealing 5% of max hp as damage to itself
        int chance = Main.r.nextInt(5);
        if (chance == 0) {
            p.setBattleHp(p.getBattleHp() + p.getHealAmount());
            System.out.println("You healed for " + Colors.GREEN + p.getHealAmount() + Colors.RESET + " health!");
        } else if (chance == 1) {
            p.takeDamage((int) (p.getHp() * 0.05));
            System.out.println("You took " + Colors.RED + (int) (p.getHp() * 0.08) + Colors.RESET + " damage!");
        }
        //1 in 5 chance of removing 10% of player's damage, 1in 5 chance of adding 10% of player's damage
        chance = Main.r.nextInt(5);
        if (chance == 0) {
            p.setBattleDamage((int) (p.getBattleDamage() * 0.9));
            System.out.println("Your damage has " + Colors.RED + "decreased by 10%!" + Colors.RESET);
        } else if (chance == 1) {
            p.setBattleDamage((int) (p.getBattleDamage() * 1.1));
            System.out.println("Your damage has " + Colors.RED + "increased by 10%!" + Colors.RESET);
        }

    }

    public void turnEnd(Player p, List<Enemy> enemies) {
        //1 in 5 chance of adding a spirit in enemies
        //1 in 5 chance of adding a spirit in enemies
        int chance = Main.r.nextInt(10);
        if (enemies.size() == 0) {
            chance = 5;
        }
        if (chance == 0) {
            try {
                enemies.add(Main.allSpirits.get(Main.r.nextInt(0, Main.allSpirits.size() - 1)).getClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Colors.YELLOW + "A sprite has appeared!" + Colors.RESET);
        } //if chance is 1 turn one enemy into a random enemy in the game
        else if (chance == 1) {
            try {

                enemies.set(Main.r.nextInt(0, enemies.size()), Main.allEnemies.get(Main.r.nextInt(0, Main.allEnemies.size())).getClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Colors.YELLOW + "An enemy has changed!" + Colors.RESET);
        }
        // if chance is 2, double everything's battle health
        else if (chance == 2 && Main.r.nextInt(2) == 0) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp() << 1);
            }
            p.setBattleHp(p.getBattleHp() << 1);
            System.out.println("All health has " + Colors.GREEN + "doubled!" + Colors.RESET);
        }
        //if chance is 3 deal 20 damage to everything
        else if (chance == 3) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp() - 20);
            }
            p.takeDamage(20);
            System.out.println("Everything took " + Colors.RED + "20 damage!" + Colors.RESET);
        }
        //if chance is 4, heal everything for 20
        else if (chance == 4) {
            for (Enemy e : enemies) {
                e.setBattleHp(e.getBattleHp() + 20);
            }
            p.setBattleHp(p.getBattleHp() + 20);
            System.out.println("Everything " + Colors.GREEN + "healed for 20hp!" + Colors.RESET);
        }
        //if chance is 5, do nothing
    }

    public int modifyEnemyDamage(int preChange) {
        int chance = Main.r.nextInt(3);
        if (chance == 0) {
            return preChange << 1;
        } else if (chance == 1) {
            return preChange / 2;
        } else {
            return preChange;
        }
    }
}
