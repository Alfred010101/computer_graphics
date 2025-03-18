package zPaint;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

/**
 *
 * @author Alfred
 */
public class VarPath
{

    public static int pointIndex = 0;
    public static ArrayList points = new ArrayList();
    public static Color color = null;
    public static Point p = null;
    public static Shape partialShape = null;
    public static int shapeType = GV.getShapeType();
    public static Figura edicion = null;
    public static double lx = 0;
    public static double ly = 0;
    public static ArrayList<Figura> shapes = new ArrayList<>();
    public static boolean r = false;
    public static boolean paint = false;
    public static Color color2 = null;
    public static Paint colorD = null;

}
