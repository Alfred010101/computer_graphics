package Ch2GraphicsBasics2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class Hello2D
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("Hello 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add( new Hello2DPanel());
        frame.pack();
        frame.setVisible(true);
    }
}

class Hello2DPanel extends JPanel
{

    public Hello2DPanel()
    {
        setPreferredSize(new Dimension(640, 480));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        Ellipse2D e = new Ellipse2D.Double(-100, -50, 200, 100);
        AffineTransform tr = new AffineTransform();
        tr.rotate(Math.PI / 6.0);
        Shape shape = tr.createTransformedShape(e);
        g2.translate(300, 200);
        g2.scale(2, 2);
        g2.draw(shape);
        g2.drawString("Hello 2D", 0, 0);
    }
}
