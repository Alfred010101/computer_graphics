package zPaint;

import zPaint.depen.GV;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author Alfred
 */
public class Paint extends JPanel
{

    protected String PATH_RESOURCES = "src/zPaint/resources/";
    private final int MIN_LEFT_PANEL = 0;
    private final int MAX_LEFT_PANEL = 60;
    private JScrollPane leftPanel;
    private final WorkPanel centerPanel;

//    public int pointIndex = 0;
//    public ArrayList points = new ArrayList();
//    public Color color = null;
//    public Point p = null;
//    public Shape partialShape = null;
//    public int shapeType = GV.getShapeType();
//    public Figura edicion = null;
//    public double lx = 0, ly = 0;
//    public ArrayList<Figura> shapes = new ArrayList<>();
//    public boolean r = false;
//    public boolean paint = false;
//    public Color color2 = null;
//    public Paint colorD = null;

    public Paint()
    {
        setLayout(new BorderLayout());

        initScroll();

        centerPanel = new WorkPanel();
        FillStoke rightPanel = new FillStoke();

        leftPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JSplitPane splitPaneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
        splitPaneLeft.setContinuousLayout(true);
        splitPaneLeft.setOneTouchExpandable(true);
        splitPaneLeft.setDividerLocation(60);

        splitPaneLeft.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, (PropertyChangeEvent evt) ->
        {
            int currentLocation = splitPaneLeft.getDividerLocation();
            if (currentLocation < MIN_LEFT_PANEL)
            {
                splitPaneLeft.setDividerLocation(MIN_LEFT_PANEL);
            } else if (currentLocation > MAX_LEFT_PANEL)
            {
                splitPaneLeft.setDividerLocation(MAX_LEFT_PANEL);
            }
        });

        JSplitPane splitPaneRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneLeft, rightPanel);
        splitPaneRight.setContinuousLayout(true);
        splitPaneRight.setOneTouchExpandable(true);
        splitPaneRight.setDividerLocation(splitPaneRight.getWidth() - 300);

        splitPaneRight.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, (PropertyChangeEvent evt) ->
        {
            int currentLocation = splitPaneRight.getDividerLocation();
            int minRightLocation = splitPaneRight.getWidth() - 300;
            int maxRightLocation = splitPaneRight.getWidth();

            if (currentLocation < minRightLocation)
            {
                splitPaneRight.setDividerLocation(minRightLocation);
            } else if (currentLocation > maxRightLocation)
            {
                splitPaneRight.setDividerLocation(maxRightLocation);
            }
        });

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                int nuevaPosicion = splitPaneRight.getWidth() - 300;
                splitPaneRight.setDividerLocation(nuevaPosicion);
            }
        });

        add(splitPaneRight, BorderLayout.CENTER);
    }

    private void initScroll()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        initShape("Selection.png", panel, (e) ->
        {
            GV.setShapeType(GV.SELECCION);
        });

        initShape("Line.png", panel, (e) ->
        {
            GV.setShapeType(GV.LINE2D);
        });

        initShape("Rectangle.png", panel, (e) ->
        {
            GV.setShapeType(GV.RECTANGLE);
        });

        initShape("RoundRectangle.png", panel, (e) ->
        {
            GV.setShapeType(GV.ROUNDRECTANGLE2D);
        });

        initShape("Ellipse.png", panel, (e) ->
        {
            GV.setShapeType(GV.ELLIPSE2D);
        });

        initShape("Arc.png", panel, (e) ->
        {
            GV.setShapeType(GV.ARC2D);
        });

        initShape("Polygon.png", panel, (e) ->
        {
            GV.setShapeType(GV.POLYGON);
        });

        initShape("QuadCurve.png", panel, (e) ->
        {
            GV.setShapeType(GV.QUADCURVE2D);
        });

        initShape("CubicCurve.png", panel, (e) ->
        {
            GV.setShapeType(GV.CUBICCURVE2D);
        });

        leftPanel = new JScrollPane(panel);
        leftPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftPanel.getVerticalScrollBar().setUnitIncrement(16);
    }

    private void initShape(String source, JPanel panel, ActionListener e)
    {
        JButton boton = new JButton();
        boton.setIcon(new ImageIcon(PATH_RESOURCES + source));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setToolTipText(source.split("\\.")[0] + " Tool");
        boton.addActionListener(e);
        panel.add(boton);
    }
}
