package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Colors;
import KeeperLand.Main;
import KeeperLand.StatusEffects.Poison;

import java.util.List;

public class Toxic extends Mutations {

    public Toxic() {
        super("Toxic", Colors.GREEN_BRIGHT);
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        //random 1 in 4 chance to poison
        if (Math.random() < 0.25) {
            Main.player.addStatusEffects(new Poison());
            System.out.println("The Toxic " + self.getName() + " poisons you!");
        }
    }
}
