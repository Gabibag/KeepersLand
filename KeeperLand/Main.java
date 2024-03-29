package KeeperLand;

import KeeperLand.Abstracts.*;
import KeeperLand.Enviroments.GatesToHell;
import KeeperLand.Enviroments.LavaZone;
import KeeperLand.Enviroments.NullZone;
import KeeperLand.Enviroments.StarterLand;
import KeeperLand.Interacts.Battle;
import KeeperLand.Interacts.Inventory;
import KeeperLand.Interacts.LevelUp;
import KeeperLand.Interacts.Shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class Main {
    public static final List<Enviorment> allPlaces = new ArrayList<>();
    public static Enviorment currentPlace;
    public static final List<Enemy> allEnemies = new ArrayList<>();
    public static final List<Item> allItem = new ArrayList<>();
    public static final List<Mutations> allMutations = new ArrayList<>();
    public static final List<StatusEffects> allStatusEffects = new ArrayList<>();

    public static final List<Sprite> allSprites = new ArrayList<>();

    public static final List<Boss> allBosses = new ArrayList<>();
    public static Random r;
    public static final List<Interactable> allInteracts = new ArrayList<>(); //adds everything that can be talked to(interacted) to an arraylist
    public static Player player;
    public static Scanner s;

    public static void main(String[] args) {

        s = new Scanner(System.in);
        r = new Random();

        initTypes();
        Enemy.loaded = true;
        System.out.println(Colors.CLEAR + "Press ctrl + c to quit ;)");

        //defaults for player
        List<String> saveList = allPlayerFiles();
        int saves = 0;
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
            player = new Player(name, 40, 5,
                    new ArrayList<>());
            player.addMoney(50);
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
        } else if (player.getName().contains("playtest") || player.getName().contains("ptest")) {
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < 19; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                getNewPlace();
            }
            player.incStageNum(19);
            System.out.println("sussy");
            Main.currentPlace = new LavaZone();
        } else if (player.getName().contains("runThrough") || player.getName().contains("rtest")) {
            System.out.println(Integer.MAX_VALUE);
            int lvl = Helper.getInput("What level would you like to be at?", Integer.MAX_VALUE);
            int divs = Math.max(lvl / 10, 1);

            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);
                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                Shop.statBuy(player);
                player.incStageNum(1);
                getNewPlace();
                if (i % divs == 0) {
                    System.out.println("Player is at level " + player.getStageNum() + "/" + lvl + " (" + (int) (100.0 * i / lvl) + "%)");
                    Helper.checkForComplexCreation(Main.allItem);
                }
            }
            currentPlace = new NullZone();
            if (lvl > 99_999) {
                for (int i = 0; i < allPlaces.size(); i++) {
                    System.out.println("[" + i + "] " + allPlaces.get(i).getName());
                }
                int location = Helper.getInput("What location would you like to be at?", 0, allPlaces.size());

                try {
                    Main.currentPlace = allPlaces.get(location - 1).getClass().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                currentPlace = allPlaces.get(location);
            }
            //add all items that have the word "shard" in them from the list allItems
            allItem.stream().filter(i -> i.getName().toLowerCase().contains("shard") && !i.getName().toLowerCase().contains("keeper")).forEach(i -> player.addInventory(i));

            System.out.println("amogus");
        } else if (player.getName().equalsIgnoreCase("bossTest") || player.getName().equalsIgnoreCase("btest")) {
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < 40; i++) {
                spawns = Battle.getEnemies(player);
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
        } else if (player.getName().equalsIgnoreCase("StatsTest") || player.getName().equalsIgnoreCase("stest")) {
            int lvl = Helper.getInput("What level would you like to be at?", 99999999);
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

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
        //endregion
        for (int i = allInteracts.size() - 2; i >= 0; i--) {
            if (allInteracts.get(i).getName().contains("Quit")) {
                Interactable inter = allInteracts.get(allInteracts.size() - 1);
                allInteracts.set(allInteracts.size() - 1, allInteracts.get(i));
                allInteracts.set(i, inter);
            } else if (allInteracts.get(i).getName().contains("Battle")) {
                Interactable inter = allInteracts.get(0);
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
        if (saves.size() == 0) {
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
     * peforms black magic to get all the types
     **/
    public static void initTypes() {
        try {
            for (String classpathEntry : System.getProperty("java.class.path").split(System.getProperty("path.separator"))) {
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
                            } catch (Exception e) {
                                //    System.out.println("failed to load class " + e);
                                //       System.out.println(className.length());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        //if it didnt work, we are not from  a .jar and can use the old method. We will know by checking if allInteracts is empty
        if (allInteracts.size() > 0) {
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
                    //System.out.println(ignored);
                }
            } else if (listOfFile.isDirectory()) {
                initDirc(listOfFile, path + listOfFile.getName() + ".");
            }
        }
    }

}