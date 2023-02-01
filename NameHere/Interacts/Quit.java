package NameHere.Interacts;

import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Interactable;

public class Quit extends Interactable {

    @Override
    public String getName() {
        return "Quit";
    }

    @Override
    public void onChoose(Player p) {
        Main.player.Save(p.getName() + ".plr");
        System.out.println("Save created \nGoodbye");
        System.exit(0);
    }
}
