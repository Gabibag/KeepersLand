package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;

import java.util.Arrays;
import java.util.List;

public class Shop extends Interactable {
    @Override public String getName(){return "Shop";}
    public void onChoose(Player player) {
        System.out.println("Welcome to the shop, " + player.getName() + ". \nYou have " + Colors.CYAN + player.getMoney()  + " ◊");
        while (true) {
            List<Item> items = getItems(player);
            for (int i = 0; i < items.size(); i++)
                System.out.println(Colors.PURPLE +
                        "[" + (i + 1) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() + "◊" + Colors.PURPLE);
            int choice = Helper.getInput("[-1] Leave " + Colors.RESET + "\nEnter your choice:", -1, items.size() + 1);
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
                System.out.println("Bought " + i.getName() + " for " + i.getCost() + " \n new bal: " + player.getMoney());
            }
        }
    }

    public List<Item> getItems(Player p) {
        //TODO logic
        return Arrays.asList(
                Item.warriorSword,
                Item.bountyHunterSword, Item.slimeShield, Item.warriorSword);

    }
}
