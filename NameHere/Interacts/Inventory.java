package NameHere.Interacts;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

public class Inventory extends Interactable{
    public void onChoose(Player p){
        System.out.println(p.getName() + "'s inventory: \n");
        for(int i = 0; i < p.getInventory().size(); i++){
            System.out.println("[" + (i + 1) +"] " + p.getInventory().get(i).getName());
        }
        int input = Helper.getInput(Colors.PURPLE + "Enter an item number for more info or 0 to quit" + Colors.RESET, 0, p.getInventory().size());
        if(input != 0){
            Item inspect = p.getInventory().get(input -1);
            System.out.println(inspect.getName() + ":");
            System.out.println(inspect.getDescription());
            System.out.println(Colors.RED + "Damage Increase: " + inspect.getDmgIncr());
            System.out.println("Health Increase: " + inspect.getHpIncr()+ Colors.RESET);
            System.out.println("Rarity: "+ Helper.getWordRarity(inspect));
            Helper.Prompt("Press enter when done");
            onChoose(p);
            System.out.println(Colors.CLEAR);
        }
    }
    public String getName(){return "Inventory";}

}