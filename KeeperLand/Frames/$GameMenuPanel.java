package KeeperLand.Frames;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Main;

import javax.swing.*;

import static KeeperLand.Main.mainPanel;

public class $GameMenuPanel extends JFrame {
    private Interactable interacts;
    public $GameMenuPanel() {
        //set the size of the panel
        this.setSize($GamePanel.size.width, $GamePanel.size.height);
        //set the background color
        this.setLayout(null);
        //set the visibility to true

        //add buttons for each item in the list allInteracts
        for (Interactable i : Main.allInteracts) {
            JButton interact = new JButton(i.getName());
            interact.setBounds(0, 0, 100, 100);
            //add an action listener to the button
            interact.addActionListener(e -> {
            //set the interact to the interact that was clicked
                interacts = i;
            //close the menu
            });
            this.add(interact);
        }
        System.out.println("a");
        //add a button to exit the menu
        JButton exit = new JButton("Exit");
        exit.setBounds(0, 0, 100, 100);
        exit.addActionListener(e -> {
            //close the menu
            System.exit(0);
        });
        this.add(exit);
        this.setSize($GamePanel.size.width / 6, $GamePanel.size.height / 6);
        this.move(mainPanel.getWidth()+this.getWidth()/2, mainPanel.getHeight() - this.getHeight());
        System.out.println("b");
        this.setVisible(true);
        System.out.println("b");
    }
}
