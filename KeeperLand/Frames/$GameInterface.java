package KeeperLand.Frames;

import KeeperLand.Enviroments.StarterLand;
import KeeperLand.Main;
import KeeperLand.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static KeeperLand.Main.*;


public class $GameInterface extends JPanel {
    //displays home screen
    public $GameInterface() {
        List<String> saveList = allPlayerFiles();

        this.add(new JLabel("Welcome to KeeperLand!", SwingConstants.CENTER));
        Button start = new Button("1. Start Game");
        start.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Name your Player");
            player = new Player(name, 40, 5,
                                new ArrayList<>());
            player.addMoney(50);
            player.setHealAmount(3);
            player.setHealVariance(1);
            Main.currentPlace = new StarterLand();
            mainPanel.setVisible(false);
            mainPanel.dispose();
            System.out.println("Start Game");
            new $GameMenuPanel();
        });
        Button load = new Button("2. Load Game");
        load.addActionListener(e -> {
            System.out.println("Load Game");
            try {
                player = loadSave();
                if (player == null) {
                    JOptionPane.showMessageDialog(this, "Hm. Theres an error atm. ");

                }
            } catch (Exception ignored) {

            }
        });
        System.out.println(player);
        Button exit = new Button("3. Exit");
        exit.addActionListener(e -> {
            System.exit(0);
        });
        this.add(start);
        this.add(load);
        this.add(exit);


    }
    //TODO: make this work
    public Player loadSave() throws Exception {
        AtomicBoolean selected = new AtomicBoolean(false);
        List<String> saves = allPlayerFiles();
        if (saves.size() == 0) {
            JOptionPane.showMessageDialog(this, "Hm. There isn't a save for you atm. ");
            throw new Exception("no saves");
        }
        JFrame savesList = new JFrame();
        savesList.add(new JLabel("Load A Game"));
        savesList.setSize($GamePanel.size.width / 6, $GamePanel.size.height / 6);
        savesList.move(mainPanel.getWidth()+savesList.getWidth()/2, mainPanel.getHeight() - savesList.getHeight());
        savesList.setLayout(new GridLayout(saves.size(), 1));
        for (String save : saves) {

            JButton saveButton = new JButton(save);
            saveButton.setAction(new AbstractAction() {
                public void actionPerformed(ActionEvent ae) {
                    synchronized (saveButton) {
                        saveButton.notify();
                    }
                }
            });
            /*saveButton.addActionListener(e -> {
                try {
                    player = Player.loadFromFile(save);
                    savesList.setVisible(false);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                selected.set(true);
                System.out.println("selected");
            });
            */savesList.add(saveButton);

        }

        System.out.println(savesList.isUndecorated());
        savesList.setVisible(true);

        return player;
    }
}
