package NameHere.Enemies.Toxic;

import NameHere.Item;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Enviroments.ToxicEnv;

public class Slime extends Enemy{

    public Slime() {
        super();
        this.baseHp = 25;
        this.damage = 2;
        this.xp = 20;
        this.name = "Slime";
        this.battleHp = baseHp;
        this.drops.add(Item.slimeShield);
        this.coins = 2;
    }

    @Override
    public boolean canSpawn(Player p) {
        return Main.currentPlace instanceof ToxicEnv;
    }
}
