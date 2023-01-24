import java.util.Dictionary;
import java.util.List;
import java.util.function.Function;

public abstract class Interactable {
    public Interactable(){
        //because Java wont let me load all of the types currently in runtime, we have to use this ugly alternative 
        Main.allInteracts.add(this);
        //this makes it so we know this is an usable interactable 
        //WITHOUT THIS IT WILL NOT BE USABLE, MAKE SURE TO ADD IT TO OTHER CONSTRUCTORS
    }
    //the message sent at the start
    public abstract String getStartMessage(Player p);
    
public abstract void OnChoose(Player p);
}
