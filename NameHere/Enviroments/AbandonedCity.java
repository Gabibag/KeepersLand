package NameHere.Enviroments;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import NameHere.Colors;
import NameHere.Helper;
import NameHere.Item;
import NameHere.ItemData;
import NameHere.Main;
import NameHere.Player;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Enemies.Sprites.HealingSprite;

public class AbandonedCity  extends Enviorment{

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
    public void playerAction(Player p) {
    }
    @Override
    public void turnEnd(Player p, List<Enemy> enemies) {
        if(Main.r.nextFloat() > 0.8f){
        Enemy m = Helper.getRandomElements(enemies, 1).get(0);
        System.out.println("The city's radiation mutates an " + m.getName() + ", splitting it into 2");
        enemies.remove(m);
        try {
            Enemy m1 = m.getClass().getConstructor().newInstance();
            Enemy m2 = m.getClass().getConstructor().newInstance();
            m1.setBattleHp(m.getBattleHp()/2);
            m2.setBattleHp(m.getBattleHp()/2);

        enemies.add(m1);
        enemies.add(m2);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        }
    }


    @Override
    public int modifyPlayerDamage(int preChange) {
        Player p = Main.player;
        for (Item i  : p.getInventory()) {
            if(i.getName().equals("Radiation Suit")){
                return preChange;
            }
        }
        System.out.println(Colors.GREEN + "You are exposed to the city's radiation and deal 2 less damage, you should buy a radiation suit"+ Colors.RESET);
        return preChange -2;
    }

    @Override
    public int modifyEnemyDamage(int preChange) {
        return preChange;
    }
    
}
