package KeeperLand;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enemies.Common.Archer;
import KeeperLand.Enemies.Common.Goblin;
import KeeperLand.Enemies.Common.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static KeeperLand.Main.player;

public class Helper {
    public static boolean speedMode = false;
    public static boolean moreShopInfo = false;
    static Scanner s = new Scanner(System.in);

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

    /**
     * returns the scale factor
     * @param type 0 for hp, 1 for damage, 2 for coins, Enemy e
     *             @return the scale factor proportional to the level of the enemy
     */

    public static float getScaleFactor(int type, Enemy e) {
//        System.out.println("Scaling " + e.getName() + " Type:  " + type);
        //type 0 = hp, 1 = damage, 2 = coins
        int level = e.getLevel();
        if (player == null) {
            return 1;
        }
        int tempHp = player.getHp();
        int tempDmg = player.getDamage();
        for (Item i : player.getInventory()) {
            tempHp += i.getHpIncr();
            tempDmg += i.getDmgIncr();
        }
        float multi = level/5f + 1;

        if (type == 0) {
            float num = (multi*  2) + (multi*0.2f);
            return (num) <= 1 ? 1 : (num);
        }
        else if (type == 1) {
            float num = (multi*  2) + (tempHp *0.05f) ;
            if (e.getLevel() >= 20 && e.getLevel() < 30 ) {
                num = (multi*  2) + (tempHp*0.1f) ;
            }else if (e.getLevel() >= 30 && e.getLevel() < 40 ) {
                num = (multi*  2) + (tempHp*0.2f) ;
            } else if (e.getLevel() >= 40) {
                num = (multi*  2) + (tempHp * 0.3f);

            }
            return Math.max((num/e.getDamage()), 1);
        }
        else if (type == 2) {
            float num = level / 40f;
            return (num) <= 1 ? 1 : (num); //coins scale
        }

        return 1 + (level / 5f); //just in case
    }
    public static int intendedHitsTaken(Enemy e){
        return e.getBaseHp()/5;
    }
    public static int intendedHitsDealt(Enemy e){
        return 40/e.getDamage();
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

    public static <t> void AddArrayToList(List<t> add, t[] added) {
        add.addAll(Arrays.asList(added));
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
            return Colors.YELLOW_UNDERLINED + "legendary" + Colors.RESET;
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
     * @return       list of random entities given in list
     */
    public static <T> List<T> getRandomElements(List<T> list, int amount) {
        List<T> r = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            try {
                r.add(list.get(Main.r.nextInt(list.size())));
            } catch (Exception e) {
                r.add((T) new Goblin());
                r.add((T) new Archer());
                r.add((T) new Warrior());

            }
        }
        //check for duplicates, if there are any, replace them with a random element
        for (int i = 0; i < r.size(); i++) {
            for (int j = 0; j < r.size(); j++) {
                if (i != j && r.get(i).equals(r.get(j))) {
                    r.set(i, list.get(Main.r.nextInt(list.size())));
                }
            }
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
     * @return
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

    public static String RandomColor() {
        List<String> colors = Arrays.asList(Colors.RED, Colors.GREEN, Colors.YELLOW);
        return Helper.getRandomElements(colors, 1).get(0);
    }
}
