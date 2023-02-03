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
        System.out.print(
                "Welcome to the shop, " + player.getName());
        while (true) {
            List<Item> items = getItems(player);
            System.out.println("\nYou have " + Colors.CYAN + player.getMoney() + "◊");
            System.out.println(Colors.PURPLE + "[0] Leave");
            System.out.println(Colors.PURPLE + "[1] Quick Buy");
            System.out.println("[2] Inspect");
            System.out.println("[3] Sell Items");
            //System.out.print(Colors.RESET);
            try {
                for (int i = 0; i < items.size(); i++){
                    System.out.println(
                            "[" + (i + 4) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() +
                            "◊" +
                            Colors.PURPLE);
                }
            } catch (Exception e) {
                //items.add(Item.empty);
            }
            int choice = Helper.getInput( "",0,
                                         items.size() + 3);
            if (choice == 0) {
                return;
            }
            else if (choice == 1 ) {
                quickBuy(player, items);
            }
            else if(choice == 2){

                for (int i = 0; i < items.size(); i++)
                    System.out.println(
                            "[" + (i + 1) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() +
                            "◊" +
                            Colors.PURPLE);
                int sC = Helper.getInput(Colors.RESET + "Enter an item to inspect", 1, items.size());
                System.out.println(items.get(sC - 1));
                Helper.Prompt("Press Enter when done");
                System.out.println(Colors.CLEAR);
                continue;
            }
            else if(choice == 3){

                List<Item> inv = player.getInventory();//ref type, no need for set
                System.out.println("[0] Go back");
                for(int i = 0; i < player.getInventory().size();i++){
                    System.out.println("[" + (i + 1) + "] " + inv.get(i).getName());
                }
                int c = Helper.getInput("Enter an item to sell",0  , inv.size());
                if(c ==0){
                    continue;
                }
                else{
                    System.out.println("Sold " + inv.get(c - 1).getName() + " for " + (int)(inv.get(c - 1).getCost()* (9f/10)));
                    player.addMoney((int)(inv.get(c - 1).getCost()* (9f/10)));
                    inv.remove(c - 1);
                }
                continue;
            }
            else{
            Item i = items.get(choice - 4);
            if (i.getCost() > player.getMoney()) {
                System.out.println("Not enough money");
            }
            else {
                player.getInventory().add(i);
                player.chargeMoney(i.getCost());
                System.out.println(
                        "Bought " + i.getName() + " for " + i.getCost() + " \nNew balance: " + player.getMoney());
            }
        }
    }
    }
    public static void quickBuy(Player p, List<Item> items){
        int money = p.getMoney();
        System.out.println("[0] Go back");
        for(Item i : items){
            System.out.println("[" + (items.indexOf(i) + 1) + "] " + i.getName() + " " + i.getCost() + "◊");
        }
        int i = Helper.getInput("Enter an Item number:", 0, items.size());
        if(i == 0){
            System.out.println(Colors.CLEAR);
            return;
        }
        Item item = items.get(i - 1);
        int numBuy = money / item.getCost();
        for(int j = 0; j < numBuy; j++){
            p.getInventory().add(item);
            p.chargeMoney(item.getCost());
        }
        System.out.println("Bought " + numBuy + " " + item.getName() + "s for " + (numBuy * item.getCost()) + "◊");
    }
    public List<Item> getItems(Player p) {
        return Main.currentPlace.getShopItems();
    }
}
