package KeeperLand.Abstracts;

import KeeperLand.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static KeeperLand.Main.allMutations;
import static KeeperLand.Main.player;

public abstract class Enemy {
    protected int damage;
    protected int baseHp;

    public void setName(String name) {
        this.name = name;
    }

    protected String name;
    protected int dodgeRate = 1;
    protected int xp;
    protected List<Item> drops = new ArrayList<>();
    protected int battleHp;
    protected int coins;
    protected int tokens;
    protected int level;

    public Mutations getMutate() {
        return mutate;
    }

    public void setMutate(Mutations mutate) {
        this.mutate = mutate;
    }

    protected Mutations mutate;
    Random r = new Random();
    public static boolean loaded = false;
    public Enemy() {
        this.setBaseStats();
        try {
            int rand = r.nextInt(100);
            if (rand >=95){
                rand = 3;
            } else if (rand >= 80) {
                rand = 2;
            }else if (rand >=50){
                rand = 1;
            }else{
                rand = 0;
            }
            rand *= r.nextBoolean() ? 1 : -1;

            this.level = Main.player.getStageNum() + rand;
            System.out.println();
            if (this.level < 1) this.level = 1;
        } catch (Exception e) {
            this.level = 1;
        }
        scaleStats(this);
        if (!Main.allEnemies.contains(this)) {
            Main.allEnemies.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
        //to prevent errors with the list being static sized
        this.drops = new ArrayList<>(this.drops);
        this.drops.add(ItemData.OmegaShard);
        //random 1 in 20 chance to mutate
        if (r.nextInt(2) == 0) {
            this.mutate = allMutations.get(r.nextInt(allMutations.size()));
        }
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }





    //create a get type method that returns the string "Enemy"
    public String getType() {
        return "Enemy";
    }

    public List<Item> getDrops() {

        return drops;
    }

    public ArrayList<Item> setDrops(List<Item> drops) {
        this.drops = drops;
        return null;
    }

    public void addDrops(Item drops) {
        this.drops.add(drops);
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(int dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    public String getDodgeText() {
        return " dodged your attack!";
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getBattleHp() {
        return battleHp;
    }

    public void setBattleHp(int battleHp) {
        this.battleHp = battleHp;
    }

    public String displayBattleHp() {
        return battleHp + "hp";
    }

    public void scaleStats(Enemy e) {
        e.setBaseHp((int) (e.getBaseHp() * Helper.getScaleFactor(0, e)));
        e.setDamage((int) (e.getDamage() * Helper.getScaleFactor(1, e)));
        e.setCoins((int) (e.getCoins() * Helper.getScaleFactor(2, e)));

//        this.xp *= Helper.getScaleFactor();
    }

    public abstract void setBaseStats();

    public abstract boolean canSpawn(Player p);

    public int Attack(Player p, List<Enemy> allies) {
        //by default, just hits for its damage

        return damage;
    }

    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        //by default, just gives xp and money
        if (mutate != null) {
            mutate.onDeath(allies, self);
        }else {
            String drops = randDrops(p, this);
            if( drops != null){
                System.out.println("You killed a " + name + "! (" + self.getCoins() + Colors.CYAN + "◊" +
                        Colors.RESET + ") and got a " + Colors.CYAN + drops + Colors.CYAN+ "!");
            }else {
                System.out.println("You killed a " + name + "! (" + self.getCoins() + Colors.CYAN + "◊" +
                        Colors.RESET + ")");
            }
        }
        p.addMoney(coins);
        player.addMoney(self.getCoins());
        p.addXp(xp);

    }

    public String randDrops(Player p, Enemy e) {
        if (r.nextInt(1, 2) == 1) {
            for (Item drop : this.drops) {
                if (r.nextInt(drop.getRarity()) == 1) {
                    p.addInventory(drop);
                    return drop.getName();
                }
            }
        } else {
            for (Item drop : Item.allPool) {
                if (r.nextInt(drop.getRarity()) == 1) {
                    p.addInventory(drop);
                    return drop.getName();
                }
            }
        }

        p.addMoney(e.getCoins());
        p.addXp(e.xp);
        return null;
    }
}