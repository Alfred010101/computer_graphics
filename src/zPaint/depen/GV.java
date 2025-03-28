package zPaint.depen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.border.*;

public class GV
{

    public static final Color PALE_RED_COLOR = new Color(255, 228, 225);       // MistyRose
    public static final Color PALE_BLUE_COLOR = new Color(230, 230, 250);      // Lavender
    public static final Color PALE_GREEN_COLOR = new Color(240, 255, 240);     // Honeydew
    public static final Color PALE_YELLOW_COLOR = new Color(255, 255, 224);    // LightYellow
    public static final Color PALE_RANDOM_COLOR = new ColorRandom();    // LightYellow

//    private final ArrayList shapes = new ArrayList();
//    private static int shapeType = 20;
//    public static final int RECTANGLE = 0;
//    public static final int ROUNDRECTANGLE2D = 1;
//    public static final int ELLIPSE2D = 2;
//    public static final int ARC2D = 3;
//    public static final int LINE2D = 4;
//    public static final int QUADCURVE2D = 5;
//    public static final int CUBICCURVE2D = 6;
//    public static final int POLYGON = 7;
//    public static final int GENERAL = 8;
//    public static final int AREA = 9;
//    public static final int ESTRELLA = 10;
//    public static final int LUNA = 11;
//    public static final int ESPADA = 12;
//    public static final int CUBO = 13;
//    public static final int PIRAMIDE = 14;
//    public static final int LETRAE = 15;
//    public static final int TORRE = 16;
//    public static final int TREBOL = 17;
//    public static final int RAYO = 18;
//    public static final int CORAZON = 19;
//    public static final int SELECCION_MODE = 20;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static Dimension getScreenSize()
    {
        return SCREEN_SIZE;
    }

    private static final int FRAME_WIDTH = SCREEN_SIZE.width - 50;  // HORIZONTAL_GAP
    private static final int FRAME_HEIGHT = SCREEN_SIZE.height - 100; // VERTICAL_GAP
    private static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    public static Dimension getFrameSize()
    {
        return FRAME_SIZE;
    }

    private static final int PAINT2D_PANEL_HEIGHT = FRAME_HEIGHT - 50; // PAINT2D_PANEL_GAP
    private static final Dimension PAINT2D_PANEL_SIZE = new Dimension(FRAME_WIDTH, PAINT2D_PANEL_HEIGHT);

    public static Dimension getPaint2DPanelSize()
    {
        return PAINT2D_PANEL_SIZE;
    }

    public static void setPanelSize(Component component, Dimension size)
    {
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
        component.setSize(size);
    }

    // Border utility methods
    public static Border createTitledBorder(String title, Color color)
    {
        return new TitledBorder(new LineBorder(color, 2, true), title);
    }

    public static Border createCompoundBorder(String title, Color color)
    {
        return new CompoundBorder(new LineBorder(color, 2), new TitledBorder(title));
    }

//    public static void setShapeType(int x)
//    {
//        GV.shapeType = x;
//    }
//
//    public static int getShapeType()
//    {
//        return shapeType;
//    }

    //Mis variables
    public static int pointIndex = 0;
    public static ArrayList points = new ArrayList();
    public static Color color = null;
    public static Point p = null;
    public static Shape partialShape = null;
//    public static int shapeType = GV.getShapeType();
    public static Figura edicion = null;
    public static ArrayList<Figura> shapes = new ArrayList<>();
    public static boolean r = false;
    public static boolean paint = false;
    public static Color color2 = null;
    public static Paint colorD = null;

    //figura a dibujar
    public static double startX = 0;
    public static double startY = 0;
    public static double endX = 0;
    public static double endY = 0;
    public static MyShape previewShape = null;
    public static ArrayList<MyShape> myShapes = new ArrayList<>();

    public static enum ShapeTypes
    {
        SELECCION_MODE,
        LINE,
        RECTANGLE,
        ROUNDRECTANGLE,
        ELLIPSE,
        ARC,
        POLYGON,
        QUADCURVE2D,
        CUBICCURVE2D,
        //Propias
        ESTRELLA,
        LUNA,
        ESPADA,
        CUBO,
        PIRAMIDE,
        LETRAE,
        TORRE,
        TREBOL,
        RAYO,
        CORAZON
    }

    private static ShapeTypes shapeTypeEnum = ShapeTypes.SELECCION_MODE;

    public static void setShapeTypeEnum(ShapeTypes shapeTypes)
    {
        shapeTypeEnum = shapeTypes;
    }

    public static ShapeTypes getShapeTypeEnum()
    {
        return shapeTypeEnum;
    }

    public static MyShape selectedShape = null;
    public static int offsetX, offsetY;
    public static boolean isMoving = false;
//    public static boolean selectionMode = false;
    public static boolean resizing = false;

}
