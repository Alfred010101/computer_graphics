package zPaint;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author Alfred
 */
public class PaintWork extends JPanel
{

    protected String PATH_RESOURCES = "src/zPaint/resources/";
    private final int MIN_LEFT_PANEL = 0;
    private final int MAX_LEFT_PANEL = 60;
    private JScrollPane leftPanel;
    private final JavaDraw2DPanel centerPanel;

    public int pointIndex = 0;
    public ArrayList points = new ArrayList();
    public Color color = null;
    public Point p = null;
    public Shape partialShape = null;
    public int shapeType = GV.getShapeType();
    public Figura edicion = null;
    public double lx = 0, ly = 0;
    public ArrayList<Figura> shapes = new ArrayList<>();
    public boolean r = false;
    public boolean paint = false;
    public Color color2 = null;
    public Paint colorD = null;

    public PaintWork()
    {
        setLayout(new BorderLayout());

        initScroll();

        centerPanel = new JavaDraw2DPanel();
        FillStoke rightPanel = new FillStoke();

        leftPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JSplitPane splitPaneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
        splitPaneLeft.setContinuousLayout(true);
        splitPaneLeft.setOneTouchExpandable(true);
        splitPaneLeft.setDividerLocation(60);

        splitPaneLeft.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, (PropertyChangeEvent evt) ->
        {
            int currentLocation = splitPaneLeft.getDividerLocation();
            if (currentLocation < MIN_LEFT_PANEL)
            {
                splitPaneLeft.setDividerLocation(MIN_LEFT_PANEL);
            } else if (currentLocation > MAX_LEFT_PANEL)
            {
                splitPaneLeft.setDividerLocation(MAX_LEFT_PANEL);
            }
        });

