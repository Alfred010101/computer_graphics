package Ch2Exercises;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex4
{

    public static void main(String s[])
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Ex4Panel());
        frame.pack();
        frame.setVisible(true);
    }
}

class Ex4Panel extends JPanel
{

    public Ex4Panel()
    {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D r = new Rectangle2D.Double(0, 0, 50, 50);
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if ((x + y) % 2 == 0)
                {
                    g2.draw(r);
                } else
                {
                    g2.fill(r);
                }
                g2.translate(50, 0);
            }
            g2.translate(-400, 50);
        }
    }
}
