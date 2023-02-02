package NameHere.Interacts;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;
import NameHere.Enemies.Bosses.Death;
import NameHere.Enemies.Bosses.DemonLord;
import NameHere.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Battle extends Interactable {


    private static void updateItems(Player p, boolean battleEnd) {
        if (!battleEnd) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getBattleHp() + i.getHpIncr());
                p.setDmg(p.getDmg() + i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() + i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() + i.getHealVariance());
            }
        }else  {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getHp() - i.getHpIncr());
                p.setDmg(p.getDmg() - i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() - i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() - i.getHealVariance());
            }
        }
    }

    @Override
    public void onChoose(Player p) {
        p.setBattleHp(p.getHp());
        updateItems(p,false);
        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> spawns = getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, (p.getStageNum()%10 == 0 ? 1 : 3));//only spawns 1 boss


        try {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.set(i, enemies.get(i).getClass().getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            System.out.println("Failed to create a new enemy object, check your cnstr");
        }
        System.out.println(Colors.RED+"A battle is starting!" + Colors.RESET);
        Helper.Sleep(1);
        System.out.print(Colors.CLEAR);
        if(p.getStageNum()%10 == 0){
            try {
                ((Boss)(enemies.get(0))).bossOnSpawn(enemies);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(p.getName().equals("among us")){
            enemies.clear();
            enemies.add(new Death());
        }
        while (enemies.size() > 0) {
            while (Actions > 0) {
                for (Enemy enemy : enemies) {
                    System.out.print(Colors.RED + enemy.getName() + "  ");
                }

                System.out.println();
                //TODO: add a check if the health exceeds the text length of the char so the names spread out
                for (Enemy enemy : enemies) {
                    for (int i = 0; i < enemy.getName().length(); i++) {
                        if ((enemy.getName().length() <= 4)) {
                            System.out.print(" " + enemy.getBattleHp() + "hp");
                            for (int j = 0; j < enemy.getName().length() - 4; ) {
                                System.out.print(" ");
                            }
                            break;
                        }
                        else if ((enemy.getName().length() - (Integer.toString(enemy.getBattleHp()).length() + 2)) / 2 <
                                 1) {
                            System.out.print(enemy.getBattleHp() + "hp" + " ");
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
                    System.out.print("  ");
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
                        if (enemies.size()>1) {
                            for (int i = 0; i < enemies.size(); i++) {
                                System.out.println(Colors.PURPLE + "[" + (i + 1) + "] " + enemies.get(i).getName());
                                System.out.print(Colors.RESET);
                            }
                            choice = Helper.getInput("\nPlayer " + p.getBattleHp() + "hp: ", enemies.size());
                        }

                        System.out.println(Colors.CLEAR);
                        if (r.nextInt(20 / enemies.get(choice - 1).getDodgeRate()) != 0) {
                            int pDamage = Main.currentPlace.modifyPlayerDamage(p.getDmg());
                            enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() - pDamage);
                            System.out.println("Dealt " + Colors.RED_BOLD + pDamage + Colors.RESET + " damage to " +
                                               enemies.get(choice - 1).getName());
                            Helper.Sleep(0.5);
                            if (enemies.get(choice - 1).getBattleHp() <= 0) {
                                enemies.get(choice - 1).onDeath(p, enemies);
                                System.out.println(enemies.get(choice - 1).getName() + " has been killed!");
                                enemies.get(choice - 1).randDrops(p, enemies.get(choice-1));
                                p.addMoney(enemies.get(choice - 1).getCoins());
                                System.out.println(
                                        "You gained " + enemies.get(choice - 1).getCoins() + Colors.CYAN + "◊" +
                                        Colors.RESET);
                                enemies.remove(choice - 1);
                            }
                        }
                        else {
                            System.out.println(enemies.get(choice - 1).getName() + " dodged your attack!");
                        }
                        Helper.Sleep(1);
                        break;
                    //#endregion
                        //#region case2
                    case 2:

                        int healAmount =
                                p.getHealAmount() + (r.nextInt(p.getHealVariance()));
                        if (p.getBattleHp() + healAmount > p.getHp()) {
                            healAmount = 0;
                        }
                        p.setBattleHp(p.getBattleHp() + healAmount);
                        System.out.print(Colors.CYAN + "You healed for " + healAmount);
                        Helper.Sleep(1);
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
                int damage = 0;
                if(enemy instanceof Boss){
                    damage = ((Boss)enemy).BossAttack(p, enemies);
                }
                else{
                damage = enemy.Attack(p, enemies);
                }
                p.takeDamage(Main.currentPlace.modifyEnemyDamage(damage));
                Helper.Sleep(enemies.size()>=4 ? 0.5 : 1);

            }

            if (p.getBattleHp() <= 0) {
                System.out.println("You lost!");
                IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                        enemies::remove); //the magic of intellij
            }else{
                Helper.contiuePrompt();
            }
            Helper.Sleep(1.4);
            System.out.println(Colors.RESET + Colors.CLEAR);
            Main.currentPlace.turnEnd(p, enemies);
            Actions = p.getActionAmount();
        }
        if (p.getBattleHp() > 0) {
            //TODO drops
            System.out.println("You won!");
            p.incStageNum(1);

        }
        updateItems(p,true);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Helper.Sleep(1);

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

    public static List<Enemy> getEnemies(Player p) {
        List<Enemy> returned = new ArrayList<>();
        for (Enemy e : Main.allEnemies) {
            if (e.canSpawn(p)) {
                if (p.getStageNum() % 10 == 0){
                    if (e instanceof Boss) {
                        returned.add(e);
                    }
                }
                else if (!(e instanceof Boss)) {
                returned.add((e));
            }
            }

        }

        return returned;
    }



    @Override
    public String getName() {
        return "Battle";
    }


}
