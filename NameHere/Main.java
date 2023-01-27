package NameHere;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Abstracts.Interactable;
import NameHere.Enviroments.LavaZone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static List<Enviorment> allPlaces = new ArrayList<>();
    public static Enviorment currentPlace;
    public static List<Enemy> allEnemies = new ArrayList<>();
    public static Random r;
    public static List<Interactable> allInteracts = new ArrayList<>(); //adds everything that can be talked to(interacted) to an arraylist

    public static void main(String[] args) {
        s = new Scanner(System.in);
        r = new Random();
        initTypes();
        System.out.println(Colors.CLEAR + "Press ctrl + c to quit ;)");
        //defaults for player
        player = new Player(Helper.Prompt(Colors.CYAN + "Welcome \nEnter your player's name: " + Colors.RESET), 30, 5,
                            new ArrayList<>());
        player.addMoney(50);
        player.setHealAmount(3);
        player.setHealVariance(1);
        getNewPlace();
        if(player.getName().equals("among us")){
            player.incStageNum(900);
            System.out.println(Helper.getScaleFactor());
            player.setHealAmount(100);
            player.addMoney(99999);
            player.setDmg(500);
            System.out.println("sus");
            Main.currentPlace = new LavaZone();
        }
        while (true) {
            System.out.print(Colors.RESET + Colors.CLEAR);
            System.out.println("You are currently in the " + currentPlace.getName() + Colors.PURPLE);
            for (int i = 0; i < allInteracts.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allInteracts.get(i).getName());
            }
            int choice = -1 + Helper.getInput(Colors.RESET, allInteracts.size() + 1);
            //TODO choice validation
            allInteracts.get(choice).onChoose(player);
        }
    }


    public static void getNewPlace() {
        currentPlace = allPlaces.get(r.nextInt(allPlaces.size()));
        while (!currentPlace.isVaild(player)) {
            currentPlace = allPlaces.get(r.nextInt(allPlaces.size()));
        }
    }

    public static Player player;

    public static Scanner s;


    /**
     * peforms black magic to get all of the types
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