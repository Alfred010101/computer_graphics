
package zPaint;

import java.awt.Shape;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Alfred
 */
public class Figura implements Shape
{

    private Shape shape; // Forma base (ejemplo: Rectangulo2D, Ellipse2D, etc.)
    private Color color; // Color del borde
    private Color fillColor; // Color de relleno
    private float transparency; // Nivel de transparencia (0.0f a 1.0f)
    private Stroke stroke;
    private Paint paint;

    public Figura(Shape shape, Color color, Color fillColor, float transparency)
    {
        this.shape = shape;
        this.color = color;
        this.fillColor = fillColor;
        this.transparency = transparency;
    }

    public Figura(Color fillColor, Shape shape, Stroke stroke)
    {
        this.shape = shape;
        this.fillColor = fillColor;
        this.stroke = stroke;
    }

    public Figura(Shape shape, Color color, Stroke stroke)
    {
        this.shape = shape;
        this.color = color;
        this.stroke = stroke;
    }
    
    public Figura(Shape shape, Paint color, Stroke stroke)
    {
        this.shape = shape;
        this.paint = color;
        this.stroke = stroke;
    }

    public Figura(Shape shape, Paint color, Stroke stroke, Color fill)
    {
        this.shape = shape;
        this.paint = color;
        this.stroke = stroke;
        this.fillColor = fill;
    }

    public Paint getPaint()
    {
        return paint;
    }

    public void setPaint(Paint paint)
    {
        this.paint = paint;
    }

    // Implementación de métodos de la interfaz Shape
    @Override
    public Rectangle getBounds()
    {
        return shape.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        return shape.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y)
    {
        return shape.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h)
    {
        return shape.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r)
    {
        return shape.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h)
    {
        return shape.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r)
    {
        return shape.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at)
    {
        return shape.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness)
    {
        return shape.getPathIterator(at, flatness);
    }

    // Métodos adicionales para manejar propiedades visuales
    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getFillColor()
    {
        return fillColor;
    }

    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
    }

    public float getTransparency()
    {
        return transparency;
    }

    public void setTransparency(float transparency)
    {
        this.transparency = transparency;
    }

    // Método para dibujar la figura
    public void draw(Graphics2D g2d)
    {
        Composite originalComposite = g2d.getComposite();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));

        if (fillColor != null)
        {
            g2d.setColor(fillColor);
            g2d.fill(shape);
        }

        if (color != null)
        {
            g2d.setColor(color);
            g2d.draw(shape);
        }

        g2d.setComposite(originalComposite);
    }
    
    public Shape getShape()
    {
        return this.shape;
    }
    
    public void setShape(Shape s)
    {
        this.shape = s;
    }

    public Stroke getStroke()
    {
        return stroke;
    }

    public void setStroke(Stroke stroke)
    {
        this.stroke = stroke;
    }
    
    
}
