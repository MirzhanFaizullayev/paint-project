import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class fgk extends JFrame {
    private int lastX, lastY;

    public fgk() {
        super("My mini paint");

//  _______________________________________________________
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
            }
        };
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        //Container contenant = getContentPane();
        drawingPanel.setBackground(Color.white);

//       ___________________________________________________

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println("1");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastX=lastY=0;
            }



        });



        drawingPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Graphics g = drawingPanel.getGraphics();
                if(lastX==0&&lastY==0){
                    lastX = evt.getX();
                    lastY = evt.getY();
                }

                g.drawLine(lastX, lastY, evt.getX(), evt.getY());
                lastX = evt.getX();
                lastY = evt.getY();


                }



        });

//      __________________________________________
        add(drawingPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new fgk();
    }
}