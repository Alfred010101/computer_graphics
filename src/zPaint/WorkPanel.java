package zPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
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
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class WorkPanel extends JPanel
{

    private final MouseEventHandler mouseEventHandler = new MouseEventHandler(WorkPanel.this);
    private final MouseMotionEvenetHandler mouseMotionEventHandler = new MouseMotionEvenetHandler(WorkPanel.this);

    public WorkPanel()
    {
        super();
        setBackground(GV.PALE_BLUE_COLOR);
        setPreferredSize(new Dimension(640, 480));
        addMouseListener(mouseEventHandler);
        addMouseMotionListener(mouseMotionEventHandler);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Figura shape : VarPath.shapes)
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
}

class MouseEventHandler implements MouseListener
{

    private final JPanel panel;

    public MouseEventHandler(JPanel panel)
    {
        this.panel = panel;
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
        if (GV.getShapeType() > 19)
        {
            for (Figura s : VarPath.shapes)
            {
                Shape shape = s.getShape();
                if (shape.contains(ev.getX(), ev.getY()))
                {
                    VarPath.edicion = s;
                    VarPath.lx = ev.getX();
                    VarPath.ly = ev.getY();
                    System.out.println(shape.getClass());
                    break;
                }
            }
        } else
        {
            VarPath.points.add(ev.getPoint());
            VarPath.pointIndex++;
            VarPath.p = null;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (GV.getShapeType() < 20)
        {
            FigurasAdicionales a = new FigurasAdicionales();
            Graphics g = panel.getGraphics();
            Point p1 = (Point) (VarPath.points.get(VarPath.pointIndex - 1));
            VarPath.p = e.getPoint();
            Shape s = null;
            int[] x = a.dimension(p1.x, p1.y, VarPath.p.x, VarPath.p.y);
            switch (GV.getShapeType())
            {
                case GV.RECTANGLE ->
                    s = new Rectangle(x[0], x[1], x[2], x[3]);
                case GV.ROUNDRECTANGLE2D ->
                    s = new RoundRectangle2D.Float(x[0], x[1], x[2], x[3], 10, 10);
                case GV.ELLIPSE2D ->
                    s = new Ellipse2D.Float(x[0], x[1], x[2], x[3]);
                case GV.ARC2D ->
                    s = new Arc2D.Float(x[0], x[1], x[2], x[3], 30, 120, Arc2D.OPEN);
                case GV.LINE2D ->
                    s = new Line2D.Float(p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.QUADCURVE2D ->
                {
                    if (VarPath.pointIndex > 1)
                    {
                        Point p2 = (Point) VarPath.points.get(0);
                        s = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                    }
                }
                case GV.CUBICCURVE2D ->
                {
                    if (VarPath.pointIndex > 2)
                    {
                        Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
                        Point p3 = (Point) VarPath.points.get(VarPath.pointIndex - 3);
                        s = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                    }
                }
                case GV.ESTRELLA ->
                    s = a.estrella((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.ESPADA ->
                    s = a.espada((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.LUNA ->
                    s = a.luna((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.CUBO ->
                    s = a.cubo((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.PIRAMIDE ->
                    s = a.piramide((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.LETRAE ->
                    s = a.letraE((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.TREBOL ->
                    s = a.trebol((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.RAYO ->
                    s = a.rayo((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.TORRE ->
                    s = a.torreE((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.CORAZON ->
                    s = a.corazon((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
                case GV.POLYGON ->
                {
                    if (e.isShiftDown())
                    {
                        s = new Polygon();
                        for (int i = 0; i < VarPath.pointIndex; i++)
                        {
                            ((Polygon) s).addPoint(((Point) VarPath.points.get(i)).x, ((Point) VarPath.points.get(i)).y);
                        }
                        ((Polygon) s).addPoint(VarPath.p.x, VarPath.p.y);
                    }
                }
            }
            if (s != null)
            {
                Stroke stroke;
//                stroke = new BasicStroke(HPanel.grosor(), HPanel.termino(), HPanel.union(), HPanel.miter(), HPanel.lineaP(), 0);
                stroke = new BasicStroke();

                if (VarPath.paint)
                {
                    VarPath.colorD = new GradientPaint(x[0], x[1], VarPath.color, x[2], x[3], VarPath.color2);
                    if (VarPath.r)
                    {
                        VarPath.shapes.add(new Figura(s, VarPath.colorD, stroke, VarPath.color)); // Con relleno
                    } else
                    {
                        VarPath.shapes.add(new Figura(s, VarPath.colorD, stroke)); // Sin relleno
                    }
                } else
                {
                    if (VarPath.r)
                    {
                        VarPath.shapes.add(new Figura(VarPath.color, s, stroke)); // Color sólido con relleno
                    } else
                    {
                        VarPath.shapes.add(new Figura(s, VarPath.color, stroke)); // Contorno sólido
                    }
                }
                VarPath.points.clear();
                VarPath.pointIndex = 0;
                VarPath.p = null;
                panel.repaint();
                System.out.println(e.getX() + "," + e.getY());
            }
        } else
        {
            if (VarPath.edicion != null)
            {
                VarPath.edicion = null;
            }
        }
    }
}

class MouseMotionEvenetHandler implements MouseMotionListener
{

    private final JPanel panel;

    public MouseMotionEvenetHandler(JPanel panel)
    {
        this.panel = panel;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (GV.SELECCION != GV.getShapeType())
        {
            FigurasAdicionales a = new FigurasAdicionales();
            Graphics2D g = (Graphics2D) panel.getGraphics();
            g.setXORMode(Color.white);
            Point p1 = (Point) VarPath.points.get(VarPath.pointIndex - 1);
            System.out.println(e.getX() + ",123  " + e.getY());
            switch (GV.getShapeType())
            {
                case GV.RECTANGLE:
                    g.setColor(VarPath.color);
                    if (VarPath.p != null)
                    {
                        int[] x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        g.drawRect(x[0], x[1], x[2], x[3]);
                    }
                    VarPath.p = e.getPoint();
                    int[] x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    g.drawRect(x[0], x[1], x[2], x[3]);
                    break;
                case GV.ROUNDRECTANGLE2D:
                    if (VarPath.p != null)
                    {
                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                    }
                    VarPath.p = e.getPoint();
                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                    break;
                case GV.ELLIPSE2D:
                    if (VarPath.p != null)
                    {
                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        g.drawOval(x[0], x[1], x[2], x[3]);
                    }
                    VarPath.p = e.getPoint();
                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    g.drawOval(x[0], x[1], x[2], x[3]);
                    break;
                case GV.ARC2D:
                    if (VarPath.p != null)
                    {
                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                    }
                    VarPath.p = e.getPoint();
                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                    break;
                case GV.LINE2D:
                case GV.POLYGON:
                    if (VarPath.p != null)
                    {
                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.QUADCURVE2D:
                    if (VarPath.pointIndex == 1)
                    {
                        if (VarPath.p != null)
                        {
                            g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        }
                        VarPath.p = e.getPoint();
                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    } else
                    {
                        Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
                        if (VarPath.p != null)
                        {
                            g.draw(VarPath.partialShape);
                        }
                        VarPath.p = e.getPoint();
                        VarPath.partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        g.draw(VarPath.partialShape);
                    }
                    break;
                case GV.CUBICCURVE2D:
                switch (VarPath.pointIndex)
                {
                    case 1 ->
                    {
                        if (VarPath.p != null)
                        {
                            g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                        }
                        VarPath.p = e.getPoint();
                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    case 2 ->                         {
                            Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
                            if (VarPath.p != null)
                            {
                                g.draw(VarPath.partialShape);
                            }   VarPath.p = e.getPoint();
                            VarPath.partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                            g.draw(VarPath.partialShape);
                        }
                    default ->                         {
                            Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
                            Point p3 = (Point) VarPath.points.get(VarPath.pointIndex - 3);
                            if (VarPath.p != null)
                            {
                                g.draw(VarPath.partialShape);
                            }   VarPath.p = e.getPoint();
                            VarPath.partialShape = new CubicCurve2D.Float(p3.x, p3.y + 85, p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                            g.draw(VarPath.partialShape);
                        }
                }
                    break;


                case GV.ESTRELLA:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.estrella((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.estrella((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.ESPADA:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.espada((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.espada((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.LUNA:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.luna((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.luna((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.CUBO:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.cubo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.cubo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.RAYO:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.rayo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.rayo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.TREBOL:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.trebol((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.trebol((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.CORAZON:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.corazon((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.corazon((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.TORRE:
                    System.out.println(VarPath.shapeType);
                    g.setColor(VarPath.color);
                    if (VarPath.p != null)
                    {
                        a.torreE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.torreE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.LETRAE:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.letraE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.letraE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.PIRAMIDE:
                    System.out.println(VarPath.shapeType);
                    if (VarPath.p != null)
                    {
                        a.piramide((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    }
                    VarPath.p = e.getPoint();
                    a.piramide((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
                    break;
                case GV.SELECCION:
                    break;
            }
        } else
        {
            Graphics2D g = (Graphics2D) panel.getGraphics();
            g.setXORMode(Color.white);
            if (VarPath.edicion != null)
            {
                AffineTransform tr = new AffineTransform();

                System.out.println(e.getX() + "," + e.getY());
                int x = e.getX();
                int y1 = e.getY();
                int y = e.getY();

                tr.translate(x - VarPath.lx, y - VarPath.ly);
                g.draw(tr.createTransformedShape(VarPath.edicion));

                tr.translate(x - VarPath.lx, y - VarPath.ly);
                int index = VarPath.shapes.indexOf(VarPath.edicion);
                VarPath.edicion.setShape(tr.createTransformedShape(VarPath.edicion.getShape()));
                if (index != -1)
                {
                    VarPath.shapes.set(index, VarPath.edicion);
                }
                VarPath.lx = x;
                VarPath.ly = y1;

                panel.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
}
