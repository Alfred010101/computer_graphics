//  ****************************************************
//      File: AAACh1.java
//
//      Exercises of chapter 1 course.
//
//      CAVEES 
//      BEGIN:   110225
//  ****************************************************  //
package Ch1;

import Ch1.exercises.*;
import Ch1.exercises.Hello2D;
import Ch1.exercises.Hello2DV2;
import Ch3GraphicsRenderingDetails.*;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AAACh1 extends JPanel
{
    private final JTabbedPane tabbedPane;
    private final JPanel hello2DPanel = new Hello2D();
    private final JPanel hello2DV2Panel = new Hello2DV2();
    private final JPanel ex3EllipsesSliderPanel = new Ex3EllipsesSlider();
    private final JPanel ex4RandomEllipses = new Ex4RandomEllipses();
    private final JPanel ex5AreaGeometry = new Ex5AreaGeometry();
    private final JPanel ex6Spirograph = new Ex6Spirograph();
    private final JPanel ex7Demo2D = new Ex7Demo2D();
    private final JPanel ex8DrawShapes = new Ex8DrawShapes();
    private final JPanel ex9AreaGeometry = new Ex9AreaGeometry();
    private final JPanel ex10CustomPath = new Ex10CustomPath();
    private final JPanel ex11TestColor = new TestColor();
    private final JPanel ex12TestPaints = new TestPaints();
    private final JPanel ex13TestStrokes = new TestStrokes();
    private final JPanel ex14Transformations = new Transformations();
    private final JPanel ex15Composition = new Composition();
    private final JPanel ex16Compositing = new Compositing();
    private final JPanel ex17TestClip = new TestClip();
    private final JPanel ex18FontFun = new FontFun();
    private final JPanel ex19GlyphClip = new GlyphClip();
    
    public static void main(String[] args) 
    {
        new SnippetFrame (new AAACh1(), "Exercises").setVisible(true);
    }
    
    public AAACh1()
    {   
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        
        tabbedPane.addTab ("Ex1 First Program Hello2D", hello2DPanel);
        tabbedPane.addTab ("Ex2 Hello2DV2", hello2DV2Panel);
        tabbedPane.addTab ("Ex3 EllipsesSlider", ex3EllipsesSliderPanel);
        tabbedPane.addTab ("Ex4 RandomEllipses", ex4RandomEllipses);
        tabbedPane.addTab ("Ex5 AreaGeometry", ex5AreaGeometry);
        tabbedPane.addTab ("Ex6 Spirograph", ex6Spirograph);
        tabbedPane.addTab ("Ex7 Demo2D", ex7Demo2D);
        tabbedPane.addTab ("Ex8 DrawShapes", ex8DrawShapes);
        tabbedPane.addTab ("Ex9 AreaGeometry", ex9AreaGeometry);
        tabbedPane.addTab ("Ex10 CustomPath", ex10CustomPath);
        tabbedPane.addTab ("Ex11 TestColor", ex11TestColor);
        tabbedPane.addTab ("Ex12 TestPaints", ex12TestPaints);
        tabbedPane.addTab ("Ex13 TestStrokes", ex13TestStrokes);
        tabbedPane.addTab ("Ex14 Transformations", ex14Transformations);
        tabbedPane.addTab ("Ex15 Composition", ex15Composition);
        tabbedPane.addTab ("Ex16 Compositing", ex16Compositing);
        tabbedPane.addTab ("Ex17 TestClip", ex17TestClip);
        tabbedPane.addTab ("Ex18 FontFun", ex18FontFun);
        tabbedPane.addTab ("Ex19 GlyphClip", ex19GlyphClip);
        
        tabbedPane.setSelectedIndex(0);
        
        setLayout( new GridLayout(1,1));
        
        add(tabbedPane);
    }
}
