package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.*;

import java.util.List;

public class Shop extends Interactable {
    @Override
    public String getName() {
        return "Shop";
    }

    public void onChoose(Player player) {
        System.out.println(
                "Welcome to the shop, " + player.getName() + ". \nYou have " + Colors.CYAN + player.getMoney() + "◊");
        while (true) {
            List<Item> items = getItems(player);
            System.out.println(Colors.PURPLE + "[0] Quit");
            try {
                for (int i = 0; i < items.size(); i++)
                    System.out.println(
                            "[" + (i + 1) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() +
                            "◊" +
                            Colors.PURPLE);
            } catch (Exception e) {
                items.add(Item.empty);
            }
            int choice = Helper.getInput("[" + (items.size() + 1) + "] Inspect shop item\n" + "Enter your choice", 0,
                                         items.size() + 1);
            if (choice == 0) {
                return;
            }
            else if (choice == items.size() + 1) {
                for (int i = 0; i < items.size(); i++)
                    System.out.println(
                            "[" + (i + 1) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() + "◊" +
                            Colors.PURPLE);
                int sC = Helper.getInput(Colors.RESET + "Enter an item to inspect", 1, items.size());
                System.out.println(items.get(sC - 1));
                Helper.Prompt("Press Enter when done");
                System.out.println(Colors.CLEAR);
                continue;
            }
            Item i = items.get(-1 + choice);
            if (i.getCost() > player.getMoney()) {
                System.out.println("Not enough money");
            }
            else {
                player.getInventory().add(i);
                player.chargeMoney(i.getCost());
                System.out.println(
                        "Bought " + i.getName() + " for " + i.getCost() + " \n new balance: " + player.getMoney());
            }
        }
    }

    public List<Item> getItems(Player p) {
        return Main.currentPlace.getShopItems();
    }
}
