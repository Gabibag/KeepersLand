package NameHere;
import java.util.*;
public class Helper {
    static Scanner s = new Scanner(System.in);
    /**
     * Sends a message, then returns the next line
     * @param msg what to be sent
     * @return the users next line
     */
    public static String Prompt(String msg){
        System.out.print(Colors.PURPLE+msg+Colors.RESET);
        return s.nextLine();

    }
    /**
     * returns an items rarity as a word from a number following this chart 
     * 10-750  - common
    *751-850 - uncommon
    * 851-925 - rare
    * 926-975 - not epic but still cool
    * 976-999 - super cool
    * 1000 - legendary
     * @param i item in question
     * @return rarity as a word
     */
    public static String getWordRarity(Item i){
        int rarity = i.getRarity();
        if( rarity < 750){
            return "common";
        }
        else if (rarity < 850){
            return " uncommon";
        } 
        else if (rarity < 925){
            return "rare";
        }
        else if (rarity < 975) {
            return "epic";
        }
        else if (rarity <= 999){
            return "super epic";
        }
        else if (rarity  > 1000){
            return "legendary";
        }
        return "unknown";
    }
    /**
     * Dont do it
     * @throws Exception skill issue
     */
    public Helper() throws Exception{
        throw new Exception("Do not create objects of a helper class");
    }
    /**
     * gets some random elements from an list. Can get the same thing more than one time
     * @param <T> type of objects in the array
     * @param list the list to choose from
     * @param amount the size of the returned array
     * @return
     */
    public static <T> List<T> getRandomElements(List<T> list, int amount){
        List<T> r = new ArrayList<T>();
        for(int i =0; i < amount; i++){
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
    public static int getInput(String msg, int top){
        try{
            System.out.println(msg);
            int r = Integer.parseInt(Prompt(Colors.CYAN + "Player: "));
            if(r > 0 && r <= top){
                System.out.print(Colors.CLEAR);
                return r;
            }
            System.out.println(Colors.RED + "Not an option" + Colors.RESET);
            return getInput(msg, top);
        }
        catch(Exception e){
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
    public static int getInput(String msg, int bottom, int top){
        try{
            System.out.println(msg);
            int r =  Integer.parseInt(Prompt(Colors.CYAN + "Player: "));
            if(r >= bottom && r <= top){
                System.out.print(Colors.CLEAR);
                return r;
            }
            System.out.println("Not an option");
            return getInput(msg, bottom, top);
            }catch(Exception e){
            System.out.println("Bad input, try again");
                return getInput(msg, bottom, top);    
            }
            
    }
}
