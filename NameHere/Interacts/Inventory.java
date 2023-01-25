package NameHere.Interacts;

import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

public class Inventory extends Interactable{
    public void onChoose(Player p){
        System.out.println(p.getName() + "'s inventory: ");
        for(int i = 0; i < p.getInventory().size(); i++){
            System.out.println("[" + (i + 1) +"] " + p.getInventory().get(i).getName());
        }
        int input = Helper.getInput("Enter an item number for more info or 0 to quit", 0, p.getInventory().size());
        if(input == 0){
        }
        else{
            Item inspect = p.getInventory().get(input -1);
            System.out.println(inspect.getName() + ":");
            System.out.println(inspect.getDescription());
            System.out.println("Damage Increase: " + inspect.getDmgIncr());
            System.out.println("Health Increase: " + inspect.getHpIncr());
            System.out.println("Rarity: "+ Helper.getWordRarity(inspect));
            Helper.Prompt("Press a enter when done...");
            onChoose(p);
        }
    }
    public String getName(){return "Inventory";}

}