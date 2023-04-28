package KeeperLand.Abstracts;

import KeeperLand.Main;

public abstract class Sprite extends Enemy {
    public Sprite() {
        this.setBaseStats();
        this.scaleStats(this);
        if (!Main.allSprites.contains(this)) {
            Main.allSprites.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
    }
}
