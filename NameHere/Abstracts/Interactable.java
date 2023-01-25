package NameHere.Abstracts;
import java.util.Dictionary;
import java.util.List;
import java.util.function.Function;

import NameHere.Main;
import NameHere.Player;

public abstract class Interactable {
    public Interactable(){
        //because Java wont let me load all of the types currently in runtime, we have to use this ugly alternative 
        Main.allInteracts.add(this);
        //this makes it so we know this is a usable interactable
        //WITHOUT THIS IT WILL NOT BE USABLE, MAKE SURE TO ADD IT TO OTHER CONSTRUCTORS
    }
    public abstract String getName();
    public abstract void onChoose(Player p);
}
