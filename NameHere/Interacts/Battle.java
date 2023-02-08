package NameHere.Interacts;

import NameHere.Abstracts.Boss;
import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Interactable;
import NameHere.*;
import NameHere.Enemies.Bosses.DemonLord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static NameHere.Main.player;

public class Battle extends Interactable {


    static void updateItems(Player p, boolean battleEnd) {
        if (!battleEnd) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getBattleHp() + i.getHpIncr());
                p.setBattleDamage(p.getDamage() + i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() + i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() + i.getHealVariance());
            }
        }
        else {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getHp() - i.getHpIncr());
                p.setBattleDamage(p.getDamage() - i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() - i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() - i.getHealVariance());
            }
        }
    }

    public static List<Enemy> getEnemies(Player p) {

        List<Enemy> returned = new ArrayList<>();
        for (Enemy e : Main.allEnemies) {
            if (e.canSpawn(p)) {
                if (p.getStageNum() % 10 == 0) {
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

    //create a static method that removes all enemies in the list given that has a battleHp that is less than 0
    public static void removeDead(List<Enemy> enemies) {
        for (Enemy choice : enemies) {
            if (choice.getBattleHp() <= 0) {
                choice.onDeath(player, enemies);
                System.out.println(choice.getName() + " has been killed!");
                choice.randDrops(player, choice);
                player.addMoney(choice.getCoins());
                System.out.println(
                        "You gained " + choice.getCoins() + Colors.CYAN + "◊" +
                        Colors.RESET);
                enemies.remove(choice);
            }
        }
    }

    @Override
    public void onChoose(Player p) {
        int tempMaxHp = p.getHp();
        for (Item i : p.getInventory()) {
            tempMaxHp += i.getHpIncr();
        }
        p.setBattleHp(p.getHp());
        updateItems(p, false);
        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> spawns = getEnemies(p);
        List<Enemy> enemies = Helper.getRandomElements(spawns, (p.getStageNum() % 10 == 0 ? 1 : 7));//only spawns 1 boss


        try {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.set(i, enemies.get(i).getClass().getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            System.out.println("Failed to create a new enemy object, check your cnstr type:");
            e.printStackTrace();
      }
        System.out.println(Colors.RED + "A battle is starting!" + Colors.RESET);
        Helper.Sleep(1);
        System.out.print(Colors.CLEAR);
        if (p.getStageNum() % 10 == 0) {
            try {
                ((Boss) (enemies.get(0))).bossOnSpawn(enemies);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        while (enemies.size() > 0) {
            removeDead(enemies);
            //tell user their stage number and enviorment
            System.out.println("You are in the " + Main.currentPlace.getName() + Colors.RESET);
            while (Actions > 0) {
                System.out.println();
                //TODO: add a check if the health exceeds the text length of the char so the names spread out
                printHealth(enemies, p);
                System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Heal");
                System.out.println("[3] Info" + Colors.RESET);
                int choice = Helper.getInput(Colors.RESET + "Current Health: " + p.getBattleHp(), 3);
                switch (choice) {
                    //#region case1
                    case 1 -> {//attack
                        System.out.println(Colors.CLEAR);
                        if (enemies.size() > 1) {
                            for (int i = 0; i < enemies.size(); i++) {
                                System.out.println(Colors.PURPLE + "[" + (i + 1) + "] " + enemies.get(i).getName());
                                System.out.print(Colors.RESET);
                            }
                            choice = Helper.getInput("\nPlayer " + p.getBattleHp() + "hp: ", enemies.size());
                        }
                        System.out.println(Colors.CLEAR);
                        if (r.nextInt(25 / enemies.get(choice - 1).getDodgeRate()) != 0) {
                            int pDamage = Main.currentPlace.modifyPlayerDamage(p.getBattleDamage());
                            enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() - pDamage);
                            System.out.println("Dealt " + Colors.RED_BOLD + pDamage + Colors.RESET + " damage to " +
                                               enemies.get(choice - 1).getName());

                            if (enemies.get(choice - 1).getBattleHp() <= 0) {
                                enemies.get(choice - 1).onDeath(p, enemies);
                                System.out.println(enemies.get(choice - 1).getName() + " has been killed!");
                                enemies.get(choice - 1).randDrops(p, enemies.get(choice - 1));
                                p.addMoney(enemies.get(choice - 1).getCoins());
                                System.out.println(
                                        "You gained " + enemies.get(choice - 1).getCoins() + Colors.CYAN + "◊" +
                                        Colors.RESET);
                                enemies.remove(choice - 1);
                            }
                            Helper.contiuePrompt();
                        }
                        else {
                            System.out.println(enemies.get(choice - 1).getName() + " dodged your attack!");
                            Helper.Sleep(1);
                        }
                    }
                    //#endregion
                    //#region case2
                    case 2 -> {
                        try {
                            int healAmount =
                                    (p.getHealAmount() + (r.nextInt(p.getHealVariance() << 1) - p.getHealVariance()))/(tempMaxHp / p.getHp());
                            if (healAmount + p.getBattleHp() >= tempMaxHp) {
                                healAmount = tempMaxHp - p.getBattleHp();
                            }
                            p.setBattleHp(p.getBattleHp() + healAmount);
                            System.out.print(Colors.RED + ((healAmount + p.getBattleHp() ==
                                                            tempMaxHp) ? "You healed to full health" :
                                    "You healed " + healAmount + " health"));
                        } catch (Exception e) {
                            System.out.println("You are at your max health");
                        }
                        Helper.Sleep(0.4);
                    }
                    //#endregion
                    //#region case3
                    case 3 -> {
                        inv(enemies);
                        continue;
                    }
                    //#endregion
                }
                Main.currentPlace.playerAction(p);
                if (enemies.size() > 0) {
                    Actions--;
                }
                else {
                    p.setActionAmount(2);
                    break;
                }
                System.out.println(Colors.CLEAR);
            }

            System.out.println(Colors.CLEAR + Colors.RED);
            for (Enemy enemy : enemies) {
                int damage = 0;
                if (enemy instanceof Boss) {
                    damage = ((Boss) enemy).BossAttack(p, enemies);
                }
                else {
                    damage = enemy.Attack(p, enemies);
                }
                p.takeDamage(Main.currentPlace.modifyEnemyDamage(damage));
//                Helper.Sleep(enemies.size()>=4 ? 0.5 : 1);

            }
            Helper.contiuePrompt();
            if (p.getBattleHp() <= 0) {
                System.out.println("You lost!");
                IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                        enemies::remove); //the magic of intellij
                Helper.Sleep(1);
            }
            System.out.println(Colors.RESET + Colors.CLEAR);
            Main.currentPlace.turnEnd(p, enemies);
            Actions = p.getActionAmount();
        }
        if (p.getBattleHp() > 0) {
            //TODO drops
            System.out.println("You won!");
            p.incStageNum(1);

        }
        updateItems(p, true);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Helper.Sleep(1);

    }

    private void printHealth(List<Enemy> enemies, Player p) {
        System.out.println(Colors.PURPLE + "Player hp: " + p.getBattleHp());
        String Names  = "";
        String HpAmounts = "";
        String hpBars = "";
        for (Enemy enemy : enemies) {
            String nameAdd = enemy.getName();
            String hpAdd =   enemy.getBattleHp() + "hp";
            if(nameAdd.length() > hpAdd.length()){
                //find the difference
                int diff = nameAdd.length() - hpAdd.length();
                int offset = diff / 2;
                for(int i = 0; i < offset; i++){
                    hpAdd = " " + hpAdd + " ";
                }
                if(diff %2 != 0){
                    hpAdd += " ";
                }
            }
            else if (nameAdd.length() < hpAdd.length()){
                int diff = hpAdd.length() - nameAdd.length();
                int offset = diff / 2;
                for(int i = 0; i < offset; i++){
                    nameAdd = " " + nameAdd + " ";
                }
                if(diff %2 != 0){
                    nameAdd += " ";
                }
            }
            int barLength = Math.max(nameAdd.length(), hpAdd.length());
            String bar = "[=]";
            if(!(barLength < 3)){
                 bar = "[";
                for(int i = 0; i < barLength - 2; i++){
                    bar += "=";
                }
                bar += "]";
            }
            hpBars += bar + "  ";
            Names +=  nameAdd + "  ";
            HpAmounts += hpAdd + "  ";
        }
        System.out.println(Names);
       // System.out.println(hpBars);
        System.out.println(HpAmounts +  Colors.RESET);
    }

    public static void inv(List<Enemy> enemies) {

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
            System.out.println(
                    Colors.RED_BRIGHT + "Dodge Rate: " + enemies.get(choiceInfo - 1).getDodgeRate() + Colors.RESET);
            Helper.contiuePrompt();
        }
        inv(enemies);

    }  //TODO get location + opponent info

    @Override
    public String getName() {
        return "Battle";
    }


}
