package Ch1.exercises;

import Ch1.SnippetFrame;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class Ex7Demo2D extends JPanel
{

    public Ex7Demo2D()
    {
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.white);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex7Demo2D(), "Java 2D Demo").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw an ellipse
        Shape ellipse = new Ellipse2D.Double(150, 100, 200, 200);
        GradientPaint paint = new GradientPaint(100, 100, Color.white, 400, 400, Color.gray);
        g2.setPaint(paint);
        g2.fill(ellipse);
        // set transparency
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        g2.setComposite(ac);
        g2.setColor(Color.blue);
        // draw transparent text
        Font font = new Font("Serif", Font.BOLD, 120);
        g2.setFont(font);
        g2.drawString("Java", 120, 200);
        // get outline of text glyph
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, "2D");
        Shape glyph = gv.getOutline(150, 300);
        // draw rotated glyph
        g2.rotate(Math.PI / 6, 200, 300);
        g2.fill(glyph);
    }
}
