package NameHere.Interacts;

import NameHere.*;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Battle extends Interactable {
    private static void Sleep(double s) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) s * 1000);
        } catch (InterruptedException e) {
            System.out.println(Colors.RED_BOLD + "You cannot quit at this time." + Colors.RESET);
        }
    }

    @Override
    public void onChoose(Player p) {
        for (Item i : p.getInventory()) {
            p.setBattleHp(p.getHp() + i.getHpIncr());
            p.setDmg(p.getDmg() + i.getDmgIncr());
            p.setHealAmount(p.getHealAmount() + i.getHealIncrease());
            p.setHealVariance(p.getHealVariance() + i.getHealVariance());
        }
        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> spawns = getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, 3);//Maybe make an amount of enemies in environment?
        try {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.set(i, enemies.get(i).getClass().getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            System.out.println("Failed to create a new enemy object, check your cnstr");
        }
        System.out.print(Colors.CLEAR);
        System.out.print(Colors.RED);
        System.out.println("A battle is starting!");
        System.out.print(Colors.RESET);
        Sleep(1);
        System.out.print(Colors.CLEAR);
        while (enemies.size() > 0) {
            while (Actions > 0) {
                for (Enemy enemy : enemies) {
                    System.out.print(Colors.RED + enemy.getName() + "  ");
                }
                System.out.println();
                //TODO: add a check if the health exceeds the text length of the char so the names spread out
                for (Enemy enemy : enemies) {
                    for (int i = 0; i < enemy.getName().length(); i++) {
                        if ((enemy.getName().length() - (Integer.toString(enemy.getBattleHp()).length() + 2)) / 2 < 1) {
                            System.out.print(" " + enemy.getBattleHp() + "hp" + " ");
                            break;
                        }
                        else if (i ==
                                 (((enemy.getName().length()) - (Integer.toString(enemy.getBattleHp()).length() + 2)) /
                                  2)) {
                            System.out.print(enemy.getBattleHp() + "hp");
                            i += 4;
                        }
                        System.out.print(" ");
                    }
                    System.out.print(" ");
                }

                System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Heal");
                System.out.println("[3] Info" + Colors.RESET);
                int choice = Helper.getInput(Colors.RESET + "Current Health: " + p.getBattleHp(), 3);
                switch (choice) {
                    //#region case1
                    case 1://attack
                        System.out.println(Colors.CLEAR);
                        for (int i = 0; i < enemies.size(); i++) {
                            System.out.println(Colors.PURPLE + "[" + (i + 1) + "] " + enemies.get(i).getName());
                            System.out.print(Colors.RESET);
                        }
                        choice = Helper.getInput("\nPlayer " + p.getBattleHp() + "hp: ", enemies.size());
                        System.out.println(Colors.CLEAR);
                        if (r.nextInt(20 / enemies.get(choice - 1).getDodgeRate()) != 1) {
                            int pDamage = Main.currentPlace.modifyPlayerDamage(p.getDmg());
                            enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() -pDamage );
                            System.out.println("Dealt " + Colors.RED_BOLD + pDamage + Colors.RESET + " damage to " +
                                               enemies.get(choice - 1).getName());
                            Sleep(0.5);
                            if (enemies.get(choice - 1).getBattleHp() <= 0) {
                                System.out.println(enemies.get(choice - 1).getName() + " has been killed!");
                                enemies.get(choice - 1).randDrops(p);
                                p.addMoney(enemies.get(choice - 1).getCoins());
                                System.out.println(
                                        "You gained " + enemies.get(choice - 1).getCoins() + Colors.CYAN + "◊" +
                                        Colors.RESET);
                                enemies.remove(choice - 1);
                            }
                            Sleep(1);
                            break;
                        }
                        else {
                            System.out.println(enemies.get(choice - 1).getName() + " dodged your attack!");
                            break;
                        }
                        //#endregion
                        //#region case2
                    case 2:

                        int healAmount =
                                p.getHealAmount() + ((r.nextInt(p.getHealVariance()) == 1 ? -1 : 1));
                        if (p.getBattleHp() + healAmount>p.getHp()){
                            healAmount = 0;
                        }
                        p.setBattleHp(p.getBattleHp() + healAmount);
                        System.out.print(Colors.CYAN + "You healed for " + healAmount);
                        Sleep(1);
                        break;
                    //#endregion
                    //#region case3
                    case 3:
                        inv(enemies);
                        continue;
                        //#endregion
                }
                Main.currentPlace.playerAction(p);
                if (enemies.size() > 0) {
                    Actions--;
                }
                else {
                    break;
                }
                System.out.println(Colors.CLEAR);
            }

            System.out.println(Colors.CLEAR + Colors.RED);
            for (Enemy enemy : enemies) {
                int damage = enemy.Attack(p, enemies);
                p.takeDamage(Main.currentPlace.modifyEnemyDamage(damage));
                Sleep((double) enemies.size() / 3);

            }
            if (p.getBattleHp() <= 0) {
                System.out.println("You lost!");
                IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                        enemies::remove); //the magic of intellij
            }
            Sleep(1.4);
            System.out.println(Colors.RESET + Colors.CLEAR);
            Main.currentPlace.turnEnd(p, enemies);
            Actions = p.getActionAmount();
        }
        if (p.getBattleHp() > 0) {
            //TODO drops
            System.out.println("You won!");
        }
        for (Item i : p.getInventory()) {
            p.setHp(p.getHp() - i.getHpIncr());
            p.setDmg(p.getDmg() - i.getDmgIncr());
            p.setHealAmount(p.getHealAmount() - i.getHealIncrease());
            p.setHealVariance(p.getHealVariance() - i.getHealVariance());
        }
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Sleep(1);

    }

    public void inv(List<Enemy> enemies) {

        System.out.println(Colors.PURPLE + "[0] Go Back");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("[" + (i + 1) + "] Inspect " + enemies.get((i)).getName());
        }

        System.out.println("[" + (enemies.size() + 1) + "] Enviroment Info" + Colors.RESET);
        int choiceInfo = Helper.getInput("", 0, enemies.size() + 1);
        if (choiceInfo == 0) {
            return;
        }
        else if (choiceInfo == (enemies.size() + 1)) {
            System.out.println(
                    "Current Location: " + Main.currentPlace.getName() + "\n" + Main.currentPlace.getDescription());
            Helper.Prompt("Press Enter");
        }
        else if (choiceInfo > 0 && choiceInfo < enemies.size() + 1) {
            System.out.println(enemies.get(choiceInfo - 1).getName() + ":");
            System.out.println(
                    Colors.RED_BRIGHT + "Max Health: " + enemies.get((choiceInfo - 1)).getBaseHp() + Colors.RESET);
            System.out.println(Colors.RED_BOLD + "Damage: " + enemies.get(choiceInfo - 1).getDamage() + Colors.RESET);
            System.out.println(
                    Colors.RED_BRIGHT + "Current Health: " + enemies.get(choiceInfo - 1).getBattleHp() + Colors.RESET);
            Helper.Prompt(Colors.PURPLE + "\nPress Enter" + Colors.RESET);
        }
        inv(enemies);

    }  //TODO get location + opponent info

    public List<Enemy> getEnemies(Player p) {
        List<Enemy> returned = new ArrayList<Enemy>();
        for (Enemy e : Main.allEnemies) {
            if (e.canSpawn(p)) {
                returned.add((e));
            }
        }
        return returned;
    }

    @Override
    public String getName() {
        return "Battle";
    }


}
