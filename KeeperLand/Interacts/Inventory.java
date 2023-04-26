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
        System.out.println("⚔ = Damage, ❤ = Health, ✧ = Heal, ⚕ = Heal Variance");
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

        int maxNameLength = 0;
        for (int i = 0; i < printItems.size(); i++) {
            if (printItems.get(i).getName().length() > maxNameLength) {
                maxNameLength = printItems.get(i).getName().length();
            }
        }
        int maxColLength = 0;
        for(int i = 0; i<printItems.size(); i++){
            if(String.valueOf(printItems.get(i).getHealVariance()).length() > maxColLength){
                maxColLength = String.valueOf(printItems.get(i).getHealVariance()).length();
            } if (String.valueOf(printItems.get(i).getHealIncrease()).length() > maxColLength){
                maxColLength = String.valueOf(printItems.get(i).getHealIncrease()).length();
            } if (String.valueOf(printItems.get(i).getHpIncr()).length() > maxColLength){
                maxColLength = String.valueOf(printItems.get(i).getHpIncr()).length();
            } if (String.valueOf(printItems.get(i).getDmgIncr()).length() > maxColLength) {
                maxColLength = String.valueOf(printItems.get(i).getDmgIncr()).length();
            }
        }
        
        
        int count = 1;
        //if the item in printItems is a shard, bring it to the top
        for (int i = 0; i < printItems.size(); i++) {
            if (printItems.get(i).getName().toLowerCase().contains("shard")) {
                Item temp = printItems.get(i);
                printItems.remove(i);
                printItems.add(0, temp);
            }
        }
        for (int i = 0; i < printItems.size(); i++) {
            Item items = printItems.get(i);
            String col = Colors.RESET;
            if (iCount.get(items.getName()) > 100) {
                col = Colors.RED_BRIGHT;
            } else if (iCount.get(items.getName()) > 50) {
                col = Colors.RED;
            } else if (iCount.get(items.getName()) > 25) {
                col = Colors.YELLOW;
            } else if (iCount.get(items.getName()) > 10) {
                col = Colors.GREEN;
            }
            if (items.getName().toLowerCase().contains("shard")) {
                col = Colors.BLUE;
            }
            String spaceCount = "";
            String variCount = "";
            String hpCount = "";
            String healCount = "";
            String dmgCount = "";
            for (int j = 0; j < maxNameLength - items.getName().length() + 2 - String.valueOf(count).length(); j++) {
                spaceCount += " ";
            }
            for (int j = 0; j < maxColLength - String.valueOf(items.getHealVariance()).length(); j++) {
                variCount += " ";
            }
            for (int j = 0; j < maxColLength - String.valueOf(items.getHpIncr()).length(); j++) {
                hpCount += " ";
            }
            for (int j = 0; j < maxColLength - String.valueOf(items.getHealIncrease()).length(); j++) {
                healCount += " ";
            }
            for (int j = 0; j < maxColLength - String.valueOf(items.getDmgIncr()).length(); j++) {
                dmgCount += " ";
            }
            System.out.println(
                    Colors.CYAN+"[" + (count) +"] " +  col + items.getName() + spaceCount +
                            Colors.RED + " ⚔" + items.getDmgIncr() + dmgCount + Colors.GREEN + " ❤" + (items).getHpIncr() + hpCount + Colors.YELLOW + " ✧" + (items).getHealIncrease() + healCount + Colors.PURPLE + " ⚕" + (items).getHealVariance() + variCount + " x" + iCount.get(items.getName()) + " " + (Helper.moreShopInfo ? Colors.RESET + " (" + (items).getDescription() + ")" : "") + Colors.RESET);
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