package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static KeeperLand.Main.player;

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
            p.addToInventory(item);
            p.chargeMoney(item.getCost());
        }
        System.out.println("Bought " + numBuy + " " + item.getName() + "s for " + (numBuy * item.getCost()) + "◊");
    }

    public static void superBuy(Player p) {
        if (Main.currentPlace.getShopItems().isEmpty()) return;
        List<Item> tempItems = new ArrayList<>(Main.currentPlace.getShopItems().stream().filter(o1 -> !o1.getName().equalsIgnoreCase("dull skull")).toList());
        //sort items by cost ascending
        tempItems.sort(Comparator.comparingInt(Item::getCost));
        int i = 0;
        while (tempItems.stream().anyMatch(o1 -> o1.getCost() <= p.getMoney())) {
            if (tempItems.get(i).getCost() > p.getMoney()) continue;
            int index = i % tempItems.size();
            buyItem(player, tempItems.get(index), true);
            i++;
        }
        System.out.println("Bought all items you could afford");
    }

    public static void statBuy(Player p) {
        if (Main.currentPlace.getShopItems().isEmpty()) return;
        List<Item> tempItems = new ArrayList<>(Main.currentPlace.getShopItems().stream().filter(o1 -> !o1.getName().equalsIgnoreCase("dull skull")).toList());
        tempItems.sort(Comparator.comparingInt(Item::getCost));
        for (Item item : tempItems) {
            if (item.getCost() > p.getMoney()) continue;
            buyItem(player, item, false);
        }
//        Helper.checkForComplexCreation(Main.currentPlace.getShopItems());

    }


    @Override
    public String getName() {
        //save the result of getItems to a list named items

        //sort the list items by cost descending
        try {
            List<Item> items = getItems(player);
            for (Item item : items) {
                if (player.getMoney() >= item.getCost()) {
                    return Colors.GREEN + "Shop" + Colors.PURPLE;
                }
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
            //System.out.print(Colors.RESET);
            displayList(items);
            int choice = Helper.getInput("", 0,
                    items.size() + 3);
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
            } else {
                Item i = items.get(choice - 4);
                buyItem(player, i, true);
            }
        }
    }

    private static void buyItem(Player player, Item i, Boolean announcePurchase) {
        if (i.getCost() > player.getMoney() && announcePurchase) {
            System.out.println("Not enough money");
        } else {
            Item give = i.randTier();

            player.addToInventory(give);
            player.chargeMoney(i.getCost());
            if (announcePurchase) {
                System.out.println(
                        "Bought " + give.getColTier() + give.getStrTier() + " " + Colors.RESET + give.getName() + " for " + i.getCost() + " \nNew balance: " + player.getMoney());
            }
        }
    }

    private static void buyMultipleItems(Item i, int count, Boolean announcePurchase) {
        if (i.getCost() * count > player.getMoney()) {
            if (announcePurchase) {
                System.out.println("Not enough money");
            }
        } else {
            for (int j = 0; j < count; j++) {
                Item give = i.randTier();
                player.addToInventory(give);
            }
            player.chargeMoney(i.getCost() * count);
            if (!announcePurchase) return;
            System.out.println(
                    "Bought " + count + " " + i.getColTier() + i.getStrTier() + " " + Colors.RESET + i.getName() + " for " + i.getCost() * count + " \nNew balance: " + player.getMoney());
        }
    }


    private static void displayList(List<Item> items) {
        //sort items by cost descending
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < items.size() - 1; j++) {
                if (items.get(j).getCost() < items.get(j + 1).getCost()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
        Player player = Main.player;
        int maxNameLength = 0;
        for (Item value : items) {
            if (value.getName().length() > maxNameLength) {
                maxNameLength = value.getName().length();
            }
        }
        int maxColLength = 0;
        for (Item item : items) {
            if (String.valueOf(item.getHealVarIncr()).length() > maxColLength) {
                maxColLength = String.valueOf(item.getHealVarIncr()).length();
            }
            if (String.valueOf(item.getHealIncr()).length() > maxColLength) {
                maxColLength = String.valueOf(item.getHealIncr()).length();
            }
            if (String.valueOf(item.getHpIncr()).length() > maxColLength) {
                maxColLength = String.valueOf(item.getHpIncr()).length();
            }
            if (String.valueOf(item.getDmgIncr()).length() > maxColLength) {
                maxColLength = String.valueOf(item.getDmgIncr()).length();
            }
        }
        try {
            for (int i = 0; i < items.size(); i++) {
                StringBuilder variCount = new StringBuilder();
                StringBuilder hpCount = new StringBuilder();
                StringBuilder healCount = new StringBuilder();
                StringBuilder dmgCount = new StringBuilder();
                String spaceCount = " ".repeat(Math.max(0, maxNameLength - items.get(i).getName().length() + 2));
                variCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(items.get(i).getHealVarIncr()).length())));
                hpCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(items.get(i).getHpIncr()).length())));
                healCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(items.get(i).getHealIncr()).length())));
                dmgCount.append(" ".repeat(Math.max(0, maxColLength - String.valueOf(items.get(i).getDmgIncr()).length())));
                String col = Colors.PURPLE;
                if (items.get(i).getCost() <= player.getMoney()) {
                    col = Colors.GREEN;
                }
                System.out.println(
                        Colors.CYAN + "[" + (i + 4) + "] " + col + items.get(i).getName() + spaceCount +
                                Colors.RED + " ⚔" + items.get(i).getDmgIncr() + dmgCount + Colors.GREEN + " ❤" + items.get(i).getHpIncr() + hpCount + Colors.YELLOW + " ✧" + items.get(i).getHealIncr() + healCount + Colors.PURPLE + " ⚕" + items.get(i).getHealVarIncr() + variCount + Colors.CYAN + " ◊" + items.get(i).getCost() + Colors.RESET + (Helper.moreShopInfo ? Colors.RESET + " (" + items.get(i).getDescription() + ")" : "") + Colors.RESET);
            }
        } catch (Exception e) {
            //items.add(Item.empty);
        }
    }

    public ArrayList<Item> getItems(Player p) {
        return new ArrayList<Item>(Main.currentPlace.getShopItems());
    }
}