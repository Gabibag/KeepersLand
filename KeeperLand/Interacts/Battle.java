package KeeperLand.Interacts;

import KeeperLand.Abstracts.*;
import KeeperLand.*;
import KeeperLand.Enemies.Sprites.HelperSprite;
import KeeperLand.Mutations.None;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static KeeperLand.Helper.getFullHealAmount;
import static KeeperLand.Helper.getMaxHealth;
import static KeeperLand.Main.player;

public class Battle extends Interactable {

    @Override
    public void onChoose(Player p) {
        p.setBattleHp(p.getHp());
        p.setBattleDamage(p.getDamage());
        updateItems(p, 1);
        Random r = Main.r;
        int Actions = p.getActionAmount();
        List<Enemy> spawns = getEnemies(p);
        while (spawns.size() < 3) {
            spawns = getEnemies(p);
            //check if spawns contains duplicates, if it does, remove it
            List<Enemy> finalSpawns = spawns;
            for (int i = 0; i < spawns.size(); i++) {
                Enemy e = spawns.get(i);
                while (spawns.stream().filter(x -> x.getName().equals(e.getName())).count() > 1) {
                    spawns.remove(e);
                }
            }
        }
        List<Enemy> enemies = Helper.getRandomElements(spawns, ((p.getStageNum() % 5 == 0 ? 1 : 3)));//only spawns 1 boss
        //replace duplicates with entities in all enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (enemies.stream().filter(x -> x.getName().equals(e.getName())).count() > 1) {
                enemies.set(i, Main.allEnemies.stream().filter(x -> x.getName().equals(e.getName())).findFirst().orElseThrow());
            }
        }
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
        whileAlive(enemies);
        battleEnd(enemies);

    }

    private static void instaAttackMode(Player p, List<Enemy> enemies) {
        //check if enemies can deal damage
        if (p.getLevel() % 5 == 0) return;
        for (Enemy enemy : enemies) {
            if (enemy.getDamage() > 0 || !(enemy.getMutate() instanceof None || enemy.getMutate() == null) || enemy instanceof Boss || enemy instanceof HelperSprite) {

                return;
            }
        }
        if (!enemies.isEmpty() && p.getBattleHp() > 0) {
            System.out.println("Insta attack mode!");
            Helper.Sleep(1);
            //make player attack them repeatedly until all enemies are dead if the enemies cant deal damage
            int counter = 0;
            double sleepTime = 0.7F;
            while (!enemies.isEmpty()) {
                for (int i = 0; i < enemies.size(); i++) {
                    playerAttack(p, enemies, i + 1);
                    checkIfDead(p, enemies);
                    removeDead(enemies);
                    printHealth(enemies);
                    Helper.Sleep(sleepTime);
                    System.out.println(Colors.CLEAR);
                }
                counter++;
                if (counter % 2 == 0) {
                    sleepTime -= 0.1F;
                }
            }
        }
    }

    static void updateItems(Player p, int battleEnd) {

        if (battleEnd == 1) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getBattleHp() + i.getHpIncr());
                p.setBattleDamage(p.getBattleDamage() + i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() + i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() + i.getHealVariance());
            }
        } else if (battleEnd == 2) {
            for (Item i : p.getInventory()) {
                p.setBattleHp(p.getHp() - i.getHpIncr());
                p.setBattleDamage(p.getBattleDamage() - i.getDmgIncr());
                p.setHealAmount(p.getHealAmount() - i.getHealIncrease());
                p.setHealVariance(p.getHealVariance() - i.getHealVariance());
            }
        } else {
            updateItems(p, 2);
            updateItems(p, 1);
        }
    }

    public static List<Enemy> getEnemies(Player p) {

        List<Enemy> returned = new ArrayList<>();
        for (Enemy e : Main.allEnemies) {
            if (!e.canSpawn(p)) {
                continue;
            }
            if (p.getStageNum() % 5 == 0) {
                if (e instanceof Boss) {
                    returned.add(e);
                }
            } else if (!(e instanceof Boss)) {
                returned.add((e));
            }


        }

        return returned;
    }

    public static void removeDead(List<Enemy> enemies) {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy choice = enemies.get(i);
            if (choice.getBattleHp() <= 0) {
                choice.onDeath(player, enemies, choice);
                enemies.remove(choice);
            }
        }
    }

    //create a static method that removes all enemies in the list given that has a battleHp that is less than 0

    public static void inv(List<Enemy> enemies) {

        System.out.println(Colors.PURPLE + "[0] Go Back");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("[" + (i + 1) + "] Inspect " + enemies.get((i)).getName());
        }

        System.out.println("[" + (enemies.size() + 1) + "] Environment Info");
        System.out.println("[" + (enemies.size() + 2) + "] Stats");
        System.out.println("[" + (enemies.size() + 3) + "] Inventory" + Colors.RESET);
        int choiceInfo = Helper.getInput("", 0, enemies.size() + 3);

        if (choiceInfo == 0) return;
        else if (choiceInfo == (enemies.size() + 1)) {
            System.out.println(
                    "Current Location: " + Main.currentPlace.getName() + "\n" + Main.currentPlace.getDescription());
        } else if (choiceInfo == enemies.size() + 2) {
            System.out.println(player);
        } else if (choiceInfo == enemies.size() + 3) {

            new Inventory().inventory(player);
        } else if (choiceInfo > 0 && choiceInfo < enemies.size() + 1) {
            Enemy e = enemies.get(choiceInfo - 1);
            System.out.println(e.getName() + ":");
            System.out.println(
                    Colors.RED_BRIGHT + "Max Health: " + enemies.get((choiceInfo - 1)).getBaseHp() + Colors.RESET);
            System.out.println(Colors.RED_BOLD + "Damage: " + e.getDamage() + Colors.RESET);
            System.out.println(
                    Colors.RED_BRIGHT + "Current Health: " + e.getBattleHp() + Colors.RESET);
            System.out.println(
                    Colors.RED_BRIGHT + "Dodge Rate: " + e.getDodgeRate()
                            + Colors.RESET);
        }
        Helper.continuePrompt();
        inv(enemies);

    }  //TODO get location + opponent info

    public static void enemyAttacks(Player p, List<Enemy> enemies) {
        System.out.println("Enemies Turn!");
        int currentHp = p.getBattleHp();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            int damage;
            if (enemy instanceof Boss) {
                damage = ((Boss) enemy).BossAttack(p, enemies);
            } else {
                damage = enemy.Attack(p, enemies);
            }
            damage = Main.currentPlace.modifyEnemyDamage(damage);
            if (damage > 0) {
                p.setBattleHp(p.getBattleHp() - damage);
//                    System.out.println(enemy.getName() + " deals " + Colors.RED +  damage + Colors.RESET +  " damage");
            }//       Helper.Sleep(enemies.size()>=4 ? 0.5 : 1);

        }
        for (StatusEffects s : player.getStatusEffects())
            s.tickEffect(p, null, enemies, "enemyAttack", currentHp - p.getBattleHp()); //"currentHp-p.getBattleHp()" is damage dealt to player


        System.out.println("Total damage taken: " + Colors.RED + (currentHp - p.getBattleHp()) + Colors.RESET + " [" + Colors.RED + "❤ " + (Math.max(p.getBattleHp(), 0)) + Colors.RESET + "]");
        Helper.continuePrompt();
        System.out.println(Colors.CLEAR);
        if (p.getBattleHp() <= 0) {
            killPlayer(p, enemies);
        }
    }

    private static void enemyAttackChoice(List<Enemy> enemies) {
        printHealth(enemies);
        System.out.print(Colors.CYAN + "\nUsing action..." + Colors.RESET);
        int newLineCount = 3 - enemies.size();
        for (int i = 0; i < newLineCount; i++) {
            System.out.println();
        }
        for (int i = 0; i < enemies.size(); i++) {
            System.out.print("\n" + Colors.RED + "[" + (i + 1) + "] " + enemies.get(i).getName());
            System.out.print(Colors.RESET);
        }
    }

    private static void checkIfDead(Player p, List<Enemy> enemies) {
        //cycle through enemies and check if they are dead

        ArrayList<Enemy> mutated = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.getMutate() == null) {
                continue;
            }
            mutated.add(e);
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).getBattleHp() > 0) continue;

            if (enemies.get(i).getMutate() == null) {
                return;
            }
            for (Enemy e : mutated) {
                if (e == enemies.get(i)) continue;
                e.getMutate().onKill(enemies, e, enemies.get(i));
            }
        }
        if (p.getBattleHp() <= 0) {
            killPlayer(p, enemies);
        }

    }

    private static void killPlayer(Player p, List<Enemy> enemies) {
        if (p.isDead()) {
            return;
        }
        System.out.println("You lost!");
        IntStream.iterate(enemies.size() - 1, i -> i >= 0, i -> i - 1).forEach(
                enemies::remove); //the magic of intellij
        p.Save(p.getName() + ".plr");
        if ((p.getStageNum() % 5 == 0 || (p.getStageNum() - 1 % 5 == 0)) || (p.getStageNum() == 0 || p.getStageNum() == 1)) {
            p.setStageNum(p.getStageNum() - 1);
        }

        //remove all status effects
        for (int i = p.getStatusEffects().size() - 2; i >= 0; i--) {
            p.removeStatusEffects(p.getStatusEffects().get(i));
        }
        p.setDead(true);
        Helper.Sleep(1);
    }

    private static void playerAttack(Player p, List<Enemy> enemies, int choice) {
        int pDamage = Main.currentPlace.modifyPlayerDamage(p.getBattleDamage());
        enemies.get(choice - 1).setBattleHp(enemies.get(choice - 1).getBattleHp() - pDamage);
        if (pDamage < p.getBattleDamage()) {
            System.out.println(Colors.RED + "Dealt " + pDamage + " damage to " +
                    enemies.get(choice - 1).getName() + Colors.RESET + " (⬇ " + p.getBattleDamage() + " -> " + pDamage + " )");
        } else if (pDamage > p.getBattleDamage()) {
            System.out.println(Colors.RED + "Dealt " + pDamage + " damage to " +
                    enemies.get(choice - 1).getName() + Colors.RESET + " (⬆ " + p.getBattleDamage() + " -> " + pDamage + " )");
        } else {
            System.out.println(Colors.RED + "Dealt " + pDamage + " damage to " +
                    enemies.get(choice - 1).getName() + Colors.RESET);
        }
        if (enemies.get(choice - 1).getMutate() != null) {
            enemies.get(choice - 1).getMutate().onHurt(enemies, pDamage, enemies.get(choice - 1)); //mutation damage.
        }
        if (!player.getStatusEffects().isEmpty()) {
            List<StatusEffects> statusEffects = player.getStatusEffects();
            for (int i = statusEffects.size() - 1; i >= 0; i--) { //not enhanced because effects vanish
                StatusEffects s = statusEffects.get(i);
                s.tickEffect(p, enemies.get(choice - 1), enemies, "playerAttack", pDamage);
            }

        }

    }

    private static void printHealth(@NotNull List<Enemy> enemies) {
        StringBuilder Names = new StringBuilder();
        StringBuilder HpAmounts = new StringBuilder();
        StringBuilder hpBars = new StringBuilder();
        for (Enemy enemy : enemies) {
            Mutations mutate;

            if (enemy.getMutate() != null) mutate = enemy.getMutate();
            else mutate = new None();
            StringBuilder nameAdd = new StringBuilder(" lvl " + enemy.getLevel() + " " + mutate.getColor() + mutate.getMutationType() + Colors.RED + " " + enemy.getName());
            StringBuilder hpAdd = new StringBuilder(enemy.getBattleHp() + "hp");
            int actualNameLength = nameAdd.length() - mutate.getColor().length() - Colors.RED.length();
            if (actualNameLength > hpAdd.length()) {
                //find the difference
                int diff = actualNameLength - hpAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) hpAdd = new StringBuilder(" " + hpAdd + " ");
                if (diff % 2 != 0) hpAdd.append(" ");
            } else if (actualNameLength < hpAdd.length()) {
                int diff = hpAdd.length() - nameAdd.length();
                int offset = diff / 2;
                for (int i = 0; i < offset; i++) {
                    nameAdd = new StringBuilder(" " + nameAdd + " ");
                }
                if (diff % 2 != 0) {
                    nameAdd.append(" ");
                }
            }

            int barLength = Math.max(actualNameLength, hpAdd.length());
            StringBuilder bar = new StringBuilder("[=]");
            if (!(barLength < 3)) {
                bar.append("=");
                bar = new StringBuilder("[");
                for (int i = 0; i < barLength - 2; i++) {
                    double percentHp = enemy.getBattleHp() / (double) enemy.getBaseHp();
                    double barPercent = i / (double) (barLength - 2);
                    if (barPercent < percentHp) bar.append("=");
                    else bar.append(" ");
                }
                bar.append("]");
            }
            hpBars.append(bar).append("  ");
            Names.append(nameAdd).append("  ");
            HpAmounts.append(hpAdd).append("  ");
        }
        System.out.println(Colors.RED + Names);
        System.out.println(hpBars);
        System.out.println(HpAmounts + Colors.RESET);
    }


    static void battleEnd(List<Enemy> enemies) {
        Player p = Main.player;
        if (p.getBattleHp() > 0 && enemies.size() == 0) {
            //TODO drops
            System.out.println("You won!");
            p.incStageNum(1);
            p.Save(p.getName() + ".plr");

        }
        //occurs after battle ends
        ArrayList<Item> inventoryTrunk = new ArrayList<>(Main.player.getInventory());

        p.setDead(false);
        updateItems(p, 2);
        Main.getNewPlace();
        p.setBattleHp(p.getHp());
        p.setActionAmount(2);
        Helper.Sleep(1);
    }

    static void whileAlive(List<Enemy> enemies) {
        Player p = Main.player;
        Random r = Main.r;
        int Actions = p.getActionAmount();
        while (enemies.size() > 0) {
            checkIfDead(p, enemies);
            removeDead(enemies);
            //tell user their stage number and environment
            while (Actions > 0) {
                String col = Colors.RESET;
                if (player.getStatusEffects().size() != 0) {
                    col = player.getStatusEffects().get(player.getStatusEffects().size() - 1).getEffectColor();
                }
                System.out.print("You are in the " + Main.currentPlace.getName() + Colors.RESET);
                System.out.println();
                //updateItems(p, 3);
                printHealth(enemies);
                printActions(Actions);
                int choice = Helper.getInputDefault(Colors.RESET + "Current Health: " + col + p.getBattleHp() + Colors.RESET, 3, 1);
                switch (choice) {
                    case 1 -> {//attack
                        System.out.println(Colors.CLEAR);

                        if (enemies.size() == 1) {
                            choice = 1;
                        } else {
                            enemyAttackChoice(enemies);
                            choice = Helper.getInput("\nPlayer " + p.getBattleHp() + "hp: ", enemies.size());
                            if (enemies.get(choice - 1).getName().contains("Keeper")) {
                                System.out.println("You are not allowed to attack the Keeper until you kill the other enemies!");
                                Helper.Sleep(1);
                                continue;
                            }
                            System.out.println(Colors.CLEAR);
                        }
                        if (r.nextInt(25 / enemies.get(choice - 1).getDodgeRate()) != 0) {
                            playerAttack(p, enemies, choice);
                            checkIfDead(p, enemies);
                            removeDead(enemies);
                        } else {
                            System.out.println(enemies.get(choice - 1).getName() + enemies.get(choice - 1).getDodgeText());
                            Helper.Sleep(1.5);
                        }
                    }
                    case 2 -> healPlayer(p, r, enemies);
                    case 3 -> inv(enemies);

                }
                Main.currentPlace.playerAction(p, enemies);
                if (enemies.size() > 0) Actions--;
                else {
                    p.setActionAmount(2);
                    break;
                }

            }
            printHealth(enemies);//print out a ui to make it look like its just changing stuff. Idk looks cool
            String col = Colors.RESET;
            if (player.getStatusEffects().size() != 0) {
                col = player.getStatusEffects().get(player.getStatusEffects().size() - 1).getEffectColor();
            }
            System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
            System.out.println(Colors.BLACK_BRIGHT + "[0] Attack");
            System.out.println("[0] Heal");
            System.out.println("[0] Info " + Colors.RESET);
            System.out.println("Current Health: " + col + p.getBattleHp() + Colors.RESET);
            Helper.continuePrompt();

            System.out.println(Colors.CLEAR);
            instaAttackMode(p, enemies);
            if (enemies.size() > 0) {
                p.setActionAmount(2);
                enemyAttacks(p, enemies);
                System.out.println(Colors.RESET);
                Main.currentPlace.turnEnd(p, enemies);
                List<StatusEffects> statusEffects = player.getStatusEffects();
                for (StatusEffects s : statusEffects) {
                    s.tickEffect(p, null, enemies, "turnEnd", 0);

                }
                checkIfDead(p, enemies);
                removeDead(enemies);
            }
            Actions = p.getActionAmount();

        }
    }

    public static void printActions(int Actions) {
        System.out.println(Colors.CYAN + "\nActions left:" + Actions + Colors.RESET);
        System.out.println(Colors.PURPLE +
                "[1] Attack");
        System.out.println("[2] Heal");
        System.out.println("[3] Info" + Colors.RESET);
    }

    @Override
    public String getName() {
        return "Battle";
    }

    static void healPlayer(Player p, Random r, List<Enemy> enemies) {
        ArrayList<Enemy> mutated = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.getMutate() == null) {
                continue;
            }
            mutated.add(e);
        }

        int max = getMaxHealth(p);
        if (p.getBattleHp() >= max) {
            System.out.println("You are already at full health!");
            Helper.continuePrompt();
            return;
        }
        int healAmount = getFullHealAmount(p, r, max);
        if (healAmount < 0) {
            healAmount = 0;
        } else if (healAmount + p.getBattleHp() >= max) {
            p.setBattleHp(max);
            System.out.println("You healed to" + Colors.RED + " full" + Colors.RESET + " health!");
            for (Enemy e : mutated) {
                e.getMutate().onHeal(enemies, healAmount, e);
            }
            for (StatusEffects s : p.getStatusEffects()) {
                s.tickEffect(p, null, enemies, "playerHeal", healAmount);
            }
            return;
        }
        if (healAmount == 0) {
            healAmount = 1;
        }
        p.setBattleHp(p.getBattleHp() + healAmount);
        System.out.println(Colors.RED + ((healAmount + p.getBattleHp() ==
                max) ? "You healed to full health" :
                "You healed " + healAmount + " health") + Colors.RESET);

        for (Enemy e : mutated) {
            e.getMutate().onHeal(enemies, healAmount, e);
        }
        for (StatusEffects s : p.getStatusEffects()) {
            s.tickEffect(p, null, enemies, "playerHeal", healAmount);
        }

    }


}
