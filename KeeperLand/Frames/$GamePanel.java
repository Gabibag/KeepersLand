package KeeperLand.Frames;

import javax.swing.*;
import java.awt.*;

public class $GamePanel extends JFrame {
    public $GamePanel() {
        //get the size of the users screen


        this.setUndecorated(true);
        this.setSize(size.width/3, size.height/3);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLocation(size.width / 2 - this.getSize().width / 2, size.height / 2 - this.getSize().height / 2);
        this.add(new $GameInterface());
        this.setVisible(true);
    }

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
}


