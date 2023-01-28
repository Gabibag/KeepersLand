package NameHere.Abstracts;

import java.util.List;

public abstract class Boss extends Enemy {
    protected int tokens;
    public abstract void bossOnSpawn(List<Enemy> enemies);
    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }





}

