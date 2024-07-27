package KeeperLand.Abstracts;

import KeeperLand.Main;

public abstract class Sprite extends Enemy {
    @Override
    public boolean isSpecial() {
        return true;
    }

    public Sprite(String description) {
        super(description, false);
        this.setBaseStats();
        if (!Main.allSprites.contains(this)) {
            Main.allSprites.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
    }

    @Override
    public boolean canSpawn() {
        return (Main.r.nextInt(30) == 2);
    }
}
