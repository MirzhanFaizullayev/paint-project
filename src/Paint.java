import jdk.jfr.internal.tool.Main;

import javax.swing.*;
import java.awt.*;

public class Paint extends JComponent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My mini paint");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocation(500,300);
        frame.getContentPane().add(new Paint());
        frame.setVisible(true);

    }
}
