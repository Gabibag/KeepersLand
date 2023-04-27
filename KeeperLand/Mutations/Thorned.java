package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;
import KeeperLand.Main;

import java.util.ArrayList;
import java.util.List;

public class Thorned extends Mutations {
    public Thorned() {
        super("Thorned");
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        Main.player.setBattleHp((int) (Main.player.getBattleHp() - damage*0.2));
        System.out.println("The Thorned " +self.getName() + Colors.BLACK + " reflects " + Colors.RESET + damage*0.2 + " damage to the player");
    }

    @Override
    public void onDeath(ArrayList<Enemy> e, int damage, Enemy self) {
        Main.player.setBattleHp((int) (Main.player.getBattleHp() - self.getDamage()*0.2));
        for (Enemy enemy : e) {
            if(enemy == self) {
                continue;
            }
            enemy.setBattleHp((int) (enemy.getBattleHp() - self.getDamage()*0.2));
        }
        System.out.println("The Thorned " + self.getName() + Colors.RED + "explodes." + Colors.RESET);
    }

}
