import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import  javax.swing.event.*;
import  java.awt.image.*;
public class myminipaint
{

    int  xod=0;
    int  lastx;
    int  x;
    int  y;
    int  lasty;
    boolean kl=false;
    Color maincolor;
    MyFrame f;
    MyPanel japan;
    JButton colorbutton;
    JColorChooser tcc;
    BufferedImage imag;
    public myminipaint()
    {
        f=new MyFrame("My mini paint");
        f.setSize(1000,1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maincolor=Color.white;

        JMenuBar menuBar = new  JMenuBar();
        f.setJMenuBar(menuBar);
        menuBar.setBounds(0,0,60,30);


        japan = new  MyPanel();
        japan.setBounds(50,30,1000,1000);
        japan.setBackground(Color.white);
        japan.setOpaque(true);
        f.add(japan);

        JToolBar toolbar = new  JToolBar("Knopkalar turatn djer", JToolBar.VERTICAL);
        JButton pen = new  JButton();
        pen.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                xod=0;
            }
        });
        toolbar.add(pen);
        JButton kist = new  JButton();
        kist.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                xod=1;
            }
        });
        toolbar.add(kist);

        JButton lastic = new JButton();
        lastic.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                xod=2;
            }
        });
        toolbar.add(lastic);
        JButton linea = new  JButton();
        linea.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                xod=4;
            }
        });
        toolbar.add(linea);

        JButton krug = new  JButton();
        krug.addActionListener(new  ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                xod=5;
            }
        });
        toolbar.add(krug);

        JButton kvadrat = new  JButton();
        kvadrat.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                xod=6;
            }
        });
        toolbar.add(kvadrat);

        toolbar.setBounds(0, 0, 30, 300);
        f.add(toolbar);

        // Тулбар для кнопок
        JToolBar colorbar = new  JToolBar("Svet tandaitn zher", JToolBar.HORIZONTAL);
        colorbar.setBounds(30, 0, 500, 500);
        colorbutton = new  JButton();
        colorbutton.setBackground(maincolor);
        colorbutton.setBounds(15, 5, 20, 20);
        colorbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                ColorDialog color = new  ColorDialog(f,"Svet tandaitn meniu");
                color.setSize(500,500);
                color.setLocation(300,200);
                color.setVisible(true);
            }
        });
        colorbar.add(colorbutton);


        colorbar.setLayout(null);
        f.add(colorbar);

        tcc = new  JColorChooser(maincolor);
        tcc.getSelectionModel().addChangeListener(new  ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                maincolor = tcc.getColor();
                colorbutton.setBackground(maincolor);
            }
        });
        japan.addMouseMotionListener(new  MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if (kl==true)
                {
                    Graphics g = imag.getGraphics();
                    Graphics2D g2 = (Graphics2D)g;
                    // установка цвета
                    g2.setColor(maincolor);
                    switch (xod)
                    {
                        // карандаш
                        case 0:
                            g2.drawLine(lastx, lasty, e.getX(), e.getY());
                            break;
                        // кисть
                        case 1:
                            g2.setStroke(new  BasicStroke(3.0f));
                            g2.drawLine(lastx, lasty, e.getX(), e.getY());
                            break;
                        // ластик
                        case 2:
                            g2.setStroke(new  BasicStroke(50.0f));
                            g2.setColor(Color.WHITE);
                            g2.drawLine(lastx, lasty, e.getX(), e.getY());
                            break;
                    }
                    lastx=e.getX();
                    lasty=e.getY();
                }
                japan.repaint();
            }
        });
        japan.addMouseListener(new  MouseAdapter()
        {

            public void mousePressed(MouseEvent e) {
                lastx=e.getX();
                lasty=e.getY();
                x=e.getX();
                y=e.getY();
                kl=true;
            }
            public void mouseReleased(MouseEvent e) {

                Graphics g = imag.getGraphics();
                Graphics2D g2 = (Graphics2D)g;
                // установка цвета
                g2.setColor(maincolor);
                // Общие рассчеты для овала и прямоугольника
                int  x1=x, x2=lastx, y1=y, y2=lasty;
                if(x>lastx)
                {
                    x2=x; x1=lastx;
                }
                if(y>lasty)
                {
                    y2=y; y1=lasty;
                }
                switch(xod)
                {
                    // линия
                    case 4:
                        g.drawLine(x, y, e.getX(), e.getY());
                        break;
                    // круг
                    case 5:
                        g.drawOval(x1, y1, (x2-x1), (y2-y1));
                        break;
                    // прямоугольник
                    case 6:
                        g.drawRect(x1, y1, (x2-x1), (y2-y1));
                        break;
                }
                x=0; y=0;
                kl=true;
                japan.repaint();
            }
        });
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  myminipaint();
            }
        });
    }

    class ColorDialog extends JDialog
    {
        public ColorDialog(JFrame owner, String title)
        {
            super(owner, title, true);
            add(tcc);
            setSize(200, 200);
        }
    }

    class MyFrame extends JFrame
    {
        public MyFrame(String title)
        {
            super(title);
        }
    }

    class MyPanel extends JPanel
    {
        public void paintComponent (Graphics g)
        {
            if(imag==null)
            {
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            super.paintComponent(g);
            g.drawImage(imag, 0, 0,this);
        }
    }
}