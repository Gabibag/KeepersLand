package KeeperLand.Abstracts;

import KeeperLand.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static KeeperLand.Main.*;

public abstract class Enemy {
    public static boolean loaded = false;
    final Random r = Main.r;
    protected int damage;
    protected int baseHp;
    protected String name;
    protected int dodgeRate = 1;
    protected int xp;
    protected List<Item> drops = new ArrayList<>();
    protected int battleHp;
    protected int coins;
    protected int tokens;
    protected int level;
    protected String description;
    protected Mutations mutate;

    public Enemy(String desrc) {
        this.description = desrc;
        this.setBaseStats();
        try {
            int rand = r.nextInt(100);
            if (rand == 99) {
                rand = 3;
            } else if (rand >= 90) {
                rand = 2;
            } else if (rand >= 75) {
                rand = 1;
            } else {
                rand = 0;
            }
            rand *= r.nextBoolean() ? 1 : -1;

            this.level = player.getStageNum() + rand;
            if (this.level < 1) this.level = 1;
        } catch (Exception e) {
            this.level = 1;
        }
        scaleStats();
        if (!allEnemies.contains(this)) {
            allEnemies.add((this)); //adds all enemies to a list
        }
        this.battleHp = this.baseHp;
        //to prevent errors with the list being static sized
        this.drops = new ArrayList<>(this.drops);
        if (player.getStageNum() > 10) {
            this.drops.add(ItemData.OmegaShard);
        }
        //random 1 in 20 chance to mutate
        if (r.nextInt(20) == 0) {
            this.mutate = allMutations.get(r.nextInt(allMutations.size()));
        }
    }

    public static String isAn(String str) {
        return (str.matches("^[aeiou].*") ? "an " : "a ");
    }

    /**
     * @return true if the enemy is not a basic enemy and has some kind of modifier to its attack
     */
    public boolean isSpecial() {
        return this.description != "Has no extra abilities, what you see is what you get!";
    }

    public Mutations getMutate() {
        return mutate;
    }

    public void setMutate(Mutations mutate) {
        this.mutate = mutate;
    }

    public boolean isBoss() {
        return false;
    }

    public String getDescription() {
//        check if description does not end in puncation, add a period
        ArrayList<Character> punctuation = new ArrayList<>();
        punctuation.add('.');
        punctuation.add('!');
        punctuation.add('?');
        if (punctuation.contains(description.charAt(description.length() - 1))) {
            return description;
        } else {
            return description + ".";
        }
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setDrops(List<Item> drops) {
        this.drops = drops;
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

    public void setName(String name) {
        this.name = name;
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

    public void scaleStats() {
        Enemy e = this;
        e.setBaseHp((int) (e.getBaseHp() * Helper.getScaleFactor(0, e.level)));
        e.setDamage((int) (e.getDamage() * Helper.getScaleFactor(1, e.level)));
        e.setCoins((int) (e.getCoins() * Helper.getScaleFactor(2, e.level)));

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
        } else if (r.nextInt(10) == 0) {
            String drops = randDrops(p, this);
            String out = "You killed " + isAn(name) + name + "! (" + self.getCoins() + Colors.YELLOW + "◊" + Colors.RESET + ")";
            if (drops != null) {
                out += " and got " + isAn(drops) + Colors.YELLOW + drops + Colors.RESET + "!";
            }
            System.out.println(out);
        }
        p.addMoney(coins);
        player.addMoney(self.getCoins());
        p.addXp(xp);

    }

    public String randDrops(Player p, Enemy e) {
        p.addMoney(e.getCoins());
        p.addXp(e.xp);
        Item item = null;

        if (!e.getDrops().isEmpty()) {
            item = e.getDrops().get(r.nextInt(e.getDrops().size()));
        }
        if (item == null) {
            return null;
        }
        p.addInventory(item);
        return item.getName();
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "\ndamage=" + damage +
                ",\nbaseHp=" + baseHp +
                ",\nname='" + name + '\'' +
                ",\ndodgeRate=" + dodgeRate +
                ",\nxp=" + xp +
                ",\nbattleHp=" + battleHp +
                ",\ncoins=" + coins +
                ",\ntokens=" + tokens +
                ",\nlevel=" + level +
                "\n}";
    }
}