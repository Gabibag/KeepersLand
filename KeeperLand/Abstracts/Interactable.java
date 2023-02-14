package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

public abstract class Interactable {
    public Interactable() {
        //because Java wont let me load all of the types currently in runtime, we have to use this ugly alternative 
        if (this.getName()!=null) {
            //makes it so its not viewable in the menu
            Main.allInteracts.add(this);
        }
        //this makes it so we know this is a usable interactable
        //WITHOUT THIS IT WILL NOT BE USABLE, MAKE SURE TO ADD IT TO OTHER CONSTRUCTORS
    }

    public abstract String getName();

    public abstract void onChoose(Player p);
}
