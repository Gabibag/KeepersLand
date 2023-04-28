package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;

import java.util.List;

import static KeeperLand.Main.player;
import static KeeperLand.Main.r;

public class Explosive extends Mutations {

    public Explosive() {
        super("Explosive");
    }

    @Override
    public void onDeath(List<Enemy> e, Enemy self) {
        System.out.println("The Explosive " + self.getName() + " explodes on death!" + "(" + self.getCoins() + Colors.CYAN + "◊" + Colors.CYAN + ")");
        for (Enemy enemy : e) {
            if(enemy == self) {
                continue;
            }
            enemy.setBattleHp((int) (enemy.getBattleHp() - self.getBaseHp()*0.2));
        }
        player.setBattleHp((int) (player.getBattleHp() - self.getBaseHp()*0.2));
        self.setBattleHp(0);
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        if(self.getBattleHp() <= 0){
            return;
        }
        //random 1 in 5 chance to explode
        int rand = r.nextInt(20 );
        if (rand == 19){
            self.setBattleHp(0);
        }
        if(rand <= 4){
            System.out.println("The Explosive " + self.getName() + " fragments on being hit!" + "(" + self.getCoins() + Colors.CYAN + "◊" + Colors.CYAN + ")");
            for (Enemy enemy : e ) {
                enemy.setBattleHp((int) (enemy.getBattleHp() - self.getDamage()*0.4));
            }

            player.setBattleHp((int) (player.getBattleHp() - self.getDamage()*0.4));
        }
    }
}
