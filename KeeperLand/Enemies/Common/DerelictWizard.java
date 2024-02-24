package KeeperLand.Enemies.Common;


import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.Random;

public class DerelictWizard extends Enemy {
    final Random r = new Random();

    public DerelictWizard() {
        super("Has no extra abilities, what you see is what you get!");
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 22;
        this.damage = 4;
        this.xp = 20;
        this.name = "Derelict Wizard";
        this.coins = 3;
        this.mutate = Main.allMutations.get(r.nextInt(Main.allMutations.size()));
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.r.nextInt(4) == 0;

    }
}
