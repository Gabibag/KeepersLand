package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;
import KeeperLand.Main;
import KeeperLand.StatusEffects.Poison;

import java.util.List;

public class Toxic extends Mutations {

    public Toxic() {
        super("Toxic");
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        //random 1 in 4 chance to poison
        if (Math.random() < 0.25 && !Main.player.getStatusEffects().contains(new Poison())) { //TODO make the poison check actually work
            Main.player.addStatusEffects(new Poison());
            System.out.println("The Toxic " + self.getName() + " poisons you!");
        }
    }
}
