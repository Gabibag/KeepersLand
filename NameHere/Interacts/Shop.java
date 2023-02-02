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
            try {
                for (int i = 0; i < items.size() + 1; i++){
                    System.out.println(
                            "[" + (i + 2) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() +
                            "◊" +
                            Colors.PURPLE);
                }
            } catch (Exception e) {
                //items.add(Item.empty);
            }
            System.out.println("[" + (items.size() + 2) + "] Inspect shop item\n" +"[" + (items.size() + 3) + "] "+ "Sell Items\n"+  "Enter your choice");
            int choice = Helper.getInput( "",0,
                                         items.size() + 3);
            if (choice == 0) {
                return;
            }
            else if (choice == 1) {
                quickBuy(Main.player);
            }
            else if (choice == items.size()-1) {
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
            else if(choice == items.size()){
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
            Item i = items.get(choice-2);
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
    public static void quickBuy(Player p){
        int money = p.getMoney();
        List<Item> tempItems = Main.currentPlace.getShopItems();
        //sort items by cost ascending
        for(int i = 0; i < tempItems.size(); i++){
            for(int j = 0; j < tempItems.size() - 1; j++){
                if(tempItems.get(j).getCost() > tempItems.get(j + 1).getCost()){
                    Item temp = tempItems.get(j);
                    tempItems.set(j, tempItems.get(j + 1));
                    tempItems.set(j + 1, temp);
                }
            }
        }

        for(int i = 0; i < tempItems.size(); i++){
            if(tempItems.get(i).getCost() <= money){
                p.getInventory().add(tempItems.get(i));
                p.chargeMoney(tempItems.get(i).getCost());
                money = p.getMoney();
                i--;
            }
        }
        System.out.println("Bought all items you could afford");
    }
    public List<Item> getItems(Player p) {
        return Main.currentPlace.getShopItems();
    }
}
