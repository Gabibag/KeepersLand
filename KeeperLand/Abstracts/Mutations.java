package KeeperLand.Abstracts;

import KeeperLand.Main;

import java.util.List;

public abstract class Mutations {
    public String getMutationType() {
        return mutationType;
    }

    public void setMutationType(String mutationType) {
        this.mutationType = mutationType;
    }

    protected String mutationType;
    public Mutations(String type){
        Main.allMutations.add(this);
        this.mutationType = type;
    }


    public void onHurt(List<Enemy> e, int damage, Enemy self){

    }

    public void onHeal(List<Enemy> e, int healamt, Enemy self){

    }
    public void onKill(List<Enemy> e, Enemy self, Enemy killed){

    }

    public void onDeath(List<Enemy> e, Enemy self){

    }
}
