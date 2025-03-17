package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestClip extends JPanel
{

    public TestClip()
    {
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.white);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new TestClip(), "Clip Path").setVisible(true));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        path.moveTo(100, 200);
        path.quadTo(250, 50, 400, 200);
        path.lineTo(400, 400);
        path.quadTo(250, 250, 100, 400);
        path.closePath();
        g2.clip(path);
        g2.setColor(new Color(200, 200, 200));
        g2.fill(path);
        g2.setColor(Color.black);
        g2.setFont(new Font("Serif", Font.BOLD, 60));
        g2.drawString("Clip Path Demo", 80, 200);
        g2.drawOval(50, 250, 400, 100);
    }
}
