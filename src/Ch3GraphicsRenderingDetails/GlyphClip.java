package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GlyphClip extends JPanel
{

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new GlyphClip(), "Glyph and Clip").setVisible(true));
    }

    public GlyphClip()
    {
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Serif", Font.BOLD, 144);
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, "Java");
        Shape glyph = gv.getOutline(100, 200);
        g2.setClip(glyph);
        g2.setColor(Color.red);
        for (int i = 0; i < 2000; i++)
        {
            Shape shape = new Ellipse2D.Double(Math.random() * 500, Math.random() * 400, 30, 20);
            g2.draw(shape);
        }
    }
}
