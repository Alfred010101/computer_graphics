package zPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import zPaint.depen.Figura;
import zPaint.depen.GV;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import zPaint.depen.GV.ShapeTypes;
import static zPaint.depen.GV.getShapeTypeEnum;
import static zPaint.depen.GV.myShapes;
import static zPaint.depen.GV.previewShape;
import static zPaint.depen.GV.selectedShape;
import static zPaint.depen.GV.shapes;
import zPaint.depen.MouseEventHandler;
import zPaint.depen.MouseMotionEvenetHandler;
import zPaint.depen.MyShape;

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
        addMouseListener(mouseEventHandler);
        addMouseMotionListener(mouseMotionEventHandler);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (MyShape shape : myShapes)
        {
            shape.draw(g2);
        }
        if (previewShape != null)
        {
            previewShape.draw(g2);
        }
        if (selectedShape != null && ShapeTypes.SELECCION_MODE == getShapeTypeEnum())
        {
            g2.setColor(Color.BLACK);
            float[] dashPattern =
            {
                5, 5
            };
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
            g2.drawRect(selectedShape.x - 2, selectedShape.y - 2, selectedShape.width + 4, selectedShape.height + 4);

            g2.setColor(Color.BLUE);
            g2.fillRect(selectedShape.x + selectedShape.width - 5, selectedShape.y + selectedShape.height - 5, 10, 10);
        }
    }

    public static boolean isOnResizeHandle(int px, int py)
    {
        if (selectedShape == null)
        {
            return false;
        }
        int handleSize = 8;
        return (px >= selectedShape.x + selectedShape.width - handleSize && px <= selectedShape.x + selectedShape.width + handleSize
                && py >= selectedShape.y + selectedShape.height - handleSize && py <= selectedShape.y + selectedShape.height + handleSize);
    }
}
