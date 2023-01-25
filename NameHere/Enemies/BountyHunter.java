package NameHere.Enemies;
import java.util.List;

import NameHere.Player;
import NameHere.Abstracts.Enemy;


public class BountyHunter extends Enemy{

    public BountyHunter() {
        super();
        this.baseHp = 20;
        this.damage = 3;
        this.xp = 20;
        this.name = "Bounty Hunter";
    }

    @Override
    public boolean canSpawn(Player p) {
        return true;
    }
}
