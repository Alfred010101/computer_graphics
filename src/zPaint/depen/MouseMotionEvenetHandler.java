/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zPaint.depen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
        endX = e.getX();
        endY = e.getY();
        if (getShapeTypeEnum() != GV.ShapeTypes.SELECCION_MODE)
        {

            if (getShapeTypeEnum() == ShapeTypes.LINE)
            {
                previewShape = new MyShape((int) startX, (int) startY, (int) endX, (int) endY, Color.GRAY, Color.GRAY, getShapeTypeEnum());
            } else
            {
                int width = (int) Math.abs(endX - startX);
                int height = (int) Math.abs(endY - startY);
                int x = (int) Math.min(startX, endX);
                int y = (int) Math.min(startY, endY);

                previewShape = new MyShape(x, y, width, height, Color.GRAY, Color.GRAY, getShapeTypeEnum());
            }
        } else if (isMoving && selectedShape != null)
        {
            selectedShape.x = (int) (endX - offsetX);
            selectedShape.y = (int) (endY - offsetY);
            System.out.println(selectedShape);
        } else if (resizing && selectedShape != null)
        {
            selectedShape.width = (int) Math.abs(endX - selectedShape.x);
            selectedShape.height = (int) Math.abs(endY - selectedShape.y);
        }
        panel.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
}
