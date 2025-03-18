package zPaint;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

    private final ArrayList shapes = new ArrayList();
    private static int shapeType = 16;
    public static final int RECTANGLE = 0;
    public static final int ROUNDRECTANGLE2D = 1;
    public static final int ELLIPSE2D = 2;
    public static final int ARC2D = 3;
    public static final int LINE2D = 4;
    public static final int QUADCURVE2D = 5;
    public static final int CUBICCURVE2D = 6;
    public static final int POLYGON = 7;
    public static final int GENERAL = 8;
    public static final int AREA = 9;
    public static final int ESTRELLA = 10;
    public static final int LUNA = 11;
    public static final int ESPADA = 12;
    public static final int CUBO = 13;
    public static final int PIRAMIDE = 14;
    public static final int LETRAE = 15;
    public static final int TORRE = 16;
    public static final int TREBOL = 17;
    public static final int RAYO = 18;
    public static final int CORAZON = 19;
    public static final int SELECCION = 20;

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
    public static void setShapeType(int x)
    {
        GV.shapeType = x;
    }
    public static int getShapeType()
    {
        return shapeType;
    }
}
