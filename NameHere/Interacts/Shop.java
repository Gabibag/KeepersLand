package NameHere.Interacts;
import java.util.Arrays;
import java.util.List;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;
import NameHere.*;
import NameHere.Abstracts.Interactable;

public class Shop extends Interactable {
    @Override public String getName(){return "Shop";}
    public void onChoose(Player player) {
        System.out.println("Welcome to the shop, " + player.getName() + ". \nYou have " + player.getMoney() + " Coins");
        while (true) {
            List<Item> items = getItems(player);
            for (int i = 0; i < items.size(); i++)
                System.out.println(
                        "[" + (i + 1) + "] " + items.get(i).getName() + " ($" + items.get(i).getCost() + ")");
            int choice = Helper.getInput("[-1] Quit \nEnter your choice:", -1, items.size() + 1);
            while(choice == 0){
                //has to be hardcoded :(
                choice = Helper.getInput("Not a choice", -1, items.size());
            }
            if (choice == -1) {
                return;
            }
            Item i = items.get(-1 + choice);
            if (i.getCost() > player.getMoney()) {
                System.out.println("Not enough money");
            }
            else {
                player.getInventory().add(i);
                player.chargeMoney(i.getCost());
                System.out.println("Bought " + i.getName() + " for " + i.getCost() + " \n new balance: " + player.getMoney());
            }
        }
    }

    public List<Item> getItems(Player p) {
        //TODO logic
        return Arrays.asList(
                new Item(5, 3,
                         "Blade of Tessting",
                         "I hate javaafjajaja",
                         100, 30),
                new Item(5, 3,
                         "Second blade of Tessting",
                         "I hate javaafjajaja",
                         100, 5));
    }
}
