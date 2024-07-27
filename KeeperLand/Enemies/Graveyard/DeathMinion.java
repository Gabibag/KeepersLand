package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.ItemData;
import KeeperLand.Main;

public class DeathMinion extends Enemy {

    public DeathMinion() {
        super("Has no extra abilities, what you see is what you get!", false);
    }

    public void setBaseStats() {
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 10;
        this.name = "Death's Minion";
        this.battleHp = baseHp;
        this.coins = 3;
        drops.add(ItemData.soul);
    }

    @Override
    public boolean canSpawn() {

        return (Main.r.nextInt(10) == 2); //(r.nextInt([spawnchance]) == 2)

    }
}
