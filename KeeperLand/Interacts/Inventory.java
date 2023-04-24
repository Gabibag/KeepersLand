package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Colors;
import KeeperLand.Helper;
import KeeperLand.Item;
import KeeperLand.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory extends Interactable {
    public void onChoose(Player p) {
        System.out.println(Colors.PURPLE + "[0] Go Back");
        System.out.println("[1] Inventory");
        System.out.println("[2] Stats" + Colors.RESET);
        int c = Helper.getInput("", 0, 2);
        //if c is 1, show inventory, case c is 2, print player p
        if (c == 1) {
            inventory(p);
        } else {
            System.out.println(p);
            Helper.continuePrompt();
        }

    }

    private void inventory(Player p) {
        System.out.println(p.getName() + "'s inventory: ");
        System.out.println("Current Balance " + Colors.CYAN + p.getMoney() + "◊");
        HashMap<String, Integer> iCount = new HashMap<>();
        for (Item i : p.getInventory()) {
            if (iCount.containsKey(i.getName())) {
                iCount.put(i.getName(), iCount.get(i.getName()) + 1);
            } else {
                iCount.put(i.getName(), 1);
            }
        }
        List<Item> printItems = new ArrayList<>();
        printItems.addAll(p.getInventory());
        //check if the item is already in the list, if it is, remove the item from the list
        for (int i = 0; i < printItems.size(); i++) {
            for (int j = i + 1; j < printItems.size(); j++) {
                if (printItems.get(i).getName().equals(printItems.get(j).getName())) {
                    printItems.remove(j);
                    j--;
                }
            }
        }

        int count = 1;
        for (Item i : printItems) {
            String col = Colors.RESET;
            if (iCount.get(i.getName()) > 100) {
                col = Colors.RED_BRIGHT;
            } else if (iCount.get(i.getName()) > 50) {
                col = Colors.RED;
            } else if (iCount.get(i.getName()) > 25) {
                col = Colors.YELLOW;
            } else if (iCount.get(i.getName()) > 10) {
                col = Colors.GREEN;
            }
            if (i.getName().toLowerCase().contains("shard")) {
                col = Colors.BLUE;
            }
            System.out.println(Colors.CYAN + (count) + col + " " + i.getName() + Colors.RESET + " x" + iCount.get(i.getName()) + Colors.RESET);
            count++;
        }
        int input = Helper.getInput(Colors.PURPLE + "Enter an item number for more info \n[0] Quit" + Colors.RESET, 0,
                p.getInventory().size());

        if (input != 0) {
            Item inspect = printItems.get(input - 1);
            System.out.println(inspect);
            Helper.Prompt("Press a enter when done");
            System.out.println(Colors.CLEAR);
            inventory(p);
        }
    }

    public String getName() {
        return "Info";
    }

}