package zPaint;

import zPaint.depen.GV;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alfred
 */
public class PaintApp extends JFrame implements ActionListener
{

    private JMenuBar menuBar;
    private ToolBar toolBarPanel;
    private Paint paintPanel;

    public PaintApp()
    {
        super("Paint");
        this.setSize(new Dimension(1200, 600));
        this.setMinimumSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents()
    {
        initMenu();
        toolBarPanel = new ToolBar();
        paintPanel = new Paint();

        this.setJMenuBar(menuBar);
        this.add(toolBarPanel, BorderLayout.NORTH);
        this.add(paintPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new PaintApp().setVisible(true));
    }

    private void initMenu()
    {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem mi = new JMenuItem("New");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Open");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Save");
        mi.addActionListener(this);
        menu.add(mi);

        menu = new JMenu("Edit");
        menuBar.add(menu);
        mi = new JMenuItem("Cut");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Copy");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Paste");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Duplicate");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Delete");
        mi.addActionListener(this);
        menu.add(mi);

        menu = new JMenu("Objects");
        menuBar.add(menu);
        mi = new JMenuItem("Luna");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Espada");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Piramide");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Trebol");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Rayo");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Torre Eiffel");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Estrella");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Letra E");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Corazon");
        mi.addActionListener(this);
        menu.add(mi);

        menu = new JMenu("Path");
        menuBar.add(menu);
        mi = new JMenuItem("Union");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Difference");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Intersection");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Exclusion");
        mi.addActionListener(this);
        menu.add(mi);
        mi = new JMenuItem("Divicion");
        mi.addActionListener(this);
        menu.add(mi);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (null != command)
        {
//            switch (command)
//            {
//                case "Estrella" ->
//                    GV.setShapeType(GV.ESTRELLA);
//                case "Luna" ->
//                    GV.setShapeType(GV.LUNA);
//                case "Espada" ->
//                    GV.setShapeType(GV.ESPADA);
//                case "Piramide" ->
//                    GV.setShapeType(GV.PIRAMIDE);
//                case "Letra E" ->
//                    GV.setShapeType(GV.LETRAE);
//                case "Torre Eiffel" ->
//                    GV.setShapeType(GV.TORRE);
//                case "Trebol" ->
//                    GV.setShapeType(GV.TREBOL);
//                case "Rayo" ->
//                    GV.setShapeType(GV.RAYO);
//                case "Corazon" ->
//                    GV.setShapeType(GV.CORAZON);
//                default ->
//                {
//                }
//            }
        }
    }
}
