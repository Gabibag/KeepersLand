import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Shop extends Interactable{
    public String getStartMessage(Player p){
        return "Welcome to the shop";
    }
    private boolean run;
    public void OnChoose(Player p){
        run = true;
        System.out.println("Welcome to the shop, " + p.getName() + ". \n You have " +  p.getMoney());
        while (run){
            
            List<Item> items = getItems(p);
            for(int i = 0; i < items.size(); i ++)
                System.out.println("[" + (i + 1) + "] " + items.get(i).getName() + " ($" + items.get(i).getCost()+ ")");
                int choice = Main.getInput("[-1] Quit \n Enter your choice:");
                if(choice == -1){
                    run = false;
                    break;
                }
                Item i = items.get(-1 + choice);
                if(i.getCost() > p.getMoney()){
                    System.out.println("Not enough money");
                    continue;
                }
                else{
                    p.getInventory().add(i);
                    p.chargeMoney(i.getCost());
                    System.out.println("Bought " + i.getName() + " for " + i.getCost() + " \n new bal: " + p.getMoney());
                }
            }
                    }
    public List<Item> getItems(Player p ){
        //TODO logic
        return Arrays.asList(new Item[]{ new Item(5, 3, "Blade of Tessting", "I hate javaafjajaja", 100, 30), new Item(5, 3, "Second blade of Tessting", "I hate javaafjajaja", 100, 5)});
    }
}
