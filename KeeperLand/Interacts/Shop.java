package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.*;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Interactable {
    public static void quickBuy(Player p, List<Item> items) {
        int money = p.getMoney();
        System.out.println("[0] Go back");
        for (Item i : items) {
            System.out.println("[" + (items.indexOf(i) + 1) + "] " + i.getName() + " " + i.getCost() + "◊");
        }
        int i = Helper.getInput("Enter an Item number:", 0, items.size());
        if (i == 0) {
            System.out.println(Colors.CLEAR);
            return;
        }
        Item item = items.get(i - 1);
        int numBuy = money / item.getCost();
        for (int j = 0; j < numBuy; j++) {
            p.getInventory().add(item);
            p.chargeMoney(item.getCost());
        }
        System.out.println("Bought " + numBuy + " " + item.getName() + "s for " + (numBuy * item.getCost()) + "◊");
    }

    public static void superBuy(Player p) {
        int money = p.getMoney();
        List<Item> tempItems = new ArrayList<>();
        for (int i = 0; i < Main.currentPlace.getShopItems().size(); i++) {
            if(!Main.currentPlace.getShopItems().get(i).getName().equalsIgnoreCase("dull skull")){
                tempItems.add(Main.currentPlace.getShopItems().get(i));
            }


        }


        //sort items by cost ascending
        for (int i = 0; i < tempItems.size(); i++) {
            for (int j = 0; j < tempItems.size() - 1; j++) {
                if (tempItems.get(j).getCost() > tempItems.get(j + 1).getCost()) {
                    Item temp = tempItems.get(j);
                    tempItems.set(j, tempItems.get(j + 1));
                    tempItems.set(j + 1, temp);
                }
            }
        }

        for (int i = 0; i < tempItems.size(); i++) {
            if (tempItems.get(i).getCost() <= money) {
                p.getInventory().add(tempItems.get(i));
                p.chargeMoney(tempItems.get(i).getCost());
                money = p.getMoney();
                i--;
            }
        }
        System.out.println("Bought all items you could afford");
    }

    @Override
    public String getName() {
        //save the result of getItems to a list named items

        //sort the list items by cost descending
        try {
            List<Item> items = getItems(Main.player);
            if (Main.player.getMoney() >= items.get(0).getCost()) {
                return Colors.GREEN + "Shop" + Colors.PURPLE;
            }
        } catch (Exception e) {
            return "Shop";
        }
        return "Shop";

    }

    public void onChoose(Player player) {
        System.out.print(
                "Welcome to the shop, " + player.getName());
        while (true) {
            List<Item> items = new ArrayList<>();
            try {
                items = getItems(player);
            } catch (Exception ignored) {//in case its empty
                items.add(ItemData.empty);

            }
            //if the player's stage number is less than 10 remove all Items that contain the word "shard" in their name
            if (player.getStageNum() < 10) {
                for (int i = items.size() - 1; i >= 0; i--) {
                    if (items.get(i).getName().contains("Shard")) {
                        Item il = ItemData.LockedItem;
                        il.setCost(9999999);
                        items.set(i, il);
                    }
                }
            }
            System.out.println("\nYou have " + Colors.CYAN + player.getMoney() + "◊");
            System.out.println(Colors.PURPLE + "[0] Leave");
            System.out.println(Colors.PURPLE + "[1] Quick Buy");
            System.out.println(Colors.PURPLE + "[2] Super Buy");
            System.out.println("[3] Inspect");
            System.out.println("[4] Sell Items");
            //System.out.print(Colors.RESET);
            int maxNameLength = 0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().length() > maxNameLength) {
                    maxNameLength = items.get(i).getName().length();
                }
            }
            int maxColLength = 0;
            for(int i = 0; i<items.size(); i++){
                if(String.valueOf(items.get(i).getHealVariance()).length() > maxColLength){
                    maxColLength = String.valueOf(items.get(i).getHealVariance()).length();
                } if (String.valueOf(items.get(i).getHealIncrease()).length() > maxColLength){
                    maxColLength = String.valueOf(items.get(i).getHealIncrease()).length();
                } if (String.valueOf(items.get(i).getHpIncr()).length() > maxColLength){
                    maxColLength = String.valueOf(items.get(i).getHpIncr()).length();
                } if (String.valueOf(items.get(i).getDmgIncr()).length() > maxColLength) {
                    maxColLength = String.valueOf(items.get(i).getDmgIncr()).length();
                }
            }
            try {
                for (int i = 0; i < items.size(); i++) {
                    StringBuilder spaceCount = new StringBuilder();
                    StringBuilder variCount = new StringBuilder();
                    StringBuilder hpCount = new StringBuilder();
                    StringBuilder healCount = new StringBuilder();
                    StringBuilder dmgCount = new StringBuilder();
                    for (int j = 0; j < maxNameLength - items.get(i).getName().length() + 2; j++) {
                        spaceCount.append(" ");
                    }
                    for (int j = 0; j < maxColLength - String.valueOf(items.get(i).getHealVariance()).length(); j++) {
                        variCount.append(" ");
                    }
                    for (int j = 0; j < maxColLength - String.valueOf(items.get(i).getHpIncr()).length(); j++) {
                        hpCount.append(" ");
                    }
                    for (int j = 0; j < maxColLength - String.valueOf(items.get(i).getHealIncrease()).length(); j++) {
                        healCount.append(" ");
                    }
                    for (int j = 0; j < maxColLength - String.valueOf(items.get(i).getDmgIncr()).length(); j++) {
                        dmgCount.append(" ");
                    }
                    String col = Colors.PURPLE;
                    if (items.get(i).getCost()<= player.getMoney()){
                        col = Colors.GREEN;
                    }
                    System.out.println(
                            Colors.CYAN+"[" + (i + 5) +"] " +  col + items.get(i).getName() + spaceCount +
                                    Colors.RED + " ⚔" + items.get(i).getDmgIncr() + dmgCount + Colors.GREEN + " ❤" + (items).get(i).getHpIncr() + hpCount + Colors.YELLOW + " ✧" + (items).get(i).getHealIncrease() + healCount + Colors.PURPLE + " ⚕" + (items).get(i).getHealVariance() + variCount + Colors.CYAN + " ◊" + (items).get(i).getCost() + Colors.RESET+ (Helper.moreShopInfo ? Colors.RESET + " (" + (items).get(i).getDescription() + ")" : "") + Colors.RESET);
                }
            } catch (Exception e) {
                //items.add(Item.empty);
            }
            int choice = Helper.getInput("", 0,
                    items.size() + 4);
            if (choice == 0) {
                return;
            } else if (choice == 1) {
                quickBuy(player, items);
            } else if (choice == 2) {
                superBuy(player);
            } else if (choice == 3) {

                for (int i = 0; i < items.size(); i++)
                    try {
                        System.out.println(
                                "[" + (i + 1) + "] " + items.get(i).getName() + Colors.CYAN + " " + items.get(i).getCost() +
                                        "◊" + Colors.RESET);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                int sC = Helper.getInput(Colors.RESET + "Enter an item to inspect", 1, items.size());
                System.out.println(items.get(sC - 1));
                Helper.Prompt("Press Enter when done");
                System.out.println(Colors.CLEAR);
            } else if (choice == 4) {

                List<Item> inv = player.getInventory();//ref type, no need for set
                System.out.println("[0] Go back");
                System.out.println(Colors.PURPLE);
                for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + inv.get(i).getName());
                }
                System.out.println("Note: Items are sold for 90% of their original value");
                int c = Helper.getInput("Enter an item to sell", 0, inv.size());
                if (c != 0) {
                    System.out.println("Sold " + inv.get(c - 1).getName() + " for " + (int) (inv.get(c - 1).getCost() * (9f / 10)));
                    player.addMoney((int) (inv.get(c - 1).getCost() * (9f / 10)));
                    inv.remove(c - 1);
                }

            } else {
                Item i = items.get(choice - 5);
                if (i.getCost() > player.getMoney()) {
                    System.out.println("Not enough money");
                } else {
                    player.getInventory().add(i);
                    player.chargeMoney(i.getCost());
                    System.out.println(
                            "Bought " + i.getName() + " for " + i.getCost() + " \nNew balance: " + player.getMoney());
                }
            }
        }
    }

    public ArrayList<Item> getItems(Player p) {
        return new ArrayList<Item>(Main.currentPlace.getShopItems());
    }
}