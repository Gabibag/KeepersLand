package KeeperLand.Abstracts;

import KeeperLand.Main;
import KeeperLand.Player;

public abstract class Sprite extends Enemy {
    public Sprite() {
        this.setBaseStats();
//        this.scaleStats();
        if (!Main.allSprites.contains(this)) {
            Main.allSprites.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.r.nextInt(30) == 2;
    }
}
