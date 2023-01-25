import java.util.Arrays;
import java.util.List;

public class Shop extends Interactable {
    public void OnChoose(Player player) {
        System.out.println("Welcome to the shop, " + player.getName() + ". \nYou have " + player.getMoney() + " Coins");
        while (true) {
            List<Item> items = getItems(player);
            for (int i = 0; i < items.size(); i++)
                System.out.println(
                        "[" + (i + 1) + "] " + items.get(i).getName() + " ($" + items.get(i).getCost() + ")");
            int choice = Main.getInput("[-1] Quit \nEnter your choice:");
            if (choice == -1) {
                return;
            }
            Item i = items.get(-1 + choice);
            if (i.getCost() > player.getMoney()) {
                System.out.println("Not enough money");
            }
            else {
                player.getInventory().add(i);
                player.chargeMoney(i.getCost());
                System.out.println("Bought " + i.getName() + " for " + i.getCost() + " \n new bal: " + player.getMoney());
            }
        }
    }

    public List<Item> getItems(Player p) {
        //TODO logic
        return Arrays.asList(
                new Item(5, 3,
                         "Blade of Tessting",
                         "I hate javaafjajaja",
                         100, 30),
                new Item(5, 3,
                         "Second blade of Tessting",
                         "I hate javaafjajaja",
                         100, 5));
    }
}
