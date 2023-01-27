package NameHere.Enemies.Lava;

import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.LavaZone;
import NameHere.Main;
import NameHere.Player;

import java.util.Random;

public class Overlord extends Enemy {
    Random r = new Random();

    public Overlord() {
        super();
        this.baseHp = 100;
        this.damage = 20;
        this.xp = 100;
        this.name = "Overlord";
        this.battleHp = baseHp;
        this.coins = 100;
    }

    @Override
    public boolean canSpawn(Player p) {

        return r.nextInt(50) == 2 && Main.currentPlace instanceof LavaZone; //(r.nextInt([spawnchance]) == 2)

    }
}
