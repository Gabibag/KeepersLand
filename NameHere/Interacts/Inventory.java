package NameHere.Interacts;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

public class Inventory extends Interactable {
    public void onChoose(Player p) {
        System.out.println(p.getName() + "'s inventory: \n");
        for (int i = 0; i < p.getInventory().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + p.getInventory().get(i).getName());
        }
        int input = Helper.getInput(Colors.PURPLE + "Enter an item number for more info \n[0] Quit" + Colors.RESET, 0,
                                    p.getInventory().size());
        if (input != 0) {
            Item inspect = p.getInventory().get(input - 1);
            System.out.println(inspect);
            Helper.Prompt("Press a enter when done");
            System.out.println(Colors.CLEAR);
            onChoose(p);
        }
    }

    public String getName() {
        return "Inventory";
    }

}