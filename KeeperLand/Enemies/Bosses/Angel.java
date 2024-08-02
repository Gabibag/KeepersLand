package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.*;
import KeeperLand.Enemies.Graveyard.DeathMinion;
import KeeperLand.Enemies.Sprites.HealingSprite;

import java.util.List;

public class Angel extends Boss {
    public Angel() {
        super("Drops a Healing Shard. Brings in a cute surprise on death.");

    }

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 3;
        this.xp = 120;
        this.name = "Angel";
        this.coins = 50;
        this.tokens = 1;
        this.drops.add(ItemData.HealingShard);
    }


    @Override
    public String getDodgeText() {
        return " turns your attack into light.";
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        for (int i = 0; i < 3; i++) {
            allies.add(new HealingSprite());
        }
        allies.add(new DeathMinion()); //the bringer of light is not what it seems...
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println("\n" +
                "O~~   O~~      O~~ O~~\n" +
                "O~~  O~~    O~ O~~ O~~\n" +
                "O~~ O~~        O~~ O~~\n" +
                "O~ O~      O~~ O~~ O~~\n" +
                "O~~  O~~   O~~ O~~ O~~\n" +
                "O~~   O~~  O~~ O~~ O~~\n" +
                "O~~     O~~O~~O~~~O~~~\n" +
                "                      ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                "O~~~ O~~~~~~              \n" +
                "     O~~O~~               \n" +
                "     O~~O~~        O~~    \n" +
                "     O~~O~ O~    O~   O~~ \n" +
                "     O~~O~~  O~~O~~~~~ O~~\n" +
                "     O~~O~   O~~O~        \n" +
                "     O~~O~~  O~~  O~~~~   \n" +
                "                          ");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR + Colors.YELLOW +
                "      O~                                   O~~\n" +
                "     O~ ~~                                 O~~\n" +
                "    O~  O~~    O~~ O~~     O~~      O~~    O~~\n" +
                "   O~~   O~~    O~~  O~~ O~~  O~~ O~   O~~ O~~\n" +
                "  O~~~~~~ O~~   O~~  O~~O~~   O~~O~~~~~ O~~O~~\n" +
                " O~~       O~~  O~~  O~~ O~~  O~~O~        O~~\n" +
                "O~~         O~~O~~~  O~~     O~~   O~~~~  O~~~\n" +
                "                          O~~                 ");
        Helper.Sleep(1);
        System.out.println(Colors.RESET + Colors.CLEAR);
        for (int i = 0; i < 4; i++) {
            allies.add(new HealingSprite());
        }
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //random chance to spawn a healing sprite
        int r = Main.r.nextInt(3);
        int t = Main.r.nextInt(3);
        if (r == 0 && t == 0) {
            System.out.println("The Angel " + Colors.RED + "summons" + Colors.RESET + " a " + Colors.GREEN + "Healing Sprite" + Colors.RESET);
            allies.add(new HealingSprite());
        } else if (r == 1) {
            System.out.println("The Angel " + Colors.YELLOW + "heals" + Colors.RESET + " itself for " + Colors.GREEN + (int) (this.battleHp * 0.2) + Colors.RESET + " health.");
            this.battleHp += (int) (this.battleHp * 0.2);

        }
        return super.Attack(p, allies);
    }
}
