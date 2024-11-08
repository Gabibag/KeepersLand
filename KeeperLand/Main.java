package KeeperLand;

import KeeperLand.Abstracts.*;
import KeeperLand.Enemies.Common.Warrior;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Enviroments.LavaZone;
import KeeperLand.Enviroments.NullZone;
import KeeperLand.Enviroments.StarterLand;
import KeeperLand.Interacts.Inventory;
import KeeperLand.Interacts.LevelUp;
import KeeperLand.Interacts.Shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class Main {
    public static final List<Environment> allPlaces = new ArrayList<>();
    public static final List<Interactable> allInteracts = new ArrayList<>(); //adds everything that can be talked to(interacted) to an arraylist
    public static final List<Enemy> allEnemies = new ArrayList<>();
    public static final List<Item> allItem = new ArrayList<>();
    public static final List<Mutations> allMutations = new ArrayList<>();
    public static final List<StatusEffects> allStatusEffects = new ArrayList<>();
    public static final List<Sprite> allSprites = new ArrayList<>();
    public static final List<Boss> allBosses = new ArrayList<>();
    public static final List<Enemy> commonEnemies = new ArrayList<>();
    public static Environment currentPlace;
    public static Random r;
    public static Player player;
    public static Scanner s;
    static FileWriter fw;

    public static void main(String[] args) throws IOException {
        s = new Scanner(System.in);
        r = new Random();


        initTypes();
        Enemy.loaded = true;
//        System.out.println(Colors.CLEAR + "Press ctrl + c to quit ;)");

        //defaults for player
        List<String> saveList = allPlayerFiles();
        int saves;
        if (saveList.isEmpty()) {
            saves = -1;
        } else {
            System.out.println("[-1] New Save");
            for (int i = 0; i < saveList.size(); i++) {
                System.out.println("[" + i + "] " + saveList.get(i).substring(0, saveList.get(i).length() - 4));
            }
            saves = Helper.getInput("", -1, saveList.size() - 1);
        }
        if (saves > -1) {
            try {
                player = Player.loadFromFile((saveList.get(saves)));
                if (player == null) {
                    System.out.println("Corrupted Save, creating new player instead");
                    saves = -1;
                }
            } catch (Exception e) {
                saves = -1;
            }
            getNewPlace();

        }
        if (saves == -1) {
            List<String> takenNames = allPlayerFiles();
            takenNames.replaceAll(s1 -> s1.substring(0, s1.length() - 4));
            String name = Helper.Prompt(Colors.CYAN + "Welcome \nEnter your player's name: " + Colors.RESET);
            List<String> invalidChars = Arrays.asList(":", "/", "\\", "?", "<", ">", "*", "|", "\"", " ");
            while (takenNames.contains(name) || invalidChars.stream().anyMatch(name::contains)) {
                name = Helper.Prompt(
                        Colors.RED + "That name is already taken, please enter a new name: " + Colors.RESET);
            }
            player = new Player(name, 30, 5,
                    new ArrayList<>());
            player.addMoney(10);
            player.setHealAmount(10);
            player.setHealVariance(1);
            player.addInventory(ItemData.starterWeapon);

            Main.currentPlace = new StarterLand();
        }
        //region testing
        if (player.getName().contains("among us") || player.getName().contains("atest")) {
            player.incStageNum(10);
            player.setHealAmount(100);
            player.addMoney(99999);
            player.setDamage(5);
            player.setHp(2000);
            System.out.println("sus");
            player.getInventory().addAll(
                    Arrays.asList(ItemData.ShatteredShard, ItemData.HellShard, ItemData.DeathShard, ItemData.OmegaShard,
                            ItemData.SpriteShard, ItemData.HealingShard, ItemData.GlitchedShard));
            Main.currentPlace = new GatesToHell();
        }
        if (player.getName().contains("playtest") || player.getName().contains("ptest")) {
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < 19; i++) {
                spawns = Helper.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                getNewPlace();
            }
            player.incStageNum(19);
            Main.currentPlace = new LavaZone();
        }
        if (player.getName().contains("runThrough") || player.getName().contains("rtest")) {
            System.out.println(Integer.MAX_VALUE);
            int lvl = Helper.getInput("What level would you like to be at?", Integer.MAX_VALUE);
            int divs = Math.max(lvl / 10, 1);

            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Helper.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, (lvl % 5 == 4 ? 1 : 3));
                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                Shop.statBuy(player);
                player.incStageNum(1);
                getNewPlace();

                if (i % divs == 0) {
                    System.out.println("Player is at level " + player.getStageNum() + "/" + lvl + " (" + (int) (100.0 * i / lvl) + "%)");
//                    Helper.checkForComplexCreation(Main.allItem);
                }
            }
            currentPlace = new NullZone();
            if (lvl > 99_999) {
                for (int i = 0; i < allPlaces.size(); i++) {
                    System.out.println("[" + i + "] " + allPlaces.get(i).getName());
                }
                int location = Helper.getInput("What location would you like to be at?", 0, allPlaces.size());

                try {
                    Main.currentPlace = allPlaces.get(location).getClass().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                currentPlace = allPlaces.get(location);
            }
            //add all items that have the word "shard" in them from the list allItems
            allItem.stream().filter(i -> i.getName().toLowerCase().contains("shard") && !i.getName().toLowerCase().contains("keeper")).forEach(i -> player.addInventory(i));

            System.out.println("amogus");
        }
        if (player.getName().equalsIgnoreCase("bossTest") || player.getName().equalsIgnoreCase("btest")) {
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < 40; i++) {
                spawns = Helper.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }

                player.incStageNum(1);
                getNewPlace();
            }
            for (int i = 0; i < allPlaces.size(); i++) {
                System.out.println("[" + i + "] " + allPlaces.get(i).getName());
            }

            currentPlace = new NullZone();
            //add all items that have the word "shard" in them from the list allItems
            for (Item i : allItem) {
                if (i.getName().toLowerCase().contains("shard")) {
                    player.addInventory(i);
                }
            }
