package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;

import java.util.List;

import static KeeperLand.Main.r;

public class Defensive extends Mutations {

    @Override
    public String getColor() {
        return Colors.BLACK;
    }
    public Defensive() {
        super("Defensive");
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        if(r.nextInt(5) == 1){
            self.setBattleHp(self.getBattleHp() + damage);
            System.out.println("The Defensive " + self.getName() + " shields itself from damage!");
            return;
        }
        self.setBattleHp(self.getBattleHp() + ((int) (damage * 0.2)));
        
    }
}
