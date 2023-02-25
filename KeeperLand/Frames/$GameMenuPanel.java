package KeeperLand.Frames;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Main;

import javax.swing.*;
import java.awt.*;

import static KeeperLand.Main.mainPanel;
import static KeeperLand.Main.player;

public class $GameMenuPanel extends JFrame {
    private Interactable interacts;
    public $GameMenuPanel() {
        //set the size of the panel
        this.setSize($GamePanel.size.width, $GamePanel.size.height);
        //set the background color
        JLabel title = new JLabel("<html><h1>Title</h1></html>");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setBounds(this.getWidth()/2, 0, 100, 100);
        this.setUndecorated(true);
        this.add(title);
        this.setLayout(new GridLayout(Main.allInteracts.size()+1, 1));
        //set the visibility to true

        //add buttons for each item in the list allInteracts
        for (Interactable i : Main.allInteracts) {
            System.out.println(i.getName());
            JButton interact = new JButton(i.getName());
            interact.setBounds(0, 0, 100, 100);
            //add an action listener to the button
            interact.addActionListener(e -> {
            //set the interact to the interact that was clicked
                interacts = i;
                interacts.onChoose(player);
            //close the menu
            });
            this.add(interact);
        }
        //add a button to exit the menu

        this.setSize($GamePanel.size.width/3, $GamePanel.size.height/3);
        //set location to center
        this.setLocationRelativeTo(mainPanel);
        this.setVisible(true);
    }
}
