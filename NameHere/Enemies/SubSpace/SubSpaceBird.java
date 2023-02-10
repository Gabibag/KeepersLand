package NameHere.Enemies.SubSpace;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.SubSpace;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class SubSpaceBird extends Enemy {
    Random r = new Random();


    @Override
    public void setBaseStats() {
        this.baseHp = 10;
        this.damage = 8;
        this.xp = 20;
        this.name = "Sub Space Bird";
        this.battleHp = baseHp;
        this.coins = 3;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof SubSpace;

    }
}
