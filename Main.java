import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.lang.*;
import java.lang.instrument.Instrumentation;

public class Main {
    public static List<Interactable> allInteracts = new ArrayList<Interactable>();
    public static void main(String[] args){
        s = new Scanner(System.in);
        Random r = new Random();
        initTypes();
        System.out.println("Press ctrl + c to quit ;)");
        //defaults for player
        
        player = new Player(Prompt("Welcome \n Enter your players name:"),100, 1, new Item[10]);
        while(true){
            //get 3 random interacts 
            //TODO make an enviroment class that gets the list, current envviroment that can change
            Interactable[] choices ={  allInteracts.get(r.nextInt(0, allInteracts.size())), allInteracts.get(r.nextInt(0, allInteracts.size())), allInteracts.get(r.nextInt(0, allInteracts.size())) };
            System.out.println("chose one option");
            for(int i = 0; i < choices.length; i ++){
                System.out.println("[" + (i + 1) +"] " + choices[i].getClass().getName());
            }
            int choice = -1 + getInput("Make your choice: ");
            //TODO choice vaildation
            choices[choice].OnChoose(player);
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
    public static Player player;
    public static Scanner s;
    //says a prompt then returns the next line, advances the stream by the length of the line
    public static String Prompt(String msg){
        System.out.println(msg);
        return s.nextLine();
    }
    public static void initTypes(){
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".java")) {
                try{
                    Class<?> s = Class.forName(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().indexOf(".java")));
                    s.newInstance();
                }
                catch(Exception e){
                    continue;
                }
            } else if (listOfFiles[i].isDirectory()) {
                //Will do later if needed
            }
          }
    }
}
