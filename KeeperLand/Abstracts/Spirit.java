package KeeperLand.Abstracts;

import KeeperLand.Main;

public abstract class Spirit extends Enemy {
    public Spirit() {
        this.setBaseStats();
        scaleStats(this);
        if (!Main.allSpirits.contains(this)) {
            Main.allSpirits.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
    }
}
