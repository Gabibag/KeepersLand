package NameHere.Interacts;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory extends Interactable{
    public void onChoose(Player p){
        System.out.println(p.getName() + "'s inventory: ");
        System.out.println("Current Balance " +Colors.CYAN+ p.getMoney() + "◊");
        HashMap<String, Integer> iCount = new HashMap<>();
        for(Item i: p.getInventory()){
            if(iCount.containsKey(i.getName())){
                iCount.put(i.getName(), iCount.get(i.getName()) + 1);
            }else{
                iCount.put(i.getName(), 1);
            }
        }
        List<Item> printItems = new ArrayList<Item>();
        
        for(Item i: p.getInventory()){
            if(!printItems.contains(i)){
                printItems.add(i);
                System.out.println(Colors.CYAN + (printItems.size()) + Colors.RESET + " " + i.getName() + "x" + iCount.get(i.getName()) + Colors.RESET);
            }
            
        }
        int input = Helper.getInput(Colors.PURPLE + "Enter an item number for more info \n[0] Quit" + Colors.RESET, 0,
                                    p.getInventory().size());
        
        if (input != 0) {
            Item inspect = printItems.get(input - 1);
            System.out.println(inspect);
            Helper.Prompt("Press a enter when done");
            System.out.println(Colors.CLEAR);
            onChoose(p);
        }

    }

    public String getName() {
        return "Inventory";
    }

}