package Ch1.exercises;

import Ch1.SnippetFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alfred
 */
public class Ex5AreaGeometry extends JPanel
{

    public Ex5AreaGeometry()
    {
        setPreferredSize(new Dimension(760, 400));
    }
    
    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex5AreaGeometry(), "Constructive Area Geometry").setVisible(true));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Shape s1 = new Ellipse2D.Double(0, 0, 100, 100);
        Shape s2 = new Ellipse2D.Double(60, 0, 100, 100);
        
        
        g2.translate(20, 50);
        g2.draw(s1);
        g2.draw(s2);
        g2.translate(0, 200);
        
        Area a1 = new Area(s1);
        Area a2 = new Area(s2);
        
        a1.add(a2);
        g2.fill(a1);
        g2.translate(180, 0);
        a1 = new Area(s1);
        a1.intersect(a2);
        g2.fill(a1);
        g2.translate(180, 0);
        a1 = new Area(s1);
        a1.subtract(a2);
        g2.fill(a1);
        g2.translate(180, 0);
        a1 = new Area(s1);
        a1.exclusiveOr(a2);
        g2.fill(a1);
    }
}
