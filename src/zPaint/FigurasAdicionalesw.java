
package zPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Alfred
 */
public class FigurasAdicionalesw 
{
    private Color color;

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
    
    

    public int[] dimension(int x1, int y1, int x2, int y2)
    {
        int[] ord = orden(x1, y1, x2, y2);

        x1 = ord[0];
        x2 = ord[1];
        y1 = ord[2];
        y2 = ord[3];

        int ancho = Math.abs(x2 - x1);
        int alto = Math.abs(y2 - y1);
        return new int[]
        {
            x1, y1, ancho, alto
        };
    }

    public int[] orden(int x1, int y1, int x2, int y2)
    {
        if (x1 > x2)
        {
            int x = x2;
            x2 = x1;
            x1 = x;
        }
        if (y1 > y2)
        {
            int y = y2;
            y2 = y1;
            y1 = y;
        }
        return new int[]
        {
            x1, x2, y1, y2
        };
    }

    public Shape estrella(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath estrella = new GeneralPath();
        estrella.moveTo(dim[0] + dim[2] / 2, dim[1]);
        estrella.lineTo(dim[0] + dim[2] * 0.62, dim[1] + dim[3] * 0.38);

        estrella.lineTo(dim[0] + dim[2], dim[1] + dim[3] * 0.38);
        estrella.lineTo(dim[0] + dim[2] * 0.69, dim[1] + dim[3] * 0.62);

        estrella.lineTo(dim[0] + dim[2] * 0.81, dim[1] + dim[3]);
        estrella.lineTo(dim[0] + dim[2] / 2, dim[1] + dim[3] * 0.75);

        estrella.lineTo(dim[0] + dim[2] * 0.19, dim[1] + dim[3]);
        estrella.lineTo(dim[0] + dim[2] * 0.31, dim[1] + dim[3] * 0.62);

        estrella.lineTo(dim[0], dim[1] + dim[3] * 0.38);
        estrella.lineTo(dim[0] + dim[2] * 0.38, dim[1] + dim[3] * 0.38);

        estrella.closePath();
        g.draw(estrella);
        return estrella;
    }

    public Shape luna(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath luna = new GeneralPath();
        luna.moveTo(dim[0], dim[1]);

        luna.curveTo(dim[0] + dim[2] / 2, dim[1] + dim[3] / 15, dim[0] + dim[2] / 2, dim[1] + dim[3] / 15 * 14, dim[0], dim[1] + dim[3]);
        luna.curveTo(dim[0] + dim[2] / 3, dim[1] + dim[3] / 15 * 14, dim[0] + dim[2] / 3, dim[1] + dim[3] / 15, dim[0], dim[1]);

        luna.closePath();
        g.draw(luna);
        return luna;
    }

    public Shape espada(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath espada = new GeneralPath();
        espada.moveTo(dim[0] + dim[2] * .5, dim[1]);
        //Filo izquierdo
        espada.lineTo((dim[0] + dim[2] * .35), dim[1] + dim[3] * .15);

        espada.lineTo((dim[0] + dim[2] * .35), dim[1] + dim[3] * .8);
        espada.lineTo((dim[0]), dim[1] + dim[3] * .8);//Empuñadula inicio

        espada.lineTo((dim[0]), dim[1] + dim[3] * .83);
        espada.lineTo((dim[0] + dim[2] * .4), dim[1] + dim[3] * .83);

        espada.lineTo((dim[0] + dim[2] * .4), dim[1] + dim[3] * .92);
        espada.lineTo((dim[0] + dim[2] * .3), dim[1] + dim[3] * .96);

        espada.lineTo((dim[0] + dim[2] / 2), dim[1] + dim[3]);

        espada.lineTo((dim[0] + dim[2] * .7), dim[1] + dim[3] * .96);
        espada.lineTo((dim[0] + dim[2] * .6), dim[1] + dim[3] * .92);

        espada.lineTo((dim[0] + dim[2] * .6), dim[1] + dim[3] * .83);
        espada.lineTo((dim[0] + dim[2]), dim[1] + dim[3] * .83);

        espada.lineTo((dim[0] + dim[2]), dim[1] + dim[3] * .8);//Empuñadula fin
        espada.lineTo((dim[0] + dim[2] * .65), dim[1] + dim[3] * .8);//Filo Derecho

        espada.lineTo((dim[0] + dim[2] * .65), dim[1] + dim[3] * .15);

        espada.closePath();
        g.draw(espada);

        return espada;
    }

