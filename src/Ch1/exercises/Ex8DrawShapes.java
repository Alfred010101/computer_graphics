package Ch1.exercises;

import Ch1.SnippetFrame;
import Ch2GraphicsBasics2d.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alfred
 */
/*public class Ex8DrawShapes extends JFrame implements ActionListener
{

    private JavaDraw2DPanel panel = null;

    public Ex8DrawShapes()
    {
        this.setTitle("Drawing Geometric Shapes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        JFrame frame = new Ex8DrawShapes();
        ((Ex8DrawShapes) frame).init();
//        frame.getContentPane().add(frame);
//        frame.pack();
        frame.setVisible(true);
    }

    public void init()
    {
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu menu = new JMenu("Objects");
        mb.add(menu);
        JMenuItem mi = new JMenuItem("Rectangle");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("RoundRectangle");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Ellipse");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Arc");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Line");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("QuadCurve");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("CubicCurve");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Polygon");
        mi.addActionListener(this);
        menu.add(mi);
        panel = new JavaDraw2DPanel();
        getContentPane().add(panel);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        String command = ev.getActionCommand();
        if (null != command)
        {
            switch (command)
            {
                case "Rectangle":
                    panel.shapeType = JavaDraw2DPanel.RECTANGLE;
                    break;
                case "RoundRectangle":
                    panel.shapeType = JavaDraw2DPanel.ROUNDRECTANGLE2D;
                    break;
                case "Ellipse":
                    panel.shapeType = JavaDraw2DPanel.ELLIPSE2D;
                    break;
                case "Arc":
                    panel.shapeType = JavaDraw2DPanel.ARC2D;
                    break;
                case "Line":
                    panel.shapeType = JavaDraw2DPanel.LINE2D;
                    break;
                case "QuadCurve":
                    panel.shapeType = JavaDraw2DPanel.QUADCURVE2D;
                    break;
                case "CubicCurve":
                    panel.shapeType = JavaDraw2DPanel.CUBICCURVE2D;
                    break;
                case "Polygon":
                    panel.shapeType = JavaDraw2DPanel.POLYGON;
                    break;
                default:
                    break;
            }
        }
    }
}
*/

public class Ex8DrawShapes extends JPanel implements ActionListener {
    private JavaDraw2DPanel panel = null;
    private JMenuBar mb = null;

    public Ex8DrawShapes() {
        setLayout(new BorderLayout());
        this.init();
        panel = new JavaDraw2DPanel();
        
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(mb, BorderLayout.NORTH);
        
        add(menuPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex8DrawShapes(), "Drawing Geometric Shapes").setVisible(true));
    
    }
    
    private void init()
    {
        mb = new JMenuBar();
//        setJMenuBar(mb);
        JMenu menu = new JMenu("Objects");
        mb.add(menu);
        JMenuItem mi = new JMenuItem("Rectangle");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("RoundRectangle");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Ellipse");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Arc");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Line");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("QuadCurve");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("CubicCurve");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Polygon");
        mi.addActionListener(this);
        menu.add(mi);
//        panel = new JavaDraw2DPanel();
//        getContentPane().add(panel);
//        pack();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String command = ev.getActionCommand();
        if (command != null) {
            switch (command) {
                case "Rectangle":
                    panel.shapeType = JavaDraw2DPanel.RECTANGLE;
                    break;
                case "RoundRectangle":
                    panel.shapeType = JavaDraw2DPanel.ROUNDRECTANGLE2D;
                    break;
                case "Ellipse":
                    panel.shapeType = JavaDraw2DPanel.ELLIPSE2D;
                    break;
                case "Arc":
                    panel.shapeType = JavaDraw2DPanel.ARC2D;
                    break;
                case "Line":
                    panel.shapeType = JavaDraw2DPanel.LINE2D;
                    break;
                case "QuadCurve":
                    panel.shapeType = JavaDraw2DPanel.QUADCURVE2D;
                    break;
                case "CubicCurve":
                    panel.shapeType = JavaDraw2DPanel.CUBICCURVE2D;
                    break;
                case "Polygon":
                    panel.shapeType = JavaDraw2DPanel.POLYGON;
                    break;
                default:
                    break;
            }
            panel.repaint();
        }
    }
}

class JavaDraw2DPanel extends JPanel implements MouseListener, MouseMotionListener
{

    private final List shapes = new ArrayList();
    public static final int RECTANGLE = 0;
    public static final int ROUNDRECTANGLE2D = 1;
    public static final int ELLIPSE2D = 2;
    public static final int ARC2D = 3;
    public static final int LINE2D = 4;
    public static final int QUADCURVE2D = 5;
    public static final int CUBICCURVE2D = 6;
    public static final int POLYGON = 7;
//    public static final int GENERAL = 8;
//    public static final int AREA = 9;
    protected int shapeType = RECTANGLE;
    // vector of input points
    private final List points = new ArrayList();
    private int pointIndex = 0;
    private Shape partialShape = null;
    private Point p = null;

