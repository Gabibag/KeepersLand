public abstract class Enemy{ 
    public Enemy(){
        Main.allEnemies.add((this));
    }
    public abstract boolean canSpawn(Player p);
}