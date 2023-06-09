package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;
import KeeperLand.Main;

import java.util.List;

public class Thorny extends Mutations {
    public Thorny() {
        super("Thorny", Colors.GREEN);
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        if(self.getBattleHp() <= 0){
            return;
        }
        Main.player.setBattleHp((int) (Main.player.getBattleHp() - damage*0.2));
        System.out.println("The Thorny " +self.getName() + Colors.BLACK + " reflects " + Colors.RESET + ((int) (damage * 0.2)) + " damage to the player");
    }

    @Override
    public void onDeath(List<Enemy> e, Enemy self) {
        Main.player.setBattleHp((int) (Main.player.getBattleHp() - self.getDamage()*0.2));
        for (Enemy enemy : e) {
            if(enemy == self) {
                continue;
            }
            enemy.setBattleHp((int) (enemy.getBattleHp() - self.getDamage()*0.2));
        }
        System.out.println("The Thorny " + self.getName() + Colors.RED + " explodes on death!" + Colors.RESET + "(" + self.getCoins() + Colors.CYAN + "◊" + Colors.RESET + ")");
    }

}