    public Shape cubo(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath cubo = new GeneralPath();
        cubo.moveTo(dim[0], dim[1] + dim[3]); //x1

        cubo.lineTo(dim[0] + dim[2] * 0.8, dim[1] + dim[3]);//x2
        cubo.lineTo(dim[0] + dim[2] * 0.8, dim[1] + dim[3] * 0.2);

        cubo.lineTo(dim[0], dim[1] + dim[3] * 0.2);//x1

        cubo.closePath();

        cubo.moveTo(dim[0], dim[1] + dim[3] * 0.2);//x2

        cubo.lineTo(dim[0] + dim[2] * 0.2, dim[1]);
        cubo.lineTo(dim[0] + dim[2], dim[1]);

        cubo.lineTo(dim[0] + dim[2], dim[1] + dim[3] * 0.8);
        cubo.lineTo(dim[0] + dim[2] * 0.8, dim[1] + dim[3]);

        cubo.moveTo(dim[0] + dim[2] * 0.8, dim[1] + dim[3] * 0.2);//x2

        cubo.lineTo(dim[0] + dim[2], dim[1]);

        g.draw(cubo);

        return cubo;
    }

    public Shape piramide(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath piramide = new GeneralPath();
        piramide.moveTo(dim[0] + dim[2] / 2, dim[1] + dim[3]); //x1

        piramide.lineTo(dim[0] + dim[2] / 2, dim[1]);
        piramide.lineTo(dim[0], dim[1] + dim[3] * 0.7);
        piramide.lineTo(dim[0] + dim[2] / 2, dim[1] + dim[3]); //x1

        piramide.lineTo(dim[0] + dim[2], dim[1] + dim[3] * 0.7);
        piramide.lineTo(dim[0] + dim[2] / 2, dim[1]);

        g.draw(piramide);

        return piramide;
    }

    public Shape letraE(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath x = new GeneralPath();
        x.moveTo(dim[0] + dim[2], dim[1]); //x1

        x.lineTo(dim[0], dim[1]);
        x.lineTo(dim[0], dim[1] + dim[3]);

        x.lineTo(dim[0] + dim[2], dim[1] + dim[3]);

        x.lineTo(dim[0] + dim[2], dim[1] + dim[3] * .8);
        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .8);

        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2], dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2], dim[1] + dim[3] * .4);
        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .4);

        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .2);
        x.lineTo(dim[0] + dim[2], dim[1] + dim[3] * .2);

        x.closePath();

        x.moveTo(dim[0] + dim[2], dim[1] + dim[3] * .2);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .05);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * -.1);
        x.lineTo(dim[0] + dim[2] * .2, dim[1] + dim[3] * -.1);
        x.lineTo(dim[0], dim[1]);

        x.moveTo(dim[0] + dim[2], dim[1]);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * -.1);

        x.moveTo(dim[0] + dim[2], dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .45);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .3);
        x.lineTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .3);
        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .4);
        x.moveTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .3);
        x.lineTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .2);

        x.moveTo(dim[0] + dim[2], dim[1] + dim[3] * .4);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .3);

        x.moveTo(dim[0] + dim[2], dim[1] + dim[3]);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .85);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .7);
        x.lineTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .7);
        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .8);
        x.moveTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .7);
        x.lineTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .6);

        x.moveTo(dim[0] + dim[2], dim[1] + dim[3] * .8);
        x.lineTo(dim[0] + dim[2] * 1.2, dim[1] + dim[3] * .7);

        g.draw(x);

        return x;
    }

    public Shape torreE(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath x = new GeneralPath();
        x.moveTo(dim[0] + dim[2] / 2, dim[1]);

        x.lineTo(dim[0] + dim[2] / 2, dim[1] + dim[3] * .1);
        x.lineTo(dim[0] + dim[2] * .55, dim[1] + dim[3] * .1);
        x.lineTo(dim[0] + dim[2] * .58, dim[1] + dim[3] * .15);
        x.lineTo(dim[0] + dim[2] * .55, dim[1] + dim[3] * .2);

        x.quadTo(dim[0] + dim[2] * .58, dim[1] + dim[3] * .4, dim[0] + dim[2] * .7, dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2] * .74, dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2] * .72, dim[1] + dim[3] * .63);
        x.lineTo(dim[0] + dim[2] * .71, dim[1] + dim[3] * .63);
        x.quadTo(dim[0] + dim[2] * .73, dim[1] + dim[3] * .67, dim[0] + dim[2] * .78, dim[1] + dim[3] * .75);
        x.lineTo(dim[0] + dim[2] * .82, dim[1] + dim[3] * .75);
        x.lineTo(dim[0] + dim[2] * .82, dim[1] + dim[3] * .79);
        x.quadTo(dim[0] + dim[2] * .86, dim[1] + dim[3] * .85, dim[0] + dim[2], dim[1] + dim[3]);
        x.lineTo(dim[0] + dim[2] * .8, dim[1] + dim[3]);

        x.quadTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .7, dim[0] + dim[2] * .2, dim[1] + dim[3]);

        x.lineTo(dim[0], dim[1] + dim[3]);
        x.quadTo(dim[0] + dim[2] * .14, dim[1] + dim[3] * .85, dim[0] + dim[2] * .18, dim[1] + dim[3] * .79);
        x.lineTo(dim[0] + dim[2] * .18, dim[1] + dim[3] * .75);
        x.lineTo(dim[0] + dim[2] * .22, dim[1] + dim[3] * .75);
        x.quadTo(dim[0] + dim[2] * .27, dim[1] + dim[3] * .67, dim[0] + dim[2] * .28, dim[1] + dim[3] * .63);
        x.lineTo(dim[0] + dim[2] * .27, dim[1] + dim[3] * .63);
        x.lineTo(dim[0] + dim[2] * .26, dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2] * .3, dim[1] + dim[3] * .6);
        x.quadTo(dim[0] + dim[2] * .42, dim[1] + dim[3] * .4, dim[0] + dim[2] * .45, dim[1] + dim[3] * .2);
//        
        x.lineTo(dim[0] + dim[2] * .42, dim[1] + dim[3] * .15);
        x.lineTo(dim[0] + dim[2] * .45, dim[1] + dim[3] * .1);
        x.lineTo(dim[0] + dim[2] / 2, dim[1] + dim[3] * .1);

        x.moveTo(dim[0] + dim[2] * .34, dim[1] + dim[3] * .75);
        x.lineTo(dim[0] + dim[2] * .66, dim[1] + dim[3] * .75);
        x.quadTo(dim[0] + dim[2] * .62, dim[1] + dim[3] * .7, dim[0] + dim[2] * .6, dim[1] + dim[3] * .63);
        x.lineTo(dim[0] + dim[2] * .4, dim[1] + dim[3] * .63);
        x.quadTo(dim[0] + dim[2] * .38, dim[1] + dim[3] * .7, dim[0] + dim[2] * .34, dim[1] + dim[3] * .75);

        g.draw(x);
        return x;
    }

    public Shape trebol(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath x = new GeneralPath();
        x.moveTo(dim[0], dim[1] + dim[3]);

        x.quadTo(dim[0] + dim[2] * .25, dim[1] + dim[3], dim[0] + dim[2] * .5, dim[1] + dim[3] * .5);
        x.quadTo(dim[0] + dim[2] * .3, dim[1] + dim[3], dim[0], dim[1] + dim[3]);
//        x.quadTo(dim[0] + dim[2] * .15, dim[1] + dim[3] * 1.10, dim[0] + dim[2] * .52, dim[1] + dim[3] * .90);
        GeneralPath y = new GeneralPath();
        y.moveTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .5);
        y.curveTo(dim[0] + dim[2] * .15, dim[1] + dim[3] * 1.10, dim[0] + dim[2] * .45, dim[1] + dim[3] * 1.1, dim[0] + dim[2] * .52, dim[1] + dim[3] * .85);
        y.curveTo(dim[0] + dim[2] * .57, dim[1] + dim[3] * 1.10, dim[0] + dim[2] * .87, dim[1] + dim[3] * 1.1, dim[0] + dim[2] * .5, dim[1] + dim[3] * .5);
        y.quadTo(dim[0] + dim[2] * .47, dim[1] + dim[3] * .7, dim[0] + dim[2] * .5, dim[1] + dim[3] * .8);
//        x.lineTo(dim[0] + dim[2] * .5, dim[1] + dim[3] * .8);

        GeneralPath combinedShape = new GeneralPath();
        for (int i = 0; i < 4; i++)
        {
            AffineTransform tr = new AffineTransform();
            tr.rotate(Math.toRadians(80 + 90 * i), dim[0] + dim[2] / 2, dim[1] + dim[3] / 2);
            Shape rotacion = tr.createTransformedShape(y);
            combinedShape.append(rotacion, false); // Añade rotacion
        }

        combinedShape.append(x, false);
        // Dibujar la figura combinada
        g.draw(combinedShape);

        return combinedShape;
    }

    public Shape rayo(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath x = new GeneralPath();

        x.moveTo(dim[0], dim[1] + dim[3]);

        x.lineTo(dim[0] + dim[2] * .4, dim[1] + dim[3] * .6);
        x.lineTo(dim[0], dim[1] + dim[3] * .6);
        x.lineTo(dim[0] + dim[2], dim[1]);
        x.lineTo(dim[0] + dim[2] * .6, dim[1] + dim[3] * .4);
        x.lineTo(dim[0] + dim[2], dim[1] + dim[3] * .4);
        x.closePath();

        g.draw(x);

        return x;
    }

    public Shape corazon(Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int[] dim = dimension(x1, y1, x2, y2);

        GeneralPath x = new GeneralPath();

        x.moveTo(dim[0] + dim[2] * .5, dim[1] + dim[3]);
        x.curveTo(dim[0] + dim[2] * .1, dim[1] + dim[3] * .4, dim[0] + dim[2] * .4, dim[1], dim[0] + dim[2] * .5, dim[1] + dim[3] * .5);
        x.curveTo(dim[0] + dim[2] * .6, dim[1], dim[0] + dim[2] * .9, dim[1] + dim[3] * .4, dim[0] + dim[2] * .5, dim[1] + dim[3]);

        g.draw(x);
        return x;
    }
}
