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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import zPaint.WorkPanel;

/**
 *
 * @author Alfred
 */
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
        startX = ev.getX();
        startY = ev.getY();
        if (getShapeTypeEnum() == ShapeTypes.SELECCION_MODE)
        {
            for (MyShape shape : myShapes)
            {
                if (shape.contains((int) startX, (int) startY))
                {
                    selectedShape = shape;
                    offsetX = (int) startX - shape.x;
                    offsetY = (int) startY - shape.y;
                    if (WorkPanel.isOnResizeHandle((int) startX, (int) startY))
                    {
                        resizing = true;
                    } else
                    {
                        isMoving = true;
                    }
                    panel.repaint();
                    return;
                } else
                {
                    selectedShape = null;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (getShapeTypeEnum() != ShapeTypes.SELECCION_MODE && previewShape != null && !isMoving && !resizing)
        {
            myShapes.add(previewShape);
            previewShape = null;
        }
        isMoving = false;
        resizing = false;
        panel.repaint();
    }
}
