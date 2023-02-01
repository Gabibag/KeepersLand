package NameHere.Abstracts;

import NameHere.Main;

public abstract class Spirit extends Enemy{
    public Spirit() {
        this.setBaseStats();
        scaleStats();
        Main.allSpirits.add((this)); //adds all enemies to a list
        this.battleHp = this.baseHp;
    }
}