    public JavaDraw2DPanel()
    {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(640, 480));
        addMouseListener(JavaDraw2DPanel.this);
        addMouseMotionListener(JavaDraw2DPanel.this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < shapes.size(); i++)
        {
            Shape s = (Shape) shapes.get(i);
            g2.draw(s);
        }
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
        points.add(ev.getPoint());
        pointIndex++;
        p = null;
    }

    @Override
    public void mouseReleased(MouseEvent ev)
    {
        Graphics g = getGraphics();
        Point p1 = (Point) (points.get(pointIndex - 1));
        p = ev.getPoint();
        Shape s = null;
        switch (shapeType)
        {
            case RECTANGLE ->
                s = new Rectangle(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
            case ROUNDRECTANGLE2D ->
                s = new RoundRectangle2D.Float(p1.x, p1.y,
                        p.x - p1.x, p.y - p1.y, 10, 10);
            case ELLIPSE2D ->
                s = new Ellipse2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
            case ARC2D ->
                s = new Arc2D.Float(p1.x, p1.y, p.x - p1.x,
                        p.y - p1.y, 30, 120, Arc2D.OPEN);
            case LINE2D ->
                s = new Line2D.Float(p1.x, p1.y, p.x, p.y);
            case QUADCURVE2D ->
            {
                if (pointIndex > 1)
                {
                    Point p2 = (Point) points.get(0);
                    s = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                }
            }
            case CUBICCURVE2D ->
            {
                if (pointIndex > 2)
                {
                    Point p2 = (Point) points.get(pointIndex - 2);
                    Point p3 = (Point) points.get(pointIndex - 3);
                    s = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y,
                            p1.x, p1.y, p.x, p.y);
                }
            }
            case POLYGON ->
            {
                if (ev.isShiftDown())
                {
                    s = new Polygon();
                    for (int i = 0; i < pointIndex; i++)
                    {
                        ((Polygon) s).addPoint(((Point) points.get(i)).x,
                                ((Point) points.get(i)).y);
                    }
                    ((Polygon) s).addPoint(p.x, p.y);
                }
            }
        }
        if (s != null)
        {
            shapes.add(s);
            points.clear();
            pointIndex = 0;

            p = null;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent ev)
    {
    }

    @Override
    public void mouseDragged(MouseEvent ev)
    {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setXORMode(Color.white);
        Point p1 = (Point) points.get(pointIndex - 1);
        switch (shapeType)
        {
            case RECTANGLE ->
            {
                if (p != null)
                {
                    g.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                }
                p = ev.getPoint();
                g.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
            }
            case ROUNDRECTANGLE2D ->
            {
                if (p != null)
                {
                    g.drawRoundRect(p1.x, p1.y,
                            p.x - p1.x, p.y - p1.y, 10, 10);
                }
                p = ev.getPoint();
                g.drawRoundRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
            }
            case ELLIPSE2D ->
            {
                if (p != null)
                {
                    g.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                }
                p = ev.getPoint();
                g.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
            }
            case ARC2D ->
            {
                if (p != null)
                {
                    g.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
                }
                p = ev.getPoint();
                g.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
            }
            case LINE2D, POLYGON ->
            {
                if (p != null)
                {
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                }
                p = ev.getPoint();
                g.drawLine(p1.x, p1.y, p.x, p.y);
            }
            case QUADCURVE2D ->
            {
                if (pointIndex == 1)
                {
                    if (p != null)
                    {
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    p = ev.getPoint();
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                } else
                {
                    Point p2 = (Point) points.get(pointIndex - 2);
                    if (p != null)
                    {
                        g.draw(partialShape);
                    }
                    p = ev.getPoint();
                    partialShape = new QuadCurve2D.Float(p2.x, p2.y,
                            p1.x, p1.y, p.x, p.y);
                    g.draw(partialShape);
                }
            }
            case CUBICCURVE2D ->
            {
                switch (pointIndex)
                {
                    case 1 ->
                    {
                        if (p != null)
                        {
                            g.drawLine(p1.x, p1.y, p.x, p.y);
                        }
                        p = ev.getPoint();
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    case 2 ->
                    {
                        Point p2 = (Point) points.get(pointIndex - 2);
                        if (p != null)
                        {
                            g.draw(partialShape);
                        }
                        p = ev.getPoint();
                        partialShape = new QuadCurve2D.Float(p2.x, p2.y,
                                p1.x, p1.y, p.x, p.y);
                        g.draw(partialShape);
                    }
                    default ->
                    {
                        Point p2 = (Point) points.get(pointIndex - 2);
                        Point p3 = (Point) points.get(pointIndex - 3);
                        if (p != null)
                        {
                            g.draw(partialShape);
                        }
                        p = ev.getPoint();
                        partialShape = new CubicCurve2D.Float(p3.x, p3.y,
                                p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        g.draw(partialShape);
                    }
                }
            }
        }
    }
}
