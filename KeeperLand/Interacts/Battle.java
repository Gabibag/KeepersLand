package KeeperLand.Interacts;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Interactable;
import KeeperLand.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static KeeperLand.Interacts.BossFight.healPlayer;
import static KeeperLand.Main.player;

public class Battle extends Interactable {

    static void updateItems(Player p, int battleEnd) {

        if (battleEnd == 1) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getBattleHp() + i.getHpIncr());
                p.setBattleDamage(p.getDamage() + i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() + i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() + i.getHealVariance());
            }
        }
        else if (battleEnd == 2) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getHp() - i.getHpIncr());
                p.setBattleDamage(p.getDamage() - i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() - i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() - i.getHealVariance());
            }
        }
        else {
            updateItems(p, 2);
            updateItems(p, 1);
        }
    }

    public static List<Enemy> getEnemies(Player p) {

        List<Enemy> returned = new ArrayList<>();
        for (Enemy e : Main.allEnemies) {
            if (e.canSpawn(p)) {
                if (p.getStageNum() % 5 == 0) {
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
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy choice = enemies.get(i);
            if (choice.getBattleHp() <= 0) {
                choice.onDeath(player, enemies, choice);
                enemies.remove(choice);
            }
        }
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
                    Colors.RED_BRIGHT + "Dodge Rate: " + enemies.get(choiceInfo - 1).getDodgeRate()
                    + Colors.RESET);
            Helper.continuePrompt();
        }
        inv(enemies);

    }  //TODO get location + opponent info

    @Override
    public void onChoose(Player p) {
        int tempMaxHp = p.getHp();
        for (Item i : p.getInventory()) {
            tempMaxHp += i.getHpIncr();
        }
        p.setBattleHp(p.getHp());
        updateItems(p, 1);
        Random r = new Random();
        int Actions = p.getActionAmount();
        List<Enemy> spawns = getEnemies(p);
        while (spawns.size()<3) {
            spawns = getEnemies(p);


            //check if spawns contains duplicates, if it does, remove it
            for (int i = spawns.size() - 1; i >= 0; i--) {
                for (int j = spawns.size() - 1; j >= i + 1; j--) {
                    if (spawns.get(i).getName().equalsIgnoreCase(spawns.get(j).getName())) {
                        spawns.remove(j);
                    }
                }
            }
        }
        List<Enemy> enemies = Helper.getRandomElements(spawns, ((p.getStageNum() % 5 == 0 ? 1 : 3)));//only spawns 1 boss


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
        if ((p.getStageNum() % 5 == 0)) {
            try {
                ((Boss) (enemies.get(0))).bossOnSpawn(enemies);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Main.currentPlace.BattleStart(p, enemies);
        while (enemies.size() > 0) {
            removeDead(enemies);
            //tell user their stage number and enviorment
            while (Actions > 0) {
                System.out.print("You are in the " + Main.currentPlace.getName() + Colors.RESET);
                System.out.println();
                //updateItems(p, 3);
                printHealth(enemies, p);
                System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
                System.out.println(Colors.PURPLE +
                                   "[1] Attack");
                System.out.println("[2] Heal");
                System.out.println("[3] Info" + Colors.RESET);
                int choice = Helper.getInputDefault(Colors.RESET + "Current Health: " + p.getBattleHp(), 3, 1);
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
                                try {
                                    enemies.get(choice - 1).onDeath(p, enemies, enemies.get(choice-1));
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                enemies.remove(choice - 1);
                            }

                            Helper.continuePrompt();
                        }
                        else {
                            System.out.println(enemies.get(choice - 1).getName() + enemies.get(choice - 1).getDodgeText());
                            Helper.Sleep(1);
                        }
                    }
                    //#endregion
                    //#region case2
                    case 2 -> {
                        try {
                            healPlayer(p, tempMaxHp, r);
                        }
                        catch (Exception e) {
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
                Main.currentPlace.playerAction(p, enemies);
                if (enemies.size() > 0) {
                    Actions--;
                }
                else {
                    p.setActionAmount(2);
                    break;
                }
                System.out.println(Colors.CLEAR);
            }

            attackEnemies(p, enemies);
            if (enemies.size() > 0) {
                System.out.println(Colors.CLEAR + Colors.RESET);
                Main.currentPlace.turnEnd(p, enemies);
            }
            Actions = p.getActionAmount();
        }
        if (p.getBattleHp() > 0) {
            //TODO drops
            System.out.println("You won!");
            p.incStageNum(1);
            p.Save(p.getName() + ".plr");

        }
        updateItems(p, 2);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        Helper.Sleep(1);

    }

    public static void attackEnemies(Player p, List<Enemy> enemies) {
        System.out.println(Colors.CLEAR);
        int totalDamage = 0;
        int currentHp = p.getBattleHp();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            int damage;
            if (enemy instanceof Boss) {
                damage = ((Boss) enemy).BossAttack(p, enemies);
            }
            else {
                damage = enemy.Attack(p, enemies);
            }
            damage = Main.currentPlace.modifyEnemyDamage(damage);
            if (damage > 0) {
                p.setBattleHp(p.getBattleHp() - damage);
//                    System.out.println(enemy.getName() + " deals " + Colors.RED +  damage + Colors.RESET +  " damage");
            }//       Helper.Sleep(enemies.size()>=4 ? 0.5 : 1);
                totalDamage += damage;

        }

        System.out.println("\nTotal damage taken: " + Colors.RED + (currentHp- p.getBattleHp()) + Colors.RESET + " [" + Colors.RED + "❤ " + (p.getBattleHp() < 0 ? 0 : p.getBattleHp()) + Colors.RESET + "]");
        Helper.continuePrompt();
        if (p.getBattleHp() <= 0) {
            System.out.println("You lost!");
            IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                    enemies::remove); //the magic of intellij
            p.Save(p.getName() + ".plr");
            Helper.Sleep(1);
        }
    }

    private void printHealth(List<Enemy> enemies, Player p) {
        StringBuilder Names = new StringBuilder();
        StringBuilder HpAmounts = new StringBuilder();
        StringBuilder hpBars = new StringBuilder();
        for (Enemy enemy : enemies) {
            StringBuilder nameAdd = new StringBuilder(enemy.getName());
            StringBuilder hpAdd = new StringBuilder(enemy.getBattleHp() + "hp");
            if (nameAdd.length() > hpAdd.length()) {
                //find the difference
                int diff = nameAdd.length() - hpAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) {
                    hpAdd = new StringBuilder(" " + hpAdd + " ");
                }
                if (diff % 2 != 0) {
                    hpAdd.append(" ");
                }
            }
            else if (nameAdd.length() < hpAdd.length()) {
                int diff = hpAdd.length() - nameAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) {
                    nameAdd = new StringBuilder(" " + nameAdd + " ");
                }
                if (diff % 2 != 0) {
                    nameAdd.append(" ");
                }
            }
            int barLength = Math.max(nameAdd.length(), hpAdd.length());
            StringBuilder bar = new StringBuilder("[=]");
            if (!(barLength < 3)) {
                bar = new StringBuilder("[");
                for (int i = 0; i < barLength - 2; i++) {
                    double percentHp = enemy.getBattleHp() / (double) enemy.getBaseHp();
                    double barPercent = i / (double) (barLength - 2);
                    if (barPercent < percentHp) {
                        bar.append("=");
                    }
                    else {
                        bar.append(" ");
                    }
                }
                bar.append("]");
            }
            hpBars.append(bar).append("  ");
            Names.append(nameAdd).append("  ");
            HpAmounts.append(hpAdd).append("  ");
        }
        System.out.println(Colors.RED + Names );
        System.out.println(hpBars);
        System.out.println(HpAmounts + Colors.RESET);
    }

    @Override
    public String getName() {
        return "Battle";
    }


}
