package NameHere.Interacts;

import NameHere.Player;
import NameHere.Abstracts.Interactable;

public class Quit extends Interactable{

    @Override
    public void OnChoose(Player p) {
       System.out.println("Goodbye");
       System.exit(0);
    }
}
