package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Frames.$GamePanel;
import KeeperLand.Item;
import KeeperLand.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static KeeperLand.Main.mainPanel;

public class Inventory extends Interactable{
    public void onChoose(Player p){
        JOptionPane info = new JOptionPane();
        Object[] options1 = {"Go Back", "Inventory", "Stats"};
        Object result = JOptionPane.showInputDialog(null, "Enter a Number",
                                    "Info", JOptionPane.QUESTION_MESSAGE,
                                    null, options1, null);

        //if c is 1, show inventory, case c is 2, print player p
        if(result.equals("Inventory")){
            inventory(p);
        }else if(result.equals("Stats")){
            JDialog stats = new JDialog();
            JOptionPane.showMessageDialog(stats, p);
        }

    }

    private void inventory(Player p) {
        JDialog inventory = new JDialog();
        inventory.setTitle(p.getName() + "'s Inventory");
        inventory.add(new JLabel("<HTML><h1>Current Balance: " + p.getMoney() + "◊<h1/><HTML/>", SwingConstants.CENTER));
        HashMap<String, Integer> iCount = new HashMap<>();
        for(Item i: p.getInventory()){
            if(iCount.containsKey(i.getName())){
                iCount.put(i.getName(), iCount.get(i.getName()) + 1);
            }else{
                iCount.put(i.getName(), 1);
            }
        }
        List<Item> printItems = new ArrayList<Item>();

        for(Item i: p.getInventory()){
            if(!printItems.contains(i)){
                printItems.add(i);
                inventory.add(new JLabel(i.getName() + " x" + iCount.get(i.getName())));
            }

        }
        //make a text field
        //make a string list and add the name of all items
        //add a button that says "inspect"
        //when inspect is clicked, show a new dialog with the item's name and description
        inventory.setLayout(new GridLayout(printItems.size() + 2, 1));
        inventory.setSize($GamePanel.size.width / 3, $GamePanel.size.height / 3);
        inventory.setLocationRelativeTo(mainPanel);
        if (printItems.size() > 0) {
            JButton inspect = new JButton("Inspect");
            inventory.add(inspect);

            inspect.addActionListener(e -> {
                Object[] options2 = printItems.toArray();
                Object result = JOptionPane.showInputDialog(null, "Enter a Number",
                                                            "Info", JOptionPane.QUESTION_MESSAGE,
                                                            null, options2, null);
                int input = Integer.parseInt(result.toString());
                if (input != 0) {
                    Item inspecteditem = printItems.get(input - 1);
                    JDialog inspectItem = new JDialog();
                    inspectItem.setTitle(inspecteditem.getName());
                    inspectItem.add(new JLabel(inspecteditem.getName()));
                    inspectItem.add(new JLabel(inspecteditem.getDesc()));
                    inspectItem.setVisible(true);
                    inventory(p);
                }
                for (Item i : printItems) {
                    inventory.add(new JLabel(i.getName()));
                }
            });
        }else {
            inventory.add(new JLabel("You have no items", SwingConstants.CENTER));
        }





        inventory.setVisible(true);


    }

    public String getName() {
        return "Info";
    }

}