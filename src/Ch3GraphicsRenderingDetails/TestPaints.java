package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestPaints extends JPanel
{

    private BufferedImage image;

    public TestPaints()
    {
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.white);
        URL url = getClass().getClassLoader().getResource("images/earth.jpg");
        try
        {
            image = ImageIO.read(url);
            System.out.println(image.getType());
        } catch (IOException ex)
        {
        }
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new TestPaints(), "Gradient and Texture Paints").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(100, 50, Color.white, 150, 50, Color.gray, true);
        g2.setPaint(gp);
        g2.fillRect(100, 40, 300, 20);
        TexturePaint tp = new TexturePaint(
                image,
                new Rectangle2D.Double(
                        100,
                        100,
                        image.getWidth(),
                        image.getHeight()
                )
        );
        g2.setPaint(tp);
        Shape ellipse = new Ellipse2D.Double(100, 100, 300, 200);
        g2.fill(ellipse);
        GradientPaint paint = new GradientPaint(100, 300, Color.white, 400, 400, Color.black);
        g2.setPaint(paint);
        Font font = new Font("Serif", Font.BOLD, 144);
        g2.setFont(font);
        g2.drawString("Java", 100, 400);
    }
}
