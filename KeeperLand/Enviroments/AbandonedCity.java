package KeeperLand.Enviroments;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Environment;
import KeeperLand.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbandonedCity extends Environment {

    @Override
    public String getDescription() {
        return "A destroyed city, full of mutated monsters. \nIt appears to have been destroyed by a nuclear blast and some of the radiation is still around.";
    }

    @Override
    public List<Item> getShopItems() {
        return Arrays.asList(ItemData.Revolver, ItemData.Meds, ItemData.RadioactiveRod, ItemData.RadiationSuit);
    }

    @Override
    public String getName() {
        return "Abandoned City";
    }

    @Override
    public void playerAction(Player p, List<Enemy> enemies) {
    }

    @Override
    public void turnEnd(Player p, List<Enemy> enemies) {
        if (Main.r.nextFloat() > 0.8f) {
            Enemy m = Helper.getRandomElements(enemies, 1).get(0);
            System.out.println("The city's radiation mutates an " + m.getName() + ", splitting it into 2");
            enemies.remove(m);
            try {
                Enemy m1 = m.getClass().getConstructor().newInstance();
                Enemy m2 = m.getClass().getConstructor().newInstance();
                m1.setBattleHp(m.getBattleHp() / 2);
                m2.setBattleHp(m.getBattleHp() / 2);

                enemies.add(m1);
                enemies.add(m2);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException
                     | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Enemy> allowedEnemies() {
        if (!this.allowedEnemies.isEmpty()) return this.allowedEnemies;
        ArrayList<Enemy> enemies = new ArrayList<>(super.allowedEnemies());
        enemies.add(new KeeperLand.Enemies.Toxic.Snake());
        enemies.add(new KeeperLand.Enemies.Toxic.Assassin());
        enemies.add(new KeeperLand.Enemies.Toxic.SwampMonster());
        enemies.add(new KeeperLand.Enemies.Toxic.Slime());
        enemies.add(new KeeperLand.Enemies.Toxic.Basilisk());
        this.allowedEnemies = enemies;
        return enemies;
    }

    @Override
    public int modifyPlayerDamage(int preChange) {
        Player p = Main.player;
        for (Item i : p.getInventory()) {
            if (i.getName().equals("Radiation Suit")) {
                return preChange;
            }
        }
        return preChange - 2;
    }

    @Override
    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }

}
