package KeeperLand.Enemies.Toxic;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Enviroments.ToxicEnv;
import KeeperLand.Main;
import KeeperLand.Player;

import java.util.List;

public class SwampMonster extends Enemy {
    public SwampMonster() {
        super("Has no extra abilities, what you see is what you get!");
    }

    public void setBaseStats() {
        this.baseHp = 12;
        this.damage = 8;
        this.xp = 10;
        this.name = "Swamp Monster";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    public int Attack(Player p, List<Enemy> allies) {
        //TODO make this gimmick deal more damage the more you lose your health
        return this.damage;
    }

    @Override
    public boolean canSpawn(Player p) {

        return Main.currentPlace instanceof ToxicEnv; //(r.nextInt([spawnchance]) == 2)

    }
}
