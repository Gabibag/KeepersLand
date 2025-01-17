package KeeperLand;

import KeeperLand.Abstracts.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static KeeperLand.Main.*;

public class Helper {
    public static boolean speedMode = false;
    public static boolean moreShopInfo = false;
    static final Scanner s = new Scanner(System.in);
    public static final int BOSS_LEVELS = 3;

    public static void checkForComplexCreation(List<Item> complexChecks) {
        List<Item> items = player.getInventory();
        for (Item i : complexChecks) {
            if (items.stream().filter(item -> i.getName().equals(item.getName()) && item.getTier() != 7).toList().size() > 30_000) {
                i.setTier(7);
                i.createComplexItem();
            }
        }

    }

    /**
     * Dont do it
     *
     * @throws Exception skill issue
     */
    public Helper() throws Exception {
        System.out.println("skill issue detected");
        throw new Exception("Do not create objects of a helper class");
    }

    public static void Sleep(double s) {
        s = (speedMode ? 0 : s);
        try {
            TimeUnit.MILLISECONDS.sleep((long) (s * 1000));
        } catch (InterruptedException e) {
            System.out.println(Colors.RED_BOLD + "You cannot quit at this time." + Colors.RESET);
        }
    }

    public static void forceSleep(double s) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) (s * 1000));
        } catch (InterruptedException e) {
            System.out.println(Colors.RED_BOLD + "You cannot quit at this time." + Colors.RESET);
        }
    }

    /**
     * Returns the scale factor based on the level of the enemy
     *
     * @param type  0 for hp, 1 for damage, 2 for coins, Enemy e
     * @param level level of the enemy
     * @return the scale factor proportional to the level of the enemy
     */
    public static float getScaleFactor(int type, int level) {
        int multi = level / 2;

        if (level <= 3) {
            return 1;
        }
        float ret;
        switch (type) {
            case 0 -> { //hp
                ret = 1 + (multi * 0.1f);
            }
            case 1 -> { //dmg
                ret = 1 + (multi * 0.1f);
            }
            case 2 -> { //coins
                ret = (multi * 0.05f);
            }
            default -> ret = multi + 1;
        }
        return ret;
    }


    /**
     * Sends a message, then returns the next line
     *
     * @param msg what to be sent
     * @return the users next line
     */
    public static String Prompt(String msg) {
        System.out.print(Colors.PURPLE + msg + Colors.RESET);
        return s.nextLine();

    }

    public static void continuePrompt() {
        if (!speedMode) {
            System.out.print(Colors.PURPLE + "Press enter to continue" + Colors.RESET);
            s.nextLine();
        } else {
            Sleep(1);
        }
    }

    /**
     * returns an items rarity as a word from a number following this chart
     * 10- common
     * 20 - uncommon
     * 30 - rare
     * 40 - epic
     * 60 - super epic
     * 61+ - legendary
     *
     * @param i item in question
     * @return rarity as a word
     */
    public static String getWordRarity(Item i) {
        int rarity = i.getRarity();
        if (i.getName().contains("Shard")) {
            return Colors.BLUE + "Shard" + Colors.RESET;
        }
        if (rarity < 10) {
            return "common";
        } else if (rarity < 20) {
            return Colors.GREEN + " uncommon" + Colors.RESET;
        } else if (rarity < 30) {
            return Colors.RED + "rare" + Colors.RESET;
        } else if (rarity < 40) {
            return Colors.PURPLE_BOLD + "epic" + Colors.RESET;
        } else if (rarity <= 60) {
            return Colors.PURPLE_UNDERLINED + "super epic" + Colors.RESET;
        } else {
            return Colors.YELLOW_UNDERLINED + "legendary" + Colors.RESET;
        }
    }


    /**
     * gets some random elements from an list. Can get the same thing more than one time.
     *
     * @param <T>    type of objects in the array
     * @param list   the list to choose from
     * @param amount the size of the returned array
     * @return list of random entities given in list
     */
    public static <T> List<T> getRandomElements(List<T> list, int amount) {
        if (list.isEmpty()) throw new IllegalArgumentException("Provided list is empty");
        if (list.size() < amount)
            throw new IllegalArgumentException("List is too small, " + list.size() + " < " + amount + ", " + list);
        List<T> r = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            r.add(list.get(Main.r.nextInt(list.size())));
        }
        return r;
    }


    /**
     * prompts the user with a message then gets the next int they type, as long as its 1-top (inclusive both ways)
     * will keep asking until a valid input is obtained
     *
     * @param msg what to ask
     * @param top max(inclusive) val for a input
     * @return the first valid int typed
     */
    public static int getInput(String msg, int top) {
        try {
            System.out.println(msg);
            int r = Integer.parseInt(Prompt(Colors.CYAN + "Player: "));
            if (r > 0 && r <= top) {
                System.out.print(Colors.CLEAR);
                return r;
            }
            System.out.println(Colors.RED + "Not an option" + Colors.RESET);
            return getInput(msg, top);
        } catch (Exception e) {
            System.out.println(Colors.RED + "Bad input, try again" + Colors.RESET);
            return getInput(msg, top);
        }

    }

    /**
     * Similar to get input, but if no input is given, returns the default value
     * Prompts the user with a message then gets the next int they type, as long as its 1-top (inclusive both ways)
     *
     * @param msg what to ask
     * @param top max(inclusive) val for a input
     * @param def default value
     * @return the first valid int typed
     */
    public static int getInputDefault(String msg, int top, int def) {
        try {
            System.out.println(msg);
            int r = Integer.parseInt(Prompt(Colors.CYAN + "Player: "));
            if (r > 0 && r <= top) {
                System.out.print(Colors.CLEAR);
                return r;
            }
            System.out.println(Colors.RED + "Not an option" + Colors.RESET);
            return getInput(msg, top);
        } catch (Exception e) {
            System.out.println(Colors.CLEAR);
            return def;
        }

    }

    /**
     * prompts the user with a message then gets the next int they type, as long as its bottom-top (inclusive both ways)
     * will keep asking until a valid input is obtained
     *
     * @param msg    what to ask
     * @param bottom lowest valid value
     * @param top    highest valid value
     */
    public static int getInput(String msg, int bottom, int top) {
        try {
            System.out.println(msg);
            int r = Integer.parseInt(Prompt(Colors.CYAN + "Player: "));
            if (r >= bottom && r <= top) {
                System.out.print(Colors.CLEAR);
                return r;
            }
            System.out.println("Not an option");
            return getInput(msg, bottom, top);
        } catch (Exception e) {
            System.out.println("Bad input, try again");
            return getInput(msg, bottom, top);
        }

    }

    /**
     * Returns a random color from the list of colors
     *
     * @return Escaped color code
     */
    public static String RandomColor() {
        List<String> colors = Arrays.asList(Colors.RED, Colors.GREEN, Colors.YELLOW);
        return Helper.getRandomElements(colors, 1).get(0);
    }

    /**
     * Determines the initial health the player entered the battle with
     *
     * @param p player
     * @return max player health
     */
    public static int getMaxHealth(Player p) {
        return (int) ((p.getInventory().stream().mapToInt(Item::getHpIncr).sum() + p.getHp()) * 1.2);
    }

    /**
     * The calculation for healing in battle
     *
     * @param p   player
     * @param max max health
     * @return the amount to heal
     */
    public static int getFullHealAmount(Player p, double max) {

        double h = Math.max(0.4, Math.max((p.getBattleHp() / (max / 1.2)), Math.random()));
        // heals less the lower your health is.
        int healAmount = p.getInventory().stream().mapToInt(Item::getHealIncr).sum() + p.getHealAmount();
        healAmount = (int) (healAmount * h);
        int variance = p.getInventory().stream().mapToInt(Item::getHealVarIncr).sum() + p.getHealVariance();
        variance = (int) ((r.nextInt((variance << 1)) - variance) * h);

        healAmount += variance;
        return healAmount;
    }

    /**
     * Returns a list of enemies that the player can fight given the location they are in
     *
     * @param p player
     * @return an ArrayList of enemies
     */
    public static List<Enemy> getEnemies(Player p) {
        if (p.getStageNum() % BOSS_LEVELS == 0) {
            return getRandomElements(new ArrayList<Enemy>(allBosses), 1);
        }
        ArrayList<Enemy> enemies = Main.currentPlace.allowedEnemies().stream().filter(Enemy::canSpawn).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Enemy> ret = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int rand = r.nextInt(enemies.size());
            ret.add(enemies.get(rand));
            enemies.remove(rand);
        }
        enemies.clear();
        return ret;
    }
}
