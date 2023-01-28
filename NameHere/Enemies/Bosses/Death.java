package NameHere.Enemies.Bosses;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Colors;
import NameHere.Enemies.Lava.DeathMinion;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.util.List;

public class Death extends Boss{
    public void setBaseStats() {
        this.baseHp = 100;
        this.damage = 20;
        this.xp = 100;
        this.name = "DEATH";
        this.coins = 50;
        this.tokens = 1;
    }

    @Override
    public boolean canSpawn(Player p) {
        return (p.getStageNum() % 10 == 0);
    }
    @Override
    public void onDeath(Player p, List<Enemy> allies){
        Helper.Sleep(1);

        System.out.println(Colors.CLEAR + Colors.BLACK + "NO, YOU " + Colors.RED + "CANNOT DEFEAT ME!" + Colors.BLACK + " I SHALL " + Colors.RED + "BRING YOU DOWN " + Colors.BLACK + " EVEN IF I SACRIFICE " + Colors.BLACK_BACKGROUND + Colors.RED_BOLD + "MY SELF" +
                           Colors.RESET);
        for (int i = 0; i < Main.r.nextInt(4, 6); i++) {
            allies.add(new DeathMinion());
        }
        Helper.contiuePrompt();
        System.out.println(Colors.CLEAR);
    }
    @Override
    public void bossOnSpawn(List<Enemy> allies) {
    }
}
