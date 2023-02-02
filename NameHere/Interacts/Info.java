package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Helper;
import NameHere.Player;

public class Info extends Interactable {
    @Override
    public String getName() {
        return "Info";
    }

    @Override
    public void onChoose(Player p) {
        //print out all variables in the class player
        System.out.println(p);
        Helper.contiuePrompt();

    }
}
