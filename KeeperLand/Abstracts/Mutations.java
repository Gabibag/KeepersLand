package KeeperLand.Abstracts;

import KeeperLand.Main;

import java.util.List;

public abstract class Mutations {
    protected String mutationType;
    protected final String color;

    public Mutations(String type, String c) {
        Main.allMutations.add(this);
        this.mutationType = type;
        this.color = c;
    }

    public String getColor() {
        return color;
    }

    public String getMutationType() {
        return mutationType;
    }

    public void setMutationType(String mutationType) {
        this.mutationType = mutationType;
    }

    public void onHurt(List<Enemy> e, int damage, Enemy self) {

    }

    public void onHeal(List<Enemy> e, int healamt, Enemy self) {

    }

    public void onKill(List<Enemy> e, Enemy self, Enemy killed) {

    }

    public void onDeath(List<Enemy> e, Enemy self) {

    }


}
