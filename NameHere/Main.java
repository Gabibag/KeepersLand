package NameHere;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

import NameHere.Abstracts.Enemy;
import NameHere.Abstracts.Enviorment;
import NameHere.Abstracts.Interactable;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.lang.instrument.Instrumentation;

public class Main {
    public static List<Enviorment> allPlaces = new ArrayList<Enviorment>();
    public static Enviorment currentPlace;
    public static List<Enemy> allEnemies = new ArrayList<Enemy>();
    public static Random r;
    public static List<Interactable> allInteracts = new ArrayList<Interactable>(); //adds everything that can be talked to(interacted) to an arraylist
    public static void main(String[] args){
         s = new Scanner(System.in);
         r = new Random();
        initTypes();
        System.out.println("Press ctrl + c to quit ;)");
        //defaults for player
        player = new Player(Prompt("Welcome \nEnter your players name:"),100, 1, new ArrayList<Item>());
        player.addMoney(50);
        getNewPlace();
        while(true){
            System.out.println("chose one option");
            for(int i = 0; i < allInteracts.size(); i ++){
                System.out.println("[" + (i + 1) +"] " + allInteracts.get(i).getClass().getName());
            }
            int choice = -1 + getInput("Make your choice: ");
            //TODO choice vaildation
            allInteracts.get(choice).OnChoose(player);
        }
    }
    //read one int from user
    public static int getInput(String msg){
        try{
            return Integer.parseInt(Prompt(msg));
            }catch(Exception e){
            System.out.println("Bad input, try again");
            return getInput(msg);    
            }
            
    }
    public static void getNewPlace(){
        currentPlace = allPlaces.get(r.nextInt(allPlaces.size()));
        while(!currentPlace.isVaild(player)){
            currentPlace = allPlaces.get(r.nextInt(allPlaces.size()));
        }
    }
    public static Player player;

    public static Scanner s;
    //says a prompt then returns the next line, advances the stream by the length of the line
    public static String Prompt(String msg){
        System.out.println(msg);
        return s.nextLine();
    }
    //looks through files in current directory, then checks if they're java files, if they are,
    //it tries to create a new instance of the class given by the file and using that [to do something]
    public static void initTypes(){
        File folder = new File(".");
        initDirc(folder, "");
    }
    public static void initDirc(File Dirc, String path){
        File[] listOfFiles = Dirc.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".java")) {
                try{
                    Class<?> s = Class.forName(path + listOfFiles[i].getName().substring(0, listOfFiles[i].getName().indexOf(".java")));
                    s.newInstance();
                    
                }
                catch(Exception e){
                    System.out.println("failed to create " + e);
                    continue;
                }
            } else if (listOfFiles[i].isDirectory()) {
                initDirc(listOfFiles[i],   path +   listOfFiles[i].getName() +  ".");
            }
          }
    }
}