        JSplitPane splitPaneRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneLeft, rightPanel);
        splitPaneRight.setContinuousLayout(true);
        splitPaneRight.setOneTouchExpandable(true);
        splitPaneRight.setDividerLocation(splitPaneRight.getWidth() - 300);

        splitPaneRight.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, (PropertyChangeEvent evt) ->
        {
            int currentLocation = splitPaneRight.getDividerLocation();
            int minRightLocation = splitPaneRight.getWidth() - 300;
            int maxRightLocation = splitPaneRight.getWidth();

            if (currentLocation < minRightLocation)
            {
                splitPaneRight.setDividerLocation(minRightLocation);
            } else if (currentLocation > maxRightLocation)
            {
                splitPaneRight.setDividerLocation(maxRightLocation);
            }
        });

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                int nuevaPosicion = splitPaneRight.getWidth() - 300;
                splitPaneRight.setDividerLocation(nuevaPosicion);
            }
        });

        add(splitPaneRight, BorderLayout.CENTER);
    }

    private void initScroll()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        initShape("Selection.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.LINE2D;
        });

        initShape("Line.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.LINE2D;
        });

        initShape("Rectangle.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.RECTANGLE;
        });

        initShape("RoundRectangle.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.ROUNDRECTANGLE2D;
        });

        initShape("Ellipse.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.ELLIPSE2D;
        });

        initShape("Arc.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.ARC2D;
        });

        initShape("Polygon.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.POLYGON;
        });

        initShape("QuadCurve.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.QUADCURVE2D;
        });

        initShape("CubicCurve.png", panel, (e) ->
        {
            centerPanel.shapeType = JavaDraw2DPanel.CUBICCURVE2D;
        });

        leftPanel = new JScrollPane(panel);
        leftPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftPanel.getVerticalScrollBar().setUnitIncrement(16);
    }

    private void initShape(String source, JPanel panel, ActionListener e)
    {
        JButton boton = new JButton();
        boton.setIcon(new ImageIcon(PATH_RESOURCES + source));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.addActionListener(e);
        panel.add(boton);
    }

    private class MouseMotionEvenetHandler implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (GV.SELECCION != GV.getShapeType())
            {
                FigurasAdicionalesw a = new FigurasAdicionalesw();
                Graphics2D g = (Graphics2D) getGraphics();
                g.setXORMode(Color.white);
                Point p1 = (Point) points.get(pointIndex - 1);
                System.out.println(e.getX() + ",123  " + e.getY());
                switch (GV.getShapeType())
                {
                    case GV.RECTANGLE:
                        g.setColor(color);
                        if (p != null)
                        {
                            int[] x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                            g.drawRect(x[0], x[1], x[2], x[3]);
                        }
                        p = e.getPoint();
                        int[] x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                        g.drawRect(x[0], x[1], x[2], x[3]);
                        break;
                    case GV.ROUNDRECTANGLE2D:
                        if (p != null)
                        {
                            x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                            g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                        }
                        p = e.getPoint();
                        x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                        g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                        break;
                    case GV.ELLIPSE2D:
                        if (p != null)
                        {
                            x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                            g.drawOval(x[0], x[1], x[2], x[3]);
                        }
                        p = e.getPoint();
                        x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                        g.drawOval(x[0], x[1], x[2], x[3]);
                        break;
                    case GV.ARC2D:
                        if (p != null)
                        {
                            x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                            g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                        }
                        p = e.getPoint();
                        x = a.dimension(p1.x, p1.y + 85, p.x, p.y + 85);
                        g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                        break;
                    case GV.LINE2D:
                    case GV.POLYGON:
                        if (p != null)
                        {
                            g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.QUADCURVE2D:
                        if (pointIndex == 1)
                        {
                            if (p != null)
                            {
                                g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                            }
                            p = e.getPoint();
                            g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                        } else
                        {
                            Point p2 = (Point) points.get(pointIndex - 2);
                            if (p != null)
                            {
                                g.draw(partialShape);
                            }
                            p = e.getPoint();
                            partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, p.x, p.y + 85);
                            g.draw(partialShape);
                        }
                        break;
                    case GV.CUBICCURVE2D:
                        switch (pointIndex)
                        {
                            case 1 ->
                            {
                                if (p != null)
                                {
                                    g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                                }
                                p = e.getPoint();
                                g.drawLine(p1.x, p1.y + 85, p.x, p.y + 85);
                            }
                            case 2 ->
                            {
                                Point p2 = (Point) points.get(pointIndex - 2);
                                if (p != null)
                                {
                                    g.draw(partialShape);
                                }
                                p = e.getPoint();
                                partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, p.x, p.y + 85);
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
                                p = e.getPoint();
                                partialShape = new CubicCurve2D.Float(p3.x, p3.y + 85, p2.x, p2.y + 85, p1.x, p1.y + 85, p.x, p.y + 85);
                                g.draw(partialShape);
                            }
                        }
                        break;

                    case GV.ESTRELLA:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.estrella((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.estrella((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.ESPADA:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.espada((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.espada((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.LUNA:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.luna((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.luna((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.CUBO:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.cubo((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.cubo((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.RAYO:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.rayo((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.rayo((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.TREBOL:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.trebol((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.trebol((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.CORAZON:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.corazon((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.corazon((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.TORRE:
                        System.out.println(shapeType);
                        g.setColor(color);
                        if (p != null)
                        {
                            a.torreE((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.torreE((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.LETRAE:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.letraE((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.letraE((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.PIRAMIDE:
                        System.out.println(shapeType);
                        if (p != null)
                        {
                            a.piramide((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        }
                        p = e.getPoint();
                        a.piramide((Graphics2D) g, p1.x, p1.y + 85, p.x, p.y + 85);
                        break;
                    case GV.SELECCION:
                        break;
                }
            } else
            {
                Graphics2D g = (Graphics2D) getGraphics();
                g.setXORMode(Color.white);
                if (edicion != null)
                {
                    AffineTransform tr = new AffineTransform();

                    System.out.println(e.getX() + "," + e.getY());
                    int x = e.getX();
                    int y1 = e.getY();
                    int y = e.getY();

                    tr.translate(x - lx, y - ly);
                    g.draw(tr.createTransformedShape(edicion));

                    tr.translate(x - lx, y - ly);
                    int index = shapes.indexOf(edicion);
                    edicion.setShape(tr.createTransformedShape(edicion.getShape()));
                    if (index != -1)
                    {
                        shapes.set(index, edicion);
                    }
                    lx = x;
                    ly = y1;

                    repaint();
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
        }
    }

    private class MouseEventHandler implements MouseListener
    {

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
            if (GV.getShapeType() > 19)
            {
                for (Figura s : shapes)
                {
                    Shape shape = s.getShape();
                    if (shape.contains(ev.getX(), ev.getY()))
                    {
                        edicion = s;
                        lx = ev.getX();
                        ly = ev.getY();
                        System.out.println(shape.getClass());
                        break;
                    }
                }
            } else
            {
                points.add(ev.getPoint());
                pointIndex++;
                p = null;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (GV.getShapeType() < 20)
            {
                FigurasAdicionalesw a = new FigurasAdicionalesw();
                Graphics g = getGraphics();
                Point p1 = (Point) (points.get(pointIndex - 1));
                p = e.getPoint();
                Shape s = null;
                int[] x = a.dimension(p1.x, p1.y, p.x, p.y);
                switch (GV.getShapeType())
                {
                    case GV.RECTANGLE:
                        s = new Rectangle(x[0], x[1], x[2], x[3]);
                        break;
                    case GV.ROUNDRECTANGLE2D:
                        s = new RoundRectangle2D.Float(x[0], x[1], x[2], x[3], 10, 10);
                        break;
                    case GV.ELLIPSE2D:
                        s = new Ellipse2D.Float(x[0], x[1], x[2], x[3]);
                        break;
                    case GV.ARC2D:
                        s = new Arc2D.Float(x[0], x[1], x[2], x[3], 30, 120, Arc2D.OPEN);
                        break;
                    case GV.LINE2D:
                        s = new Line2D.Float(p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.QUADCURVE2D:
                        if (pointIndex > 1)
                        {
                            Point p2 = (Point) points.get(0);
                            s = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        }
                        break;
                    case GV.CUBICCURVE2D:
                        if (pointIndex > 2)
                        {
                            Point p2 = (Point) points.get(pointIndex - 2);
                            Point p3 = (Point) points.get(pointIndex - 3);
                            s = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        }
                        break;
                    case GV.ESTRELLA:
                        s = a.estrella((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.ESPADA:
                        s = a.espada((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.LUNA:
                        s = a.luna((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.CUBO:
                        s = a.cubo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.PIRAMIDE:
                        s = a.piramide((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.LETRAE:
                        s = a.letraE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.TREBOL:
                        s = a.trebol((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.RAYO:
                        s = a.rayo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.TORRE:
                        s = a.torreE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.CORAZON:
                        s = a.corazon((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                        break;
                    case GV.POLYGON:
                        if (e.isShiftDown())
                        {
                            s = new Polygon();
                            for (int i = 0; i < pointIndex; i++)
                            {
                                ((Polygon) s).addPoint(((Point) points.get(i)).x, ((Point) points.get(i)).y);
                            }
                            ((Polygon) s).addPoint(p.x, p.y);
                        }
                }
                if (s != null)
                {
                    Stroke stroke;
//                    stroke = new BasicStroke(HPanel.grosor(), HPanel.termino(), HPanel.union(), HPanel.miter(), HPanel.lineaP(), 0);
                    stroke = new BasicStroke(14.3f, 4, 2, 3.2f, new float[]
                    {
                        25, 10
                    }, 0);

                    if (paint)
                    {
                        colorD = new GradientPaint(x[0], x[1], color, x[2], x[3], color2);
                        if (r)
                        {
                            shapes.add(new Figura(s, colorD, stroke, color)); // Con relleno
                        } else
                        {
                            shapes.add(new Figura(s, colorD, stroke)); // Sin relleno
                        }
                    } else
                    {
                        if (r)
                        {
                            shapes.add(new Figura(color, s, stroke)); // Color sólido con relleno
                        } else
                        {
                            shapes.add(new Figura(s, color, stroke)); // Contorno sólido
                        }
                    }
                    points.clear();
                    pointIndex = 0;
                    p = null;
                    repaint();
                    System.out.println(e.getX() + "," + e.getY());
                }
            } else
            {
                if (edicion != null)
                {
                    edicion = null;
                }
            }
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

/*class WorkPanel extends JPanel
{

    public WorkPanel(ActionEventHandler mouseEventHandler, MouseEventHandler mouseMotionEventHandler)
    {
        super();
        setBackground(GV.PALE_BLUE_COLOR);
        setPreferredSize(new Dimension(640, 480));
        addMouseListener(mouseEventHandler);
        addMouseMotionListener(mouseMotionEventHandler);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Figura shape : shapes)
        {
            g2.setStroke(shape.getStroke());
            if (shape.getFillColor() != null && shape.getPaint() != null)
            {
                g2.setPaint(shape.getPaint());
                g2.fill(shape.getShape());
            } else
            {
                if (shape.getPaint() != null)
                {
                    g2.setPaint(shape.getPaint());
                    g2.draw(shape.getShape());
                } else
                {
                    if (shape.getFillColor() != null)
                    {
                        g2.setColor(shape.getFillColor());
                        g2.fill(shape.getShape());
                    } else
                    {

                        g2.setColor(shape.getColor());
                        g2.draw(shape.getShape());
                    }
                }
            }
        }
    }
}*/
