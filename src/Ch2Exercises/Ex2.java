package Ch2Exercises;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex2
{

    public static void main(String s[])
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Ex2Panel());
        frame.pack();
        frame.setVisible(true);
    }
}

class Ex2Panel extends JPanel
{

    int nPoints = 1000;

    public Ex2Panel()
    {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(400, 400);
        g2.scale(1, -1);
        g2.drawLine(0, -400, 0, 400);
        g2.drawLine(-400, 0, 400, 0);
        int x1 = 0;
        int y1 = 0;
        int x2;
        int y2;
        for (int i = 0; i < nPoints; i++)
        {
            double t = i * 8 * Math.PI / nPoints;
            x2 = (int) (20 * t * Math.cos(t));
            y2 = (int) (20 * t * Math.sin(t));
            g2.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }
}
