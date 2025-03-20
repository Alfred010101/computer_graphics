package zPaint.depen;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import static zPaint.depen.GV.*;

/**
 *
 * @author Alfred
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class MouseEventHandler implements MouseListener
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
            FigurasAdicionales a = new FigurasAdicionales();
            Graphics g = panel.getGraphics();
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
//                stroke = new BasicStroke(HPanel.grosor(), HPanel.termino(), HPanel.union(), HPanel.miter(), HPanel.lineaP(), 0);
                stroke = new BasicStroke();
                
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
                panel.repaint();
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

//class MouseEventHandler implements MouseListener
//{
//
//    private final JPanel panel;
//
//    public MouseEventHandler(JPanel panel)
//    {
//        this.panel = panel;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent ev)
//    {
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent ev)
//    {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent ev)
//    {
//    }
//
//    @Override
//    public void mousePressed(MouseEvent ev)
//    {
//        if (GV.getShapeType() > 19)
//        {
//            for (Figura s : VarPath.shapes)
//            {
//                Shape shape = s.getShape();
//                if (shape.contains(ev.getX(), ev.getY()))
//                {
//                    VarPath.edicion = s;
//                    VarPath.lx = ev.getX();
//                    VarPath.ly = ev.getY();
//                    System.out.println(shape.getClass());
//                    break;
//                }
//            }
//        } else
//        {
//            VarPath.points.add(ev.getPoint());
//            VarPath.pointIndex++;
//            VarPath.p = null;
//        }
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e)
//    {
//        if (GV.getShapeType() < 20)
//        {
//            FigurasAdicionales a = new FigurasAdicionales();
//            Graphics g = panel.getGraphics();
//            Point p1 = (Point) (VarPath.points.get(VarPath.pointIndex - 1));
//            VarPath.p = e.getPoint();
//            Shape s = null;
//            int[] x = a.dimension(p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//            switch (GV.getShapeType())
//            {
//                case GV.RECTANGLE ->
//                    s = new Rectangle(x[0], x[1], x[2], x[3]);
//                case GV.ROUNDRECTANGLE2D ->
//                    s = new RoundRectangle2D.Float(x[0], x[1], x[2], x[3], 10, 10);
//                case GV.ELLIPSE2D ->
//                    s = new Ellipse2D.Float(x[0], x[1], x[2], x[3]);
//                case GV.ARC2D ->
//                    s = new Arc2D.Float(x[0], x[1], x[2], x[3], 30, 120, Arc2D.OPEN);
//                case GV.LINE2D ->
//                    s = new Line2D.Float(p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.QUADCURVE2D ->
//                {
//                    if (VarPath.pointIndex > 1)
//                    {
//                        Point p2 = (Point) VarPath.points.get(0);
//                        s = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                    }
//                }
//                case GV.CUBICCURVE2D ->
//                {
//                    if (VarPath.pointIndex > 2)
//                    {
//                        Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
//                        Point p3 = (Point) VarPath.points.get(VarPath.pointIndex - 3);
//                        s = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                    }
//                }
//                case GV.ESTRELLA ->
//                    s = a.estrella((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.ESPADA ->
//                    s = a.espada((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.LUNA ->
//                    s = a.luna((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.CUBO ->
//                    s = a.cubo((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.PIRAMIDE ->
//                    s = a.piramide((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.LETRAE ->
//                    s = a.letraE((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.TREBOL ->
//                    s = a.trebol((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.RAYO ->
//                    s = a.rayo((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.TORRE ->
//                    s = a.torreE((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.CORAZON ->
//                    s = a.corazon((Graphics2D) g, p1.x, p1.y, VarPath.p.x, VarPath.p.y);
//                case GV.POLYGON ->
//                {
//                    if (e.isShiftDown())
//                    {
//                        s = new Polygon();
//                        for (int i = 0; i < VarPath.pointIndex; i++)
//                        {
//                            ((Polygon) s).addPoint(((Point) VarPath.points.get(i)).x, ((Point) VarPath.points.get(i)).y);
//                        }
//                        ((Polygon) s).addPoint(VarPath.p.x, VarPath.p.y);
//                    }
//                }
//            }
//            if (s != null)
//            {
//                Stroke stroke;
////                stroke = new BasicStroke(HPanel.grosor(), HPanel.termino(), HPanel.union(), HPanel.miter(), HPanel.lineaP(), 0);
//                stroke = new BasicStroke();
//
//                if (VarPath.paint)
//                {
//                    VarPath.colorD = new GradientPaint(x[0], x[1], VarPath.color, x[2], x[3], VarPath.color2);
//                    if (VarPath.r)
//                    {
//                        VarPath.shapes.add(new Figura(s, VarPath.colorD, stroke, VarPath.color)); // Con relleno
//                    } else
//                    {
//                        VarPath.shapes.add(new Figura(s, VarPath.colorD, stroke)); // Sin relleno
//                    }
//                } else
//                {
//                    if (VarPath.r)
//                    {
//                        VarPath.shapes.add(new Figura(VarPath.color, s, stroke)); // Color sólido con relleno
//                    } else
//                    {
//                        VarPath.shapes.add(new Figura(s, VarPath.color, stroke)); // Contorno sólido
//                    }
//                }
//                VarPath.points.clear();
//                VarPath.pointIndex = 0;
//                VarPath.p = null;
//                panel.repaint();
//                System.out.println(e.getX() + "," + e.getY());
//            }
//        } else
//        {
//            if (VarPath.edicion != null)
//            {
//                VarPath.edicion = null;
//            }
//        }
//    }
//}
//