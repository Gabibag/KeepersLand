package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;

public class Slime extends Enemy {

    public Slime() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
        this.battleHp = baseHp;
        this.drops.add(ItemData.slimeShield);
        this.coins = 2;
    }


}
