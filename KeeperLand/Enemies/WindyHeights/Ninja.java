package KeeperLand.Enemies.WindyHeights;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Main;

import java.util.Random;

public class Ninja extends Enemy {
    Random r = Main.r;

    public Ninja() {
        super("Dodges your attacks.", false);
    }


    @Override
    public void setBaseStats() {
        this.baseHp = 12;
        this.damage = 12;
        this.xp = 22;
        this.name = "Ninja";
        this.battleHp = baseHp;
        this.coins = 4;
        this.dodgeRate = 5;
    }


}
