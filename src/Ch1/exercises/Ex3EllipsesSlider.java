package Ch1.exercises;

import Ch1.GV;
import Ch1.SnippetFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ex3EllipsesSlider extends JPanel
{
    
    private final WorkPanel workPanel;
    private final ControlPanel controlPanel;
    private int n = 10; // Número inicial de elipses
    private JSlider slider;
    private final ChangeEventHandler changeEventHandler;
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex3EllipsesSlider(), "Ellipses Slider").setVisible(true));
    }
    
    public Ex3EllipsesSlider()
    {
        super(true);
        setLayout(new BorderLayout());
        
        changeEventHandler = new ChangeEventHandler();        
        workPanel = new WorkPanel();
        controlPanel = new ControlPanel();
        
        add(workPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private class ChangeEventHandler implements ChangeListener
    {
        
        @Override
        public void stateChanged(ChangeEvent e)        
        {
            n = slider.getValue();
            System.out.println("Valor del slider cambiado a: " + n);
            workPanel.repaint();
        }
    }
    
    class WorkPanel extends JPanel    
    {
        
        public WorkPanel()        
        {
            setBackground(GV.PALE_BLUE_COLOR);
        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Ellipse2D ellipse = new Ellipse2D.Double(-50, -25, 100, 50); // Tamaño de la elipse
            g2.translate(getWidth() / 2, getHeight() / 2); // Centrar el dibujo

            System.out.println("Numero de elipses a dibujar: " + n);
            
            g2.setColor(Color.MAGENTA);
            for (int i = 0; i < n; i++)
            {
                AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI * i / n);
                Shape transformedEllipse = transform.createTransformedShape(ellipse);
                g2.draw(transformedEllipse);
            }
        }
    }
    
    class ControlPanel extends JPanel    
    {
        
        public ControlPanel()        
        {
            setBackground(GV.PALE_RED_COLOR);
            setLayout(new FlowLayout());
            
            slider = new JSlider(JSlider.HORIZONTAL, 1, 50, n); // Rango del slider
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(changeEventHandler);
            
            add(new JLabel("Numero de elipses: "));
            add(slider);
        }
    }
}
