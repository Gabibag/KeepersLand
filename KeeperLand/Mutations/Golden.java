package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;
import KeeperLand.Main;

import java.util.List;

public class Golden extends Mutations {

    public Golden() {
        super("Golden", Colors.YELLOW);
    }

    @Override
    public void onDeath(List<Enemy> e, Enemy self){
        //drop double coins
        Main.player.addMoney(self.getCoins() << 1);
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        System.out.println("The Golden " + self.getName() + " drops " + Colors.YELLOW + (int)(self.getCoins() * 0.5) +  "◊!" + Colors.RESET);
        Main.player.addMoney((int) (self.getCoins() * 0.5));
    }
}
