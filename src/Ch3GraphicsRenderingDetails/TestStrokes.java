package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestStrokes extends JPanel
{

    public TestStrokes()
    {
        this.setPreferredSize(new Dimension(700, 400));
        this.setBackground(Color.white);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new TestStrokes(), "Different Strokes").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        path.moveTo(0, 120);
        path.lineTo(80, 0);
        path.lineTo(160, 120);
        Stroke stroke = new BasicStroke(20, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(stroke);
        g2.translate(50, 50);
        g2.draw(path);
        g2.drawString("JOIN_BEVEL", 100, 0);
        g2.drawString("CAP_BUTT", 40, 120);
        stroke = new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setStroke(stroke);
        g2.translate(200, 0);
        g2.draw(path);
        g2.drawString("JOIN_MITER", 100, 0);
        g2.drawString("CAP_ROUND", 40, 120);
        stroke = new BasicStroke(20, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        g2.translate(200, 0);
        g2.draw(path);
        g2.drawString("JOIN_ROUND", 100, 0);
        g2.drawString("CAP_SQUARE", 40, 120);
        float[] dashArray =
        {
            60, 20, 20, 40
        };
        float dashPhase = 0;
        stroke = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                dashArray, dashPhase);
        g2.setStroke(stroke);
        g2.translate(-400, 200);
        g2.drawLine(100, 50, 550, 50);
        g2.drawString("dash=60 20 20 40", 250, 10);
        g2.drawString("phase=0", 0, 50);
        dashPhase = 20;
        stroke = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                dashArray, dashPhase);
        g2.setStroke(stroke);
        g2.translate(0, 50);
        g2.drawLine(100, 50, 550, 50);
        g2.drawString("phase=20", 0, 50);
    }
}