//            player.setDamage(player.getDamage()*500);
            LevelUp a = new LevelUp();
            a.onChoose(player);
            System.out.println("amogngnus");
        }
        if (player.getName().equalsIgnoreCase("StatsTest") || player.getName().equalsIgnoreCase("stest")) {
            int lvl = Helper.getInput("What level would you like to be at?", 99999999);
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Helper.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, (lvl % 5 == 0 ? 1 : 3));

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                Shop.statBuy(player);

                getNewPlace();
                player.incStageNum(1);
            }

            //level up player
            LevelUp a = new LevelUp();
            a.onChoose(player);

            System.out.println(player);
            Inventory i = new Inventory();
            i.inventory(player);
            System.exit(0);
        }
        if (player.getName().equalsIgnoreCase("sampleTest")) {
//            Basically just run through the game up to the level given, then reset. Do this 10 times, average out the stats, then display
            int lvl = Helper.getInput("What level would you like to be at?", 1000);
            int runs = Helper.getInput("How many runs would you like to do?", Integer.MAX_VALUE);
            String mainDir = "./tests";
            if (!new File(mainDir).exists()) {
                new File(mainDir).mkdir();
            }
            String dirName = "/sampleTestData_";
            int fileSuffix = 1;

            while (new File(mainDir + dirName + fileSuffix + "/data.txt").exists()) {
                fileSuffix++;
            }
            String dataFile = mainDir + dirName + fileSuffix + "/data.txt";
            String avgFile = mainDir + dirName + fileSuffix + "/avg.txt";
            new File(mainDir + dirName + fileSuffix).mkdir();
            File f = new File(dataFile);
            File avgF = new File(avgFile);
            try {
                f.createNewFile();
                avgF.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file, aborting.");
                System.exit(1);
            }
            fw = new FileWriter(f, true);


            for (int i = 0; i < runs; i++) {
                ArrayList<Item> tempInv = new ArrayList<>();
                tempInv.add(ItemData.starterWeapon);
                Player p = new Player("sampleTest", 40, 5, tempInv);
                List<Enemy> spawns;
                List<Enemy> tempenemies;
                for (int j = 0; j < lvl; j++) {
                    spawns = Helper.getEnemies(p);
                    tempenemies = Helper.getRandomElements(spawns, (lvl % 5 == 0 ? 1 : 3));
                    for (Enemy e : tempenemies) {
                        e.randDrops(p, e);
                    }
                    Shop.statBuy(p);
                    if (p.getXp() >= p.getXpToLevel()) {
                        LevelUp a = new LevelUp();
                        a.levelPlayer(p);
                    }
                    getNewPlace();
                    p.incStageNum(1);
                }
                if (i % (runs / 10) == 0) {
                    System.out.println("Run " + i + " completed");
                }

                try {
                    writeToFile(i, p, f);
//                    fw.close();
                } catch (IOException e) {
                    System.out.println("Error writing to file, aborting.");
                    System.out.println();
                    System.exit(1);
                }

            }

            System.out.println("All runs completed, calculating average stats");
            Player p = null;
            try {
                Scanner sc = new Scanner(f);
                int[] stats = new int[9];
                int count = 0;
                while (sc.hasNext()) {
                    try {
                        String line = sc.nextLine();
                        if (line.contains("Run")) {
                            count++;
                        } else if (line.contains("Health")) {
                            stats[0] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Damage")) {
                            stats[1] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Heal Amount")) {
                            stats[2] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Heal Variance")) {
                            stats[3] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Money")) {
                            stats[4] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Stage")) {
                            stats[5] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("XP")) {
                            stats[6] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("Level")) {
                            stats[7] += Integer.parseInt(line.split(": ")[1]);
                        } else if (line.contains("XP to Level")) {
                            stats[8] += Integer.parseInt(line.split(": ")[1]);
                        }
                    } catch (Exception ignored) {

                    }
                }
                sc.close();
                for (int i = 0; i < stats.length; i++) {
                    stats[i] /= count;
                }
                ArrayList<Item> tempInv = new ArrayList<>();
                tempInv.add(ItemData.starterWeapon);
                p = new Player("sampleTest", stats[0], stats[1], tempInv);
                p.setHealAmount(stats[2]);
                p.setHealVariance(stats[3]);
                p.setMoney(stats[4]);
                p.setStageNum(stats[5]);
                p.setXp(stats[6]);
                p.setLevel(stats[7]);
                p.setXpToLevel(stats[8]);
                String avgStats = averageFile(stats);
                try {
                    FileWriter fw = new FileWriter(avgF, true);
                    fw.write("\n" + avgStats);
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Error writing to file, aborting.");
                    System.exit(1);
                }

            } catch (IOException e) {
                System.out.println("Error reading from file, aborting.");
                System.exit(1);
            }
//            Get approximate scale values of entities
            String dmgScale = "Damage scale attribute: " + Helper.getScaleFactor(1, lvl);
            String hpScale = "Health scale attribute: " + Helper.getScaleFactor(0, lvl);
            String coinScale = "Coin scale attribute: " + Helper.getScaleFactor(2, lvl);
            Enemy temp = new Warrior();
            temp.setBaseStats();
            temp.setLevel(lvl);
            temp.scaleStats();
            String approxEnemy = "Approximate stats for a level " + lvl + " enemy: " + temp;
            String relativeValues = "For an enemy scaled to " + lvl + ", you would...\n" + values(temp, p);
            Enemy temp2 = new Warrior();
            temp2.setBaseStats();
            ArrayList<Item> tempInv = new ArrayList<>();
            tempInv.add(ItemData.starterWeapon);

            String baseRelativeValues = "For a base enemy, you would... \n" + values(temp2, new Player("sampleTest", 40, 5, tempInv));


            try {
                FileWriter fw = new FileWriter(avgF, true);
                fw.write("\n" + dmgScale + "\n" + hpScale + "\n" + coinScale + "\n" + approxEnemy + "\n" + relativeValues + "\n" + baseRelativeValues);
                fw.close();
            } catch (IOException e) {
                System.out.println("Error writing to file, aborting.");
                System.exit(1);
            }


            System.out.printf("Stats for %d runs have been written at %s\n", runs, f.getAbsolutePath());
            fw.close();
            System.exit(0);


        }
        if (player.getName().equalsIgnoreCase("scaleTest")) {
            int lvl = Helper.getInput("What level would you like to scale to?", 1000);
            int runs = 1000;
            int lvlDivisions = Helper.getInput("How many runs would you like to scale by?", Integer.MAX_VALUE);
            String mainDir = "./tests";
            if (!new File(mainDir).exists()) {
                new File(mainDir).mkdir();
            }
            String dirName = "/scaleData_";
            int fileSuffix = 1;

            while (new File(mainDir + dirName + fileSuffix + "/run_1/data.txt").exists()) {
                fileSuffix++;
            }

            new File(mainDir + dirName + fileSuffix).mkdir();
            for (int k = 0; k < lvl / lvlDivisions; k++) {
                String currentRunFile = "/run_" + lvlDivisions * (k + 1);
                String dataFile = mainDir + dirName + fileSuffix + currentRunFile + "/data.txt";
                String avgFile = mainDir + dirName + fileSuffix + currentRunFile + "/avg.txt";
                new File(mainDir + dirName + fileSuffix + currentRunFile).mkdir();
                File f = new File(dataFile);
                File avgF = new File(avgFile);
                try {
                    f.createNewFile();
                    avgF.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error creating file, aborting.");
                    System.exit(1);
                }
                fw = new FileWriter(f, true);

                int lvlCap = lvl / lvlDivisions * k;
                for (int i = 0; i < runs; i++) {
                    ArrayList<Item> tempInv = new ArrayList<>();
                    tempInv.add(ItemData.starterWeapon);
                    Player p = new Player("sampleTest", 40, 5, tempInv);
                    List<Enemy> spawns;
                    List<Enemy> tempenemies;
                    for (int j = 0; j < lvlCap; j++) {
                        spawns = Helper.getEnemies(p);
                        tempenemies = Helper.getRandomElements(spawns, (j % 5 == 4 ? 1 : 3));
                        for (Enemy e : tempenemies) {
                            e.randDrops(p, e);
                        }
                        Shop.statBuy(p);
                        if (p.getXp() >= p.getXpToLevel()) {
                            LevelUp a = new LevelUp();
                            a.levelPlayer(p);
                        }
                        getNewPlace();
                        p.incStageNum(1);
                    }

                    try {
                        writeToFile(i, p, f);
                    } catch (IOException e) {
                        System.out.println("Error writing to file, aborting.");
                        System.out.println();
                        System.exit(1);
                    }

                }

                System.out.println("All runs completed, calculating average stats");
                Player p = null;
                try {
                    Scanner sc = new Scanner(f);
                    int[] stats = new int[9];
                    int count = 0;
                    while (sc.hasNext()) {
                        try {
                            String line = sc.nextLine();
                            if (line.contains("Run")) {
                                count++;
                            } else if (line.contains("Health")) {
                                stats[0] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Damage")) {
                                stats[1] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Heal Amount")) {
                                stats[2] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Heal Variance")) {
                                stats[3] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Money")) {
                                stats[4] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Stage")) {
                                stats[5] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("XP")) {
                                stats[6] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("Level")) {
                                stats[7] += Integer.parseInt(line.split(": ")[1]);
                            } else if (line.contains("XP to Level")) {
                                stats[8] += Integer.parseInt(line.split(": ")[1]);
                            }
                        } catch (Exception ignored) {

                        }
                    }
                    sc.close();
                    for (int i = 0; i < stats.length; i++) {
                        stats[i] /= count;
                    }
                    ArrayList<Item> tempInv = new ArrayList<>();
                    tempInv.add(ItemData.starterWeapon);
                    p = new Player("sampleTest", stats[0], stats[1], tempInv);
                    p.setHealAmount(stats[2]);
                    p.setHealVariance(stats[3]);
                    p.setMoney(stats[4]);
                    p.setStageNum(stats[5]);
                    p.setXp(stats[6]);
                    p.setLevel(stats[7]);
                    p.setXpToLevel(stats[8]);
                    String avgStats = averageFile(stats);
                    try {
                        FileWriter fw = new FileWriter(avgF, true);
                        fw.write("\n" + avgStats);
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Error writing to file, aborting.");
                        System.exit(1);
                    }

                } catch (IOException e) {
                    System.out.println("Error reading from file, aborting.");
                    System.exit(1);
                }
//            Get approximate scale values of entities
                String dmgScale = "Damage scale attribute: " + Helper.getScaleFactor(1, lvl);
                String hpScale = "Health scale attribute: " + Helper.getScaleFactor(0, lvl);
                String coinScale = "Coin scale attribute: " + Helper.getScaleFactor(2, lvl);
                Enemy temp = new Warrior();
                temp.setBaseStats();
                temp.setLevel(lvl);
                temp.scaleStats();
                String approxEnemy = "Approximate stats for a level " + lvl + " enemy: " + temp;
                String relativeValues = "For an enemy scaled to " + lvl + ", you would...\n" + values(temp, p);
                Enemy temp2 = new Warrior();
                temp2.setBaseStats();
                ArrayList<Item> tempInv = new ArrayList<>();
                tempInv.add(ItemData.starterWeapon);

                String baseRelativeValues = "For a base enemy, you would... \n" + values(temp2, new Player("sampleTest", 40, 5, tempInv));


                try {
                    FileWriter fw = new FileWriter(avgF, true);
                    fw.write("\n" + dmgScale + "\n" + hpScale + "\n" + coinScale + "\n" + approxEnemy + "\n" + relativeValues + "\n" + baseRelativeValues);
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Error writing to file, aborting.");
                    System.exit(1);
                }


                System.out.printf("Stats for %d runs have been written at %s\n", runs, f.getAbsolutePath());
                fw.close();
            }


            System.exit(0);


        }
        //endregion

        for (int i = allInteracts.size() - 2; i >= 0; i--) {
            if (allInteracts.get(i).getName().contains("Quit")) {
                Interactable inter = allInteracts.getLast();
                allInteracts.set(allInteracts.size() - 1, allInteracts.get(i));
                allInteracts.set(i, inter);
            } else if (allInteracts.get(i).getName().contains("Battle")) {
                Interactable inter = allInteracts.getFirst();
                allInteracts.set(0, allInteracts.get(i));
                allInteracts.set(i, inter);
            } else if (allInteracts.get(i).getName().contains("Shop")) {
                Interactable inter = allInteracts.get(2);
                allInteracts.set(2, allInteracts.get(i));
                allInteracts.set(i, inter);
            } else if (allInteracts.get(i).getName().contains("Settings")) {
                Interactable inter = allInteracts.get(allInteracts.size() - 2);
                allInteracts.set(allInteracts.size() - 2, allInteracts.get(i));
                allInteracts.set(i, inter);
            } else if (allInteracts.get(i).getName().contains("Level Up")) {
                Interactable inter = allInteracts.get(1);
                allInteracts.set(1, allInteracts.get(i));
                allInteracts.set(i, inter);
            }
        }

        while (true) {
            System.out.print(Colors.RESET + Colors.CLEAR);
            System.out.println(
                    "You are currently in the " + Colors.YELLOW + currentPlace.getName() + Colors.RESET + ", " +
                            (player.getStageNum() % 5 == 0 ? Colors.RED_UNDERLINED + Colors.RED_BOLD + "on stage " + player.getStageNum() : "on stage " + player.getStageNum()) +
                            Colors.PURPLE);
            for (int i = 0; i < allInteracts.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allInteracts.get(i).getName());
            }
            int choice = -1 + Helper.getInput(Colors.RESET, allInteracts.size());
            allInteracts.get(choice).onChoose(player);

        }
    }

    private static String values(Enemy temp, Player p) {
        String relativeValues;
//            How many hits it would take to kill the enemy, and how many hits it would take for the enemy to kill the player
        int dmg = p.getDamage();
        int hp = p.getHp();
        for (Item i : p.getInventory()) {
            dmg += i.getDmgIncr();
            hp += i.getHpIncr();
        }
        int hitsToKill = (int) Math.ceil((double) temp.getBattleHp() / (p.getDamage()));
        int hitsToDie = (int) Math.ceil((double) p.getHp() / (temp.getDamage()));
        if (hitsToKill == 0) {
            relativeValues = "You would one-shot the enemy";
        } else if (hitsToKill == 1) {
            relativeValues = "You would kill the enemy in one hit";
        } else {
            relativeValues = "It would take " + hitsToKill + " hits to kill the enemy";
        }
        if (hitsToDie == 0) {
            relativeValues += "\nYou would be one-shot by the enemy";
        } else if (hitsToDie == 1) {
            relativeValues += "\nYou would die in one hit";
        } else {
            relativeValues += "\nYou would take " + hitsToDie + " hits to die.";
        }
        return relativeValues;
    }

    private static String averageFile(int[] stats) {
        String avgStats = "Average Stats:\n";
        avgStats += "Health: " + stats[0] + "\n";
        avgStats += "Damage: " + stats[1] + "\n";
        avgStats += "Heal Amount: " + stats[2] + "\n";
        avgStats += "Heal Variance: " + stats[3] + "\n";
        avgStats += "Money: " + stats[4] + "\n";
        avgStats += "Stage: " + stats[5] + "\n";
        avgStats += "XP: " + stats[6] + "\n";
        avgStats += "Level: " + stats[7] + "\n";
        avgStats += "XP to Level: " + stats[7] + "\n";
        return avgStats;
    }

    private static void writeToFile(int i, Player p, File f) throws IOException {
        int realHealth = p.getHp();
        int realDamage = p.getDamage();
        int realHealAmount = p.getHealAmount();
        int realHealVariance = p.getHealVariance();

        for (Item item : p.getInventory()) {
            realHealth += item.getHpIncr();
            realDamage += item.getDmgIncr();
            realHealAmount += item.getHealIncr();
            realHealVariance += item.getHealVarIncr();
        }
        String stats = "Run " + (i + 1) + ":\n";
        stats += "Health: " + realHealth + "\n";
        stats += "Damage: " + realDamage + "\n";
        stats += "Heal Amount: " + realHealAmount + "\n";
        stats += "Heal Variance: " + realHealVariance + "\n";
        stats += "Money: " + p.getMoney() + "\n";
        stats += "Stage: " + p.getStageNum() + "\n";
        stats += "XP: " + p.getXp() + "\n";
        stats += "Level: " + p.getLevel() + "\n";
        stats += "XP to Level: " + p.getXpToLevel() + "\n";

        fw.write("\n" + stats);
    }

    public static List<String> allPlayerFiles() {
        List<String> saves = new ArrayList<>();
        for (File f : Objects.requireNonNull(new File(".").listFiles())) {
            if (f.getName().endsWith(".plr")) {
                saves.add(f.getName());
            }

        }
        return saves;
    }

    public static Player loadSave() throws Exception {
        List<String> saves = allPlayerFiles();
        if (saves.isEmpty()) {
            System.out.println("No saves could be found");
            throw new Exception("no saves");
        }
        for (int i = 0; i < saves.size(); i++) {
            System.out.println("[" + i + "] " + saves.get(i));
        }
        return Player.loadFromFile((saves.get(Helper.getInput("Choose a save:", 0, saves.size() - 1))));
    }

    public static void getNewPlace() {
        try {

            do {
                currentPlace = allPlaces.get(r.nextInt(allPlaces.size())).getClass().getDeclaredConstructor().newInstance();
            } while (currentPlace.getName().equalsIgnoreCase("keeper's land") || currentPlace.getName().equalsIgnoreCase("starter land"));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * performs black magic to get all the types
     **/
    public static void initTypes() {

        try {
            for (String classpathEntry : System.getProperty("java.class.path").split(File.pathSeparator)) {
                System.out.println("Entry " + classpathEntry);
                // System.out.println("first loop");
                if (classpathEntry.endsWith(".jar")) {
                    System.out.println(".jar found");
                    File jar = new File(classpathEntry);
                    JarInputStream is;
                    is = new JarInputStream(new FileInputStream(jar));
                    JarEntry entry;
                    while ((entry = is.getNextJarEntry()) != null) {
                        //System.out.println("name" + entry.getName());
                        if (entry.getName().endsWith(".class") && entry.getName().contains("KeeperLand")) {
                            //  System.out.println(entry.getName());
                            String className = entry.getName().replace(".class", "");
                            //   className = className.substring(className.indexOf("/")+1);
                            System.out.println(className);
                            try {
                                Class.forName(className.replace("/", ".")).newInstance();
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        //if it didn't work, we are not from  a .jar and can use the old method. We will know by checking if allInteracts is empty
        if (!allInteracts.isEmpty()) {
            System.out.println("Loaded types from .jar");
            return;
        }
        System.out.println(".jar load failed retrying with different method, checking for java files");
        File folder = new File(".");
        initDirc(folder, "");
        /*for (Interactable i : allInteracts) {
            if (i.getName().equalsIgnoreCase("quit")) {
                allInteracts.remove(i);
                allInteracts.add(i);
                break;

            }
        }*/
        //Items?

    }

    public static void initDirc(File Dirc, String path) {
        File[] listOfFiles = Dirc.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && listOfFile.getName().endsWith(".java")) {
                try {
                    Class<?> s = Class.forName(
                            path + listOfFile.getName().substring(0, listOfFile.getName().indexOf(".java")));
                    s.newInstance();

                } catch (Exception ignored) {
                }
            } else if (listOfFile.isDirectory()) {
                initDirc(listOfFile, path + listOfFile.getName() + ".");
            }
        }
    }

}