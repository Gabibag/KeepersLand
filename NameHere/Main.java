package NameHere;

import NameHere.Abstracts.*;
import NameHere.Enviroments.GatesToHell;
import NameHere.Enviroments.LavaZone;
import NameHere.Enviroments.NullZone;
import NameHere.Interacts.Battle;
import NameHere.Interacts.LevelUp;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Main {
    public static List<Enviorment> allPlaces = new ArrayList<>();
    public static Enviorment currentPlace;
    public static List<Enemy> allEnemies = new ArrayList<>();
    public static List<Item> allItem = new ArrayList<>();

    public static List<Spirit> allSpirits = new ArrayList<>();

    public static List<Boss> allBosses = new ArrayList<>();
    public static Random r;
    public static List<Interactable> allInteracts = new ArrayList<>(); //adds everything that can be talked to(interacted) to an arraylist
    public static Player player;
    public static Scanner s;
    public static void main(String[] args) {

        s = new Scanner(System.in);
        r = new Random();
        initTypes();
        System.out.println(Colors.CLEAR + "Press ctrl + c to quit ;)");
        //defaults for player
        List<String> saveList = allPlayerFiles();
        int saves = 0;
        if (saveList.size() != 0) {
            saves = Helper.getInput("[0] New save \n[1] Load Save", 0, 1);
        }
        if (saves == 1) {
            try {
                player = loadSave();
                if (player == null) {
                    System.out.println("Corrupted Save, creating new player instead");
                    saves = 0;
                }
            } catch (Exception e) {

                saves = 0;
            }

        }
        if (saves == 0) {
            List<String> takenNames = allPlayerFiles();
            takenNames.replaceAll(s1 -> s1.substring(0, s1.length() - 4));
            String name = Helper.Prompt(Colors.CYAN + "Welcome \nEnter your player's name: " + Colors.RESET);
            while (takenNames.contains(name)) {
                name = Helper.Prompt(
                        Colors.RED + "That name is already taken, please enter a new name: " + Colors.RESET);
            }
            player = new Player(name, 40, 5,
                                new ArrayList<>());
            player.addMoney(50);
            player.setHealAmount(3);
            player.setHealVariance(1);
        }
        getNewPlace();

        if (player.getName().equals("among us") || player.getName().equals("test")) {
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
        else if (player.getName().equalsIgnoreCase("playtest") || player.getName().equalsIgnoreCase("ptest")) {
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
        }
        else if (player.getName().equalsIgnoreCase("runThrough") || player.getName().equalsIgnoreCase("rtest")) {
            int lvl = Helper.getInput("What level would you like to be at?", 99999999);
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }

                /*if (player.getStageNum()%9 == 0){
                    Shop.superBuy(player);
                }*/
                player.incStageNum(1);
                getNewPlace();
            }
            for (int i = 0; i < allPlaces.size(); i++) {
                System.out.println("[" + i + "] " + allPlaces.get(i).getName());
            }

            int location = Helper.getInput("What location would you like to be at?", allPlaces.size());

            try {
                Main.currentPlace = allPlaces.get(location - 1).getClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            currentPlace = allPlaces.get(location);
            //add all items that have the word "shard" in them from the list allItems
            for (Item i : allItem) {
                if (i.getName().toLowerCase().contains("shard")) {
                    player.addInventory(i);
                }
            }

            System.out.println("amogus");
        }
        else if (player.getName().equalsIgnoreCase("bossTest") || player.getName().equalsIgnoreCase("btest")) {
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < 99; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }

                /*if (player.getStageNum()%9 == 0){
                    Shop.superBuy(player);
                }*/
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
                    player.addInventory(i);
                    player.addInventory(i);
                }
            }
//            player.setDamage(player.getDamage()*500);
            LevelUp a = new LevelUp();
            a.onChoose(player);
            System.out.println("amogngnus");
        }
        else if (player.getName().equalsIgnoreCase("StatsTest") || player.getName().equalsIgnoreCase("stest")) {
            int lvl = Helper.getInput("What level would you like to be at?", 99999999);
            List<Enemy> spawns;
            List<Enemy> tempenemies;
            for (int i = 0; i < lvl; i++) {
                spawns = Battle.getEnemies(player);
                tempenemies = Helper.getRandomElements(spawns, 3);

                for (Enemy e : tempenemies) {
                    e.randDrops(player, e);
                }
                /*if (player.getStageNum()%9 == 0){
                    Shop.superBuy(player);
                }*/
                getNewPlace();
                player.incStageNum(1);
            }

            //level up player
            LevelUp a = new LevelUp();
            a.onChoose(player);

            System.out.println(player);
            System.out.println("amogsus");
            System.exit(0);
        }
        while (true) {
            System.out.print(Colors.RESET + Colors.CLEAR);
            System.out.println(
                    "You are currently in the " + Colors.RED + currentPlace.getName() + Colors.RESET + ", on stage " +
                    player.getStageNum() +
                    Colors.PURPLE);
            for (int i = 0; i < allInteracts.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allInteracts.get(i).getName());
            }
            int choice = -1 + Helper.getInputDefault(Colors.RESET, allInteracts.size(), 6);
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
            currentPlace = allPlaces.get(r.nextInt(allPlaces.size())).getClass().getDeclaredConstructor().newInstance();
        
        while (!currentPlace.isVaild(player)) {
            currentPlace = allPlaces.get(r.nextInt(allPlaces.size()));
        }
    }catch (Exception e){
        System.out.println("Error getting new place, invaild constructor, trying again");
        getNewPlace();
    }
    }

    /**
     * peforms black magic to get all the types
     **/
    public static void initTypes() {
        File folder = new File(".");
        initDirc(folder, "");
        for (Interactable i : allInteracts) {
            if (i.getName().equalsIgnoreCase("quit")) {
                allInteracts.remove(i);
                allInteracts.add(i);
                break;

            }
        }
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
                    System.out.println(ignored);
                }
            }
            else if (listOfFile.isDirectory()) {
                initDirc(listOfFile, path + listOfFile.getName() + ".");
            }
        }
    }

}