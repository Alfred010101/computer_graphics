//  ****************************************************
//      File: AAACh1.java
//
//      Exercises of chapter 1 course.
//
//      CAVEES 
//      BEGIN:   110225
//  ****************************************************  //
package Ch1;

import Ch1.exercises.Ex3EllipsesSlider;
import Ch1.exercises.Hello2D;
import Ch1.exercises.Hello2DV2;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AAACh1 extends JPanel
{
    private final JTabbedPane tabbedPane;
    private final JPanel hello2DPanel = new Hello2D();
    private final JPanel hello2DV2Panel = new Hello2DV2();
    private final JPanel ex3EllipsesSliderPanel = new Ex3EllipsesSlider();
    
    
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
//        
        tabbedPane.setSelectedIndex(0);
        
        setLayout( new GridLayout(1,1));
        
        add(tabbedPane);
    }
}
