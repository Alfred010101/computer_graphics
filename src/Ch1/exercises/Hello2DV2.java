package Ch1.exercises;

import Ch1.GV;
import Ch1.SnippetFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;



public class Hello2DV2 extends JPanel 
{
    public static void main(String[] args) 
    {
        new SnippetFrame(new Hello2DV2(), "Hello2D V2 Panel").setVisible(true);
    }
    
    public Hello2DV2()
    {
        super(true);
        setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
        setBackground(GV.PALE_GREEN_COLOR);
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int n = 30;
    
        Ellipse2D ellipse = new Ellipse2D.Double(-100, -50, 200, 100);
        Shape ellipseR;
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI / 6.0);
    
        Shape shape = transform.createTransformedShape(ellipse);
    
        g2.translate(300,200);
        g2.setColor(Color.magenta);
        for(int i=0;i<=n;i++)
        {
            transform.rotate(2*Math.PI / n*i);
            ellipseR = transform.createTransformedShape(ellipse);
            g2.draw(ellipseR);
        }
        g2.scale(2,2);
        g2.setColor(Color.blue);
        g2.draw(shape);
        g2.setColor(Color.red);
        g2.drawString("Hello 2D", 0, 0);
    }
}

