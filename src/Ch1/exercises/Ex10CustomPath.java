package Ch1.exercises;

import Ch1.SnippetFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alfred
 */
public class Ex10CustomPath extends JPanel
{

    public Ex10CustomPath()
    {
        setPreferredSize(new Dimension(640, 480));
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex10CustomPath(), "GeneralPath and Winding Rules").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        float x0 = 1.0f;
        float y0 = 0.0f;
        float x1 = (float) Math.cos(2 * Math.PI / 5.0);
        float y1 = (float) Math.sin(2 * Math.PI / 5.0);
        float x2 = (float) Math.cos(4 * Math.PI / 5.0);
        float y2 = (float) Math.sin(4 * Math.PI / 5.0);
        float x3 = (float) Math.cos(6 * Math.PI / 5.0);
        float y3 = (float) Math.sin(6 * Math.PI / 5.0);
        float x4 = (float) Math.cos(8 * Math.PI / 5.0);
        float y4 = (float) Math.sin(8 * Math.PI / 5.0);
        path.moveTo(x2, y2);
        path.lineTo(x0, y0);
        path.lineTo(x3, y3);
        path.lineTo(x1, y1);
        path.lineTo(x4, y4);
        path.closePath();
        AffineTransform tr = new AffineTransform();
        tr.setToScale(100, 100);
        g2.translate(120, 120);
//        path = (GeneralPath) tr.createTransformedShape(path);
        path = (GeneralPath) path.createTransformedShape(tr);
        g2.draw(path);
        g2.translate(200, 0);
        g2.fill(path);
        path.setWindingRule(GeneralPath.WIND_NON_ZERO);
        g2.translate(200, 0);
        g2.fill(path);

        path.reset();
        path.moveTo(x0, y0);
        path.lineTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.closePath();
        path.moveTo(x0, y0);
        path.quadTo(x4, y4, x1, y1);
        path.quadTo(x2, y2, x3, y3);
        path.closePath();
        path.moveTo(x4, y4);
        path.curveTo(x1, y1, x3, y3, x2, y2);
        path.curveTo(x1, y1, x3, y3, x4, y4);
//        path = (GeneralPath) tr.createTransformedShape(path);
        path = (GeneralPath) path.createTransformedShape(tr);
        g2.translate(-400, 220);
        g2.draw(path);
        path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        g2.translate(200, 0);
        g2.fill(path);
        path.setWindingRule(GeneralPath.WIND_NON_ZERO);
        g2.translate(200, 0);
        g2.fill(path);
    }
}
