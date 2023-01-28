package NameHere;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Helper {
    public static void Sleep(double s) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) s * 1000);
        } catch (InterruptedException e) {
            System.out.println(Colors.RED_BOLD + "You cannot quit at this time." + Colors.RESET);
        }
    }
    /**
    *returns the scale factor
    */

    public static int getScaleFactor(){
        if(Main.player == null){
            return 1;
        }
        return 1 + Math.round(Main.player.getStageNum() / 20f);
    }
    static Scanner s = new Scanner(System.in);
    /**
     * Dont do it
     *
     * @throws Exception skill issue
     */
    public Helper() throws Exception {
        throw new Exception("Do not create objects of a helper class");
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
    public static void contiuePrompt(){
        System.out.print(Colors.PURPLE + "Press enter to continue" + Colors.RESET);
        s.nextLine();
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
        if (rarity < 10) {
            return "common";
        }
        else if (rarity < 20) {
            return Colors.GREEN + " uncommon" + Colors.RESET;
        }
        else if (rarity < 30) {
            return Colors.RED + "rare" + Colors.RESET;
        }
        else if (rarity < 40) {
            return Colors.PURPLE_BOLD + "epic" + Colors.RESET;
        }
        else if (rarity <= 60) {
            return Colors.PURPLE_UNDERLINED + "super epic" + Colors.RESET;
        }
        else {
            return Colors.YELLOW_UNDERLINED + "legendary" + Colors.RESET;
        }
    }

    /**
     * gets some random elements from an list. Can get the same thing more than one time
     * @param <T> type of objects in the array
     * @param list the list to choose from
     * @param amount the size of the returned array
     * @return
     */
    public static <T> List<T> getRandomElements(List<T> list, int amount) {
        List<T> r = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            r.add(list.get(Main.r.nextInt(list.size())));
        }

        return r;
    }

    /**
     * prompts the user with a message then gets the next int they type, as long as its 1-top (inclusive both ways)
     * will keep asking until a valid input is obtained
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
     * prompts the user with a message then gets the next int they type, as long as its bottom-top (inclusive both ways)
     * will keep asking until a valid input is obtained
     * @param msg what to ask
     * @param bottom lowest valid value 
     * @param top highest valid value
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
}
