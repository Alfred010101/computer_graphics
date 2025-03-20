package zPaint.depen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author Alfred
 */
public class MyShape
{

    public int x, y, width, height;
    Color fillColor, borderColor;
    GV.ShapeTypes type;
    boolean selected = false;

    public MyShape(int x, int y, int width, int height, Color fillColor, Color borderColor, GV.ShapeTypes type)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.type = type;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.setColor(fillColor);
        switch (type)
        {
            case RECTANGLE ->
                g2d.fillRect(x, y, width, height);
            case ROUNDRECTANGLE ->
                g2d.fillRoundRect(x, y, width, height, 20, 20);
            case ELLIPSE ->
                g2d.fillOval(x, y, width, height);
            case ARC ->
                g2d.fillArc(x, y, width, height, 0, 180);
            case LINE ->
                g2d.drawLine(x, y, width, height);
            case POLYGON ->
                drawPolygon(g2d);
            case QUADCURVE2D ->
                drawQuadCurve(g2d);
            case CUBICCURVE2D ->
                drawCubicCurve(g2d);
        }
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(2));
        if (selected)
        {
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]
            {
                5
            }, 0));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x - 2, y - 2, width + 4, height + 4);
        }
    }

    private void drawPolygon(Graphics2D g2d)
    {
        int[] xPoints =
        {
            x, x + width, x + width / 2
        };
        int[] yPoints =
        {
            y + height, y + height, y
        };
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawQuadCurve(Graphics2D g2d)
    {
        QuadCurve2D q = new QuadCurve2D.Float(x, y + height, x + width / 2, y, x + width, y + height);
        g2d.draw(q);
    }

    private void drawCubicCurve(Graphics2D g2d)
    {
        CubicCurve2D c = new CubicCurve2D.Float(x, y + height, x + width / 3, y, x + 2 * width / 3, y, x + width, y + height);
        g2d.draw(c);
    }

    public boolean contains(int px, int py)
    {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }
}
