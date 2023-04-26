package KeeperLand.Abstracts;

import KeeperLand.Main;

import java.util.ArrayList;
import java.util.List;

public abstract class Mutations {
    protected String mutationType;
    public Mutations(String type){
        Main.allMutations.add(this);
        this.mutationType = type;
    }


    public void onHurt(List<Enemy> e, int damage, Enemy self){

    }
    public void onDeath(List<Enemy> e, Enemy self){

    }
    public void onHeal(List<Enemy> e, int healamt, Enemy self){

    }
    public void onKill(List<Enemy> e, Enemy self, Enemy killed){

    }

    public abstract void onDeath(ArrayList<Enemy> e, Enemy self);
}
