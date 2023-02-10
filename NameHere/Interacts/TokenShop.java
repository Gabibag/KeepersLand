package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.*;

import java.util.ArrayList;
import java.util.List;

public class TokenShop extends Interactable {


    @Override
    public String getName() {
        //set a string to locked if the player doesn't have a shard in their inventory. if they do, set it to token shop
        for (Item item : Main.player.getInventory()) {
          if (item.getName().contains("Shard")&&!item.getName().equalsIgnoreCase("Shattered Shard")){
              if (Main.player.getTokens() >= 10){
              return Colors.GREEN + "Token Shop" + Colors.PURPLE;
              }
              return "Token Shop";
          }
        }
        return "Locked";
    }

    public void onChoose(Player player) {
        //if get name is locked tell the user they cant access this yet
        if (getName().equalsIgnoreCase("Locked")){
            System.out.println("You can't access this yet.");
            Helper.contiuePrompt();
            return;
        }
        System.out.print("Welcome to the token shop, " + player.getName());
        while (true) {
            List<Item> items = Main.allItem;
            for (int j = items.size() - 1; j >= 0; j--) {
                Item i = items.get(j);
                //if items' doesn't have shard in it or it's name is "shattered shard", remove it from items
                if (!i.getName().contains("Shard") || i.getName().equals("Shattered Shard")) {
                    items.remove(j);
                }

            }


            System.out.println("\nYou have " + Colors.GREEN_BOLD + player.getTokens() + "₪");
            System.out.println(Colors.PURPLE + "[0] Leave");
            System.out.println("[1] Inspect");
            //System.out.print(Colors.RESET);
            try {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(
                            "[" + (i + 2) + "] " + items.get(i).getName() + Colors.GREEN_BOLD + " " + items.get(i).getTokenCost() +
                            "₪" + Colors.PURPLE);
                }
            } catch (Exception e) {
                //items.add(Item.empty);
            }
            int choice = Helper.getInput("", 0, items.size() + 4);
            if (choice == 0) {
                return;
            }
            else if (choice == 1) {
                for (int i = 0; i < items.size(); i++)
                    System.out.println(
                            "[" + (i + 2) + "] " + items.get(i).getName() + Colors.GREEN_BOLD + " " + items.get(i).getTokenCost() +
                            "₪" + Colors.PURPLE);
                int sC = Helper.getInput(Colors.RESET + "Enter an item to inspect", 1, items.size());
                System.out.println(items.get(sC - 1));
                Helper.Prompt("Press Enter when done");
                System.out.println(Colors.CLEAR);
            }

            else {
                Item i = items.get(choice - 1);
                if (i.getTokenCost() > player.getTokens()) {
                    System.out.println("Not enough money");
                    Helper.Sleep(1);
                }
                else {
                    player.getInventory().add(i);
                    player.setTokens(player.getTokens()-i.getTokenCost());
                    System.out.println(
                            "Bought " + i.getName() + " for " + i.getTokenCost() + " \nNew balance: " + player.getTokens());
                }
            }
        }
    }

    public ArrayList<Item> getItems(Player p) {
        return new ArrayList<>(Main.currentPlace.getShopItems());
    }
}