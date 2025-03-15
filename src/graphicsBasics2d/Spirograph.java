package graphicsBasics2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class Spirograph
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("Spirograph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new SpiroPanel());
        frame.pack();
        frame.setVisible(true);
    }
}

class SpiroPanel extends JPanel
{

    int nPoints = 1000;
    double r1 = 60;
    double r2 = 50;
    double p = 70;

    public SpiroPanel()
    {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(200, 200);
        int x1 = (int) (r1 + r2 - p);
        int y1 = 0;
        int x2;
        int y2;
        for (int i = 0; i < nPoints; i++)
        {
            double t = i * Math.PI / 90;
            x2 = (int) ((r1 + r2) * Math.cos(t) - p * Math.cos((r1 + r2) * t / r2));
            y2 = (int) ((r1 + r2) * Math.sin(t) - p * Math.sin((r1 + r2) * t / r2));
            g2.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }
}
