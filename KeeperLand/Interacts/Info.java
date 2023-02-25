package KeeperLand.Interacts;

import KeeperLand.Abstracts.Interactable;
import KeeperLand.Player;

import javax.swing.*;

public class Info extends Interactable {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onChoose(Player p) {
        //print out all variables in the class player
        JOptionPane info = new JOptionPane();
        JOptionPane.showMessageDialog(null, p);

    }
}
