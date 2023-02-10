package NameHere.Interacts;

import NameHere.Abstracts.Interactable;
import NameHere.Colors;
import NameHere.Helper;
import NameHere.Main;
import NameHere.Player;

import java.io.File;
import java.util.List;

public class Settings extends Interactable{

    public void onChoose(Player p){
        System.out.println("Settings:");
        System.out.println("[0] Exit");
        System.out.println("[1] Change Name");
        System.out.println("[2] Modify Saves");
        System.out.println("[3] Save");
        System.out.println((Helper.speedMode ? Colors.GREEN : Colors.RESET) + "[4] Toggle Speed Mode" + Colors.RESET);
        int choice = Helper.getInput("Enter a number: ", 0, 4);
        switch (choice) {
            case 0 -> {
                return;
            }
            case 1 -> {
                new File(p.getName() + ".plr").delete();
                p.setName(Helper.Prompt("Enter a new name: "));
                p.Save(p.getName() + ".plr");
            }
            case 2 -> {
                System.out.println("Modify Saves:");
                System.out.println("[0] Exit");
                System.out.println("[1] Delete Save");
                int choice2 = Helper.getInput("Enter a number: ", 0, 3);
                switch (choice2) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        List<String> ps = Main.allPlayerFiles();
                        System.out.println("[0] Exit");
                        for (String s : ps) {
                            System.out.println("[" + (ps.indexOf(s) + 1) + "] " + s);
                        }
                        System.out.println("Choose a save to delete");
                        int choice3 = Helper.getInput("Enter a number: ", 0, ps.size());
                        if (choice3 == 0) {
                            break;
                        }
                        File f = new File(ps.get(choice3 - 1));
                        if (f.delete()) {
                            System.out.println("Save deleted successfully");
                        }
                        else {
                            System.out.println("Failed to delete save");
                        }
                    }
                }
            }
            case 3 -> p.Save(p.getName() + ".plr");
            case 4 -> {
                Helper.speedMode = !Helper.speedMode ;
                System.out.println("Toggled Speed mode to " + (Helper.speedMode ? "on" : "off"));
                Helper.contiuePrompt();
            }

        }
        this.onChoose(p);
    }
    public String getName(){
        return "Settings";
    }
}
