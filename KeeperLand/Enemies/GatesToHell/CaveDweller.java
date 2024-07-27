package KeeperLand.Enemies.GatesToHell;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class CaveDweller extends Enemy {
    Random r = Main.r;

    public CaveDweller() {
        super("Has no extra abilities, what you see is what you get!", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 30;
        this.damage = 2;
        this.xp = 20;
        this.name = "Cave Dweller";
        this.battleHp = baseHp;
        this.coins = 3;
    }


}
