package zPaint;

import zPaint.depen.Figura;
import zPaint.depen.GV;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import zPaint.depen.MouseEventHandler;
import zPaint.depen.MouseMotionEvenetHandler;

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
//        setBackground(GV.PALE_BLUE_COLOR);
//        setPreferredSize(new Dimension(640, 480));
        addMouseListener(mouseEventHandler);
        addMouseMotionListener(mouseMotionEventHandler);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Figura shape : GV.shapes)
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