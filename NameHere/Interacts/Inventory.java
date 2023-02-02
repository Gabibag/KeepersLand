package NameHere.Interacts;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

import java.util.ArrayList;

public class Inventory extends Interactable{
    public void onChoose(Player p){
        System.out.println(p.getName() + "'s inventory: ");
        System.out.println("Current Balance " +Colors.CYAN+ p.getMoney() + "◊");
        ArrayList<Item> inventoryTrunk = new ArrayList<>();
        //for each item in inventory, add it to inventoryTrunk. If it already exists, add to the variable Count in the item.
        for(Item i : p.getInventory()){
            if(inventoryTrunk.contains(i)){
                inventoryTrunk.get(inventoryTrunk.indexOf(i)).addCount();
            }else{
                inventoryTrunk.add(i);
            }
        }
        for(int i = 0; i < inventoryTrunk.size(); i++){
            System.out.println("[" + (i + 1) +"] " + inventoryTrunk.get(i).getName() + " x" + inventoryTrunk.get(i).getCount());
        }
        int input = Helper.getInput(Colors.PURPLE + "Enter an item number for more info \n[0] Quit" + Colors.RESET, 0,
                                    inventoryTrunk.size());
        if (input != 0) {
            Item inspect = inventoryTrunk.get(input - 1);
            System.out.println(inspect);
            Helper.Prompt("Press a enter when done");
            System.out.println(Colors.CLEAR);
            onChoose(p);
        }
        for(Item i : p.getInventory()){
            i.setCount(1);
        }

    }

    public String getName() {
        return "Inventory";
    }

}