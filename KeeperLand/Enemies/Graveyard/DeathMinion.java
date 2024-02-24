package KeeperLand.Enemies.Graveyard;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.Graveyard;
import KeeperLand.ItemData;
import KeeperLand.Main;
import KeeperLand.Player;

public class DeathMinion extends Enemy {

    public DeathMinion() {
        super("Has no extra abilities, what you see is what you get!");
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
    public boolean canSpawn(Player p) {

        return (Main.r.nextInt(10) == 2) && Main.currentPlace instanceof Graveyard; //(r.nextInt([spawnchance]) == 2)

    }
}
