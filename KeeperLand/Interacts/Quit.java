package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Helper;
import KeeperLand.Main;
import KeeperLand.Player;

public class Quit extends Interactable {

    @Override
    public String getName() {
        return "Quit";
    }

    @Override
    public void onChoose(Player p) {
        Main.player.Save(p.getName() + ".plr");
        int a = Helper.getInput("Are you sure you want to quit? Please enter \"9\" if yes, and anything else if no", 10);
        if (a != 9) return;

        System.out.println("Save created \nGoodbye");
        System.exit(0);
    }
}
