package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Compositing extends JPanel implements MouseListener
{

    private BufferedImage image;
    private int[] rules =
    {
        AlphaComposite.CLEAR, AlphaComposite.SRC_OVER,
        AlphaComposite.DST_OVER, AlphaComposite.SRC_IN,
        AlphaComposite.DST_IN, AlphaComposite.SRC_OUT,
        AlphaComposite.DST_OUT, AlphaComposite.SRC,
        AlphaComposite.DST, AlphaComposite.SRC_ATOP,
        AlphaComposite.DST_ATOP, AlphaComposite.XOR
    };
    private int ruleIndex = 0;

    public Compositing()
    {
        this.setPreferredSize(new Dimension(500, 400));
        this.setBackground(Color.white);
        URL url = getClass().getClassLoader().getResource("images/earth.jpg");
        try
        {
            image = ImageIO.read(url);
        } catch (IOException ex)
        {
        }
        addMouseListener(Compositing.this);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Compositing(), "Compositing Rules").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 100, 100, this);
        AlphaComposite ac = AlphaComposite.getInstance(rules[ruleIndex], 0.4f);
        g2.setComposite(ac);
        Shape ellipse = new Ellipse2D.Double(50, 50, 120, 120);
        g2.setColor(Color.red);
        g2.fill(ellipse);
        g2.setColor(Color.orange);
        Font font = new Font("Serif", Font.BOLD, 144);
        g2.setFont(font);
        g2.drawString("Java", 90, 240);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        ruleIndex++;
        ruleIndex %= 12;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
