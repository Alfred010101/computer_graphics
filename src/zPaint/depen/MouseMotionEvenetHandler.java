/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zPaint.depen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JPanel;
import static zPaint.depen.GV.*;

/**
 *
 * @author Alfred
 */
public class MouseMotionEvenetHandler implements MouseMotionListener
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
            Point p1 = (Point) points.get(pointIndex - 1);
            System.out.println(e.getX() + ",123  " + e.getY());
            switch (GV.getShapeType())
            {
                case GV.RECTANGLE:
                    g.setColor(color);
                    if (p != null)
                    {
                        int[] x = a.dimension(p1.x, p1.y, p.x, p.y);
                        g.drawRect(x[0], x[1], x[2], x[3]);
                    }
                    p = e.getPoint();
                    int[] x = a.dimension(p1.x, p1.y, p.x, p.y);
                    g.drawRect(x[0], x[1], x[2], x[3]);
                    break;
                case GV.ROUNDRECTANGLE2D:
                    if (p != null)
                    {
                        x = a.dimension(p1.x, p1.y, p.x, p.y);
                        g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                    }
                    p = e.getPoint();
                    x = a.dimension(p1.x, p1.y, p.x, p.y);
                    g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
                    break;
                case GV.ELLIPSE2D:
                    if (p != null)
                    {
                        x = a.dimension(p1.x, p1.y, p.x, p.y);
                        g.drawOval(x[0], x[1], x[2], x[3]);
                    }
                    p = e.getPoint();
                    x = a.dimension(p1.x, p1.y, p.x, p.y);
                    g.drawOval(x[0], x[1], x[2], x[3]);
                    break;
                case GV.ARC2D:
                    if (p != null)
                    {
                        x = a.dimension(p1.x, p1.y, p.x, p.y);
                        g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                    }
                    p = e.getPoint();
                    x = a.dimension(p1.x, p1.y, p.x, p.y);
                    g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
                    break;
                case GV.LINE2D:
                case GV.POLYGON:
                    if (p != null)
                    {
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                    break;
                case GV.QUADCURVE2D:
                    if (pointIndex == 1)
                    {
                        if (p != null)
                        {
                            g.drawLine(p1.x, p1.y, p.x, p.y);
                        }
                        p = e.getPoint();
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    } else
                    {
                        Point p2 = (Point) points.get(pointIndex - 2);
                        if (p != null)
                        {
                            g.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        g.draw(partialShape);
                    }
                    break;
                case GV.CUBICCURVE2D:
                    if (pointIndex == 1)
                    {
                        if (p != null)
                        {
                            g.drawLine(p1.x, p1.y, p.x, p.y);
                        }
                        p = e.getPoint();
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    } else if (pointIndex == 2)
                    {
                        Point p2 = (Point) points.get(pointIndex - 2);
                        if (p != null)
                        {
                            g.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        g.draw(partialShape);
                    } else
                    {
                        Point p2 = (Point) points.get(pointIndex - 2);
                        Point p3 = (Point) points.get(pointIndex - 3);
                        if (p != null)
                        {
                            g.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new CubicCurve2D.Float(p3.x, p3.y + 85, p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                        g.draw(partialShape);
                    }
                    break;
                case GV.ESTRELLA:
//                    System.out.println(GV.getShapeType());
                    if (p != null)
                    {
                        a.estrella((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.estrella((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.ESPADA:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.espada((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.espada((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.LUNA:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.luna((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.luna((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.CUBO:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.cubo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.cubo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.RAYO:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.rayo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.rayo((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.TREBOL:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.trebol((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.trebol((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.CORAZON:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.corazon((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.corazon((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.TORRE:
//                    System.out.println(shapeType);
                    g.setColor(color);
                    if (p != null)
                    {
                        a.torreE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.torreE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.LETRAE:
//                    System.out.println(shapeType);
                    if (p != null)
                    {
                        a.letraE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    a.letraE((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.PIRAMIDE:
//                    System.out.println(shapeType);
//                    if (p != null)
//                    {
//                        System.out.println("Dentro");
//                        a.piramide((Graphics2D) g, p1.x, p1.y, p.x, p.y);
//                    }
                    System.out.println("Fuera");
                    p = e.getPoint();
                    a.piramide((Graphics2D) g, p1.x, p1.y, p.x, p.y);
                    break;
                case GV.SELECCION:
                    break;
            }
        } else
        {

            
            if (edicion != null)
            {
                System.out.println(edicion.getShape());
                Graphics2D g = (Graphics2D) panel.getGraphics();
                g.setXORMode(Color.white);

                System.out.println(e.getX() + "," + e.getY());
                int x = e.getX();
                int y = e.getY();
                
                AffineTransform tr = new AffineTransform();
                tr.translate(x - lx, y - ly);
                int index = shapes.indexOf(edicion);
                System.out.println(index);
                edicion.setShape(tr.createTransformedShape(edicion.getShape()));
                if (index != -1)
                {
                    shapes.set(index, edicion);
                }
                lx = x;
                ly = y;

                panel.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
}

//class MouseMotionEvenetHandler implements MouseMotionListener
//{
//
//    private final JPanel panel;
//
//    public MouseMotionEvenetHandler(JPanel panel)
//    {
//        this.panel = panel;
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e)
//    {
//        if (GV.SELECCION != GV.getShapeType())
//        {
//            FigurasAdicionales a = new FigurasAdicionales();
//            Graphics2D g = (Graphics2D) panel.getGraphics();
//            g.setXORMode(Color.white);
//            Point p1 = (Point) VarPath.points.get(VarPath.pointIndex - 1);
//            System.out.println(e.getX() + ",123  " + e.getY());
//            switch (GV.getShapeType())
//            {
//                case GV.RECTANGLE:
//                    g.setColor(VarPath.color);
//                    if (VarPath.p != null)
//                    {
//                        int[] x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        g.drawRect(x[0], x[1], x[2], x[3]);
//                    }
//                    VarPath.p = e.getPoint();
//                    int[] x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    g.drawRect(x[0], x[1], x[2], x[3]);
//                    break;
//                case GV.ROUNDRECTANGLE2D:
//                    if (VarPath.p != null)
//                    {
//                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
//                    }
//                    VarPath.p = e.getPoint();
//                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    g.drawRoundRect(x[0], x[1], x[2], x[3], 10, 10);
//                    break;
//                case GV.ELLIPSE2D:
//                    if (VarPath.p != null)
//                    {
//                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        g.drawOval(x[0], x[1], x[2], x[3]);
//                    }
//                    VarPath.p = e.getPoint();
//                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    g.drawOval(x[0], x[1], x[2], x[3]);
//                    break;
//                case GV.ARC2D:
//                    if (VarPath.p != null)
//                    {
//                        x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
//                    }
//                    VarPath.p = e.getPoint();
//                    x = a.dimension(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    g.drawArc(x[0], x[1], x[2], x[3], 30, 120);
//                    break;
//                case GV.LINE2D:
//                case GV.POLYGON:
//                    if (VarPath.p != null)
//                    {
//                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.QUADCURVE2D:
//                    if (VarPath.pointIndex == 1)
//                    {
//                        if (VarPath.p != null)
//                        {
//                            g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        }
//                        VarPath.p = e.getPoint();
//                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    } else
//                    {
//                        Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
//                        if (VarPath.p != null)
//                        {
//                            g.draw(VarPath.partialShape);
//                        }
//                        VarPath.p = e.getPoint();
//                        VarPath.partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        g.draw(VarPath.partialShape);
//                    }
//                    break;
//                case GV.CUBICCURVE2D:
//                switch (VarPath.pointIndex)
//                {
//                    case 1 ->
//                    {
//                        if (VarPath.p != null)
//                        {
//                            g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                        }
//                        VarPath.p = e.getPoint();
//                        g.drawLine(p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    case 2 ->                         {
//                            Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
//                            if (VarPath.p != null)
//                            {
//                                g.draw(VarPath.partialShape);
//                            }   VarPath.p = e.getPoint();
//                            VarPath.partialShape = new QuadCurve2D.Float(p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                            g.draw(VarPath.partialShape);
//                        }
//                    default ->                         {
//                            Point p2 = (Point) VarPath.points.get(VarPath.pointIndex - 2);
//                            Point p3 = (Point) VarPath.points.get(VarPath.pointIndex - 3);
//                            if (VarPath.p != null)
//                            {
//                                g.draw(VarPath.partialShape);
//                            }   VarPath.p = e.getPoint();
//                            VarPath.partialShape = new CubicCurve2D.Float(p3.x, p3.y + 85, p2.x, p2.y + 85, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                            g.draw(VarPath.partialShape);
//                        }
//                }
//                    break;
//
//
//                case GV.ESTRELLA:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.estrella((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.estrella((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.ESPADA:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.espada((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.espada((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.LUNA:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.luna((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.luna((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.CUBO:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.cubo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.cubo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.RAYO:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.rayo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.rayo((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.TREBOL:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.trebol((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.trebol((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.CORAZON:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.corazon((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.corazon((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.TORRE:
//                    System.out.println(VarPath.shapeType);
//                    g.setColor(VarPath.color);
//                    if (VarPath.p != null)
//                    {
//                        a.torreE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.torreE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.LETRAE:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.letraE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.letraE((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.PIRAMIDE:
//                    System.out.println(VarPath.shapeType);
//                    if (VarPath.p != null)
//                    {
//                        a.piramide((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    }
//                    VarPath.p = e.getPoint();
//                    a.piramide((Graphics2D) g, p1.x, p1.y + 85, VarPath.p.x, VarPath.p.y + 85);
//                    break;
//                case GV.SELECCION:
//                    break;
//            }
//        } else
//        {
//            Graphics2D g = (Graphics2D) panel.getGraphics();
//            g.setXORMode(Color.white);
//            if (VarPath.edicion != null)
//            {
//                AffineTransform tr = new AffineTransform();
//
//                System.out.println(e.getX() + "," + e.getY());
//                int x = e.getX();
//                int y1 = e.getY();
//                int y = e.getY();
//
//                tr.translate(x - VarPath.lx, y - VarPath.ly);
//                g.draw(tr.createTransformedShape(VarPath.edicion));
//
//                tr.translate(x - VarPath.lx, y - VarPath.ly);
//                int index = VarPath.shapes.indexOf(VarPath.edicion);
//                VarPath.edicion.setShape(tr.createTransformedShape(VarPath.edicion.getShape()));
//                if (index != -1)
//                {
//                    VarPath.shapes.set(index, VarPath.edicion);
//                }
//                VarPath.lx = x;
//                VarPath.ly = y1;
//
//                panel.repaint();
//            }
//        }
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e)
//    {
//    }
//}
