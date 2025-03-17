package Ch3GraphicsRenderingDetails;

import Ch1.SnippetFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Transformations extends JPanel implements ActionListener
{

    private TransformPanel panel = null;
    private JMenuBar mb = null;

    public Transformations()
    {
        setLayout(new BorderLayout());
        this.init();
        panel = new TransformPanel();

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(mb, BorderLayout.NORTH);

        add(menuPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String s[])
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Transformations(), "Affine Transforms").setVisible(true));
    }

    private void init()
    {
        mb = new JMenuBar();
        JMenu menu = new JMenu("Transforms");
        mb.add(menu);
        JMenuItem mi = new JMenuItem("Translation");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Rotation");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Scaling");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Shearing");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Reflection");
        mi.addActionListener(this);
        menu.add(mi);
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        String command = ev.getActionCommand();
        if (null != command)
        switch (command)
        {
            case "Translation":
                panel.transformType = TransformPanel.TRANSLATION;
                break;
            case "Rotation":
                panel.transformType = TransformPanel.ROTATION;
                break;
            case "Scaling":
                panel.transformType = TransformPanel.SCALING;
                break;
            case "Shearing":
                panel.transformType = TransformPanel.SHEARING;
                break;
            case "Reflection":
                panel.transformType = TransformPanel.REFLECTION;
                break;
            default:
                break;
        }
    }
}

class TransformPanel extends JPanel implements MouseListener, MouseMotionListener
{

    static final int NONE = 0;
    static final int TRANSLATION = 1;
    static final int ROTATION = 2;
    static final int SCALING = 3;
    static final int SHEARING = 4;
    static final int REFLECTION = 5;

    int transformType = NONE;
    Shape drawShape = null;
    Shape tempShape = null;
    Point p = null;
    int x0 = 400;
    int y0 = 300;

    public TransformPanel()
    {
        super();
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.white);
        drawShape = new Rectangle(-50, -50, 100, 100);
        addMouseListener(TransformPanel.this);
        addMouseMotionListener(TransformPanel.this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(x0, y0);
        g2.drawLine(-200, 0, 200, 0);
        g2.drawLine(0, -200, 0, 200);
        g2.draw(drawShape);
    }

    @Override
    public void mouseClicked(MouseEvent ev)
    {
    }

    @Override
    public void mouseEntered(MouseEvent ev)
    {
    }

    @Override
    public void mouseExited(MouseEvent ev)
    {
    }

    @Override
    public void mousePressed(MouseEvent ev)
    {
        p = ev.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent ev)
    {
        Graphics g = getGraphics();
        Point p1 = ev.getPoint();
        AffineTransform tr = new AffineTransform();
        switch (transformType)
        {
            case TRANSLATION -> tr.setToTranslation(p1.x - p.x, p1.y - p.y);
            case ROTATION ->
            {
                double a = Math.atan2(p1.y - y0, p1.x - x0) - Math.atan2(p.y - y0, p.x - x0);
                tr.setToRotation(a);
            }
            case SCALING ->
            {
                double sx = Math.abs((double) (p1.x - x0) / (p.x - x0));
                double sy = Math.abs((double) (p1.y - y0) / (p.y - y0));
                tr.setToScale(sx, sy);
            }
            case SHEARING ->
            {
                double shx = ((double) (p1.x - x0) / (p.x - x0)) - 1;
                double shy = ((double) (p1.y - y0) / (p.y - y0)) - 1;
                tr.setToShear(shx, shy);
            }
            case REFLECTION -> tr.setTransform(-1, 0, 0, 1, 0, 0);
        }
        drawShape = tr.createTransformedShape(drawShape);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent ev)
    {
    }

    @Override
    public void mouseDragged(MouseEvent ev)
    {
        Point p1 = ev.getPoint();
        AffineTransform tr = new AffineTransform();
        switch (transformType)
        {
            case TRANSLATION -> tr.setToTranslation(p1.x - p.x, p1.y - p.y);
            case ROTATION ->
            {
                double a = Math.atan2(p1.y - y0, p1.x - x0) - Math.atan2(p.y - y0, p.x - x0);
                tr.setToRotation(a);
            }
            case SCALING ->
            {
                double sx = Math.abs((double) (p1.x - x0) / (p.x - x0));
                double sy = Math.abs((double) (p1.y - y0) / (p.y - y0));
                tr.setToScale(sx, sy);
            }
            case SHEARING ->
            {
                double shx = ((double) (p1.x - x0) / (p.x - x0)) - 1;
                double shy = ((double) (p1.y - y0) / (p.y - y0)) - 1;
                tr.setToShear(shx, shy);
            }
            case REFLECTION -> tr.setTransform(-1, 0, 0, 1, 0, 0);
        }
        Graphics2D g = (Graphics2D) getGraphics();
        g.setXORMode(Color.white);
        g.translate(x0, y0);
        if (tempShape != null)
        {
            g.draw(tempShape);
        }
        tempShape = tr.createTransformedShape(drawShape);
        g.draw(tempShape);
    }
}
