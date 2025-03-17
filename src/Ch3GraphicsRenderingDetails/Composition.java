package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Composition extends JPanel
{

    public Composition()
    {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.white);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Composition(), "Transformation Composition").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(100, 100);
        Shape e = new Ellipse2D.Double(300, 200, 200, 100);
        g2.setColor(new Color(160, 160, 160));
        g2.fill(e);
        AffineTransform transform = new AffineTransform();
        transform.translate(-400, -250);
        e = transform.createTransformedShape(e);
        g2.setColor(new Color(220, 220, 220));
        g2.fill(e);
        g2.setColor(Color.black);
        g2.drawLine(0, 0, 150, 0);
        g2.drawLine(0, 0, 0, 150);
        transform.setToRotation(Math.PI / 6.0);
        e = transform.createTransformedShape(e);
        g2.setColor(new Color(100, 100, 100));
        g2.draw(e);
        transform.setToTranslation(400, 250);
        e = transform.createTransformedShape(e);
        g2.setColor(new Color(0, 0, 0));
        g2.draw(e);
    }
}
