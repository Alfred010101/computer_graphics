package Ch2Exercises;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex6
{

    public static void main(String s[])
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Ex6Panel());
        frame.pack();
        frame.setVisible(true);
    }
}

class Ex6Panel extends JPanel
{

    public Ex6Panel()
    {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(100, 50);
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        path.moveTo(150, 0);
        path.curveTo(-50, 50, -50, 250, 150, 300);
        path.quadTo(50, 150, 150, 0);
        path.closePath();
        g2.fill(path);
    }
}
