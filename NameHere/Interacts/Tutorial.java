package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Player;

public class Tutorial extends Interactable {

    @Override
    public String getName() {
        return "Tutorial";
    }

    @Override
    public void onChoose(Player p) {
        System.out.println("Hello Player!");
        //TODO: add tutorial
    }
}
