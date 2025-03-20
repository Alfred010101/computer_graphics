package zPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import zPaint.depen.GV;

/**
 *
 * @author Alfred
 */
public class FillStoke extends JTabbedPane
{

    private final Fill fillPanel = new Fill();
//    private final StrokePaint stokePaintPanel = new StrokePaint();
    private final ColorPanel stokePaintPanel = new ColorPanel();
    private final StrokeStyle stokeStylePanel = new StrokeStyle();

    public FillStoke()
    {
        super(JTabbedPane.TOP);
        this.addTab("Fill", fillPanel);
        this.addTab("Stoke Paint", stokePaintPanel);
        this.addTab("Stoke Style", stokeStylePanel);
        this.setSelectedIndex(0);
    }
}

class Fill extends JPanel
{

    public Fill()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el relleno
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Selector de color
        JButton colorButton = new JButton("Seleccionar Color");
        colorButton.addActionListener(e ->
        {
            Color newColor = JColorChooser.showDialog(this, "Seleccionar Color de Relleno", Color.RED);
            if (newColor != null)
            {
                setBackground(newColor);
            }
        });

        // Opciones de relleno
        JCheckBox fillCheckBox = new JCheckBox("Relleno Activo", true);
        JComboBox<String> fillTypeComboBox = new JComboBox<>(new String[]
        {
            "Sólido", "Degradado", "Patrón"
        });
        JSlider opacitySlider = new JSlider(0, 100, 100);
        opacitySlider.setMajorTickSpacing(25);
        opacitySlider.setPaintTicks(true);
        opacitySlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(colorButton);
        controlsPanel.add(fillCheckBox);
        controlsPanel.add(new JLabel("Tipo de Relleno:"));
        controlsPanel.add(fillTypeComboBox);
        controlsPanel.add(new JLabel("Opacidad:"));
        controlsPanel.add(opacitySlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

class StrokePaint extends JPanel
{

    public StrokePaint()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el trazo
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Selector de color del trazo
        JButton strokeColorButton = new JButton("Seleccionar Color del Trazo");
        strokeColorButton.addActionListener(e ->
        {
            Color newColor = JColorChooser.showDialog(this, "Seleccionar Color del Trazo", Color.BLACK);
            if (newColor != null)
            {
                setBackground(newColor);
            }
        });

        // Opciones de trazo
        JCheckBox strokeCheckBox = new JCheckBox("Trazo Activo", true);
        JComboBox<String> strokeTypeComboBox = new JComboBox<>(new String[]
        {
            "Sólido", "Degradado", "Patrón"
        });
        JSlider strokeWidthSlider = new JSlider(1, 10, 1);
        strokeWidthSlider.setMajorTickSpacing(1);
        strokeWidthSlider.setPaintTicks(true);
        strokeWidthSlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(strokeColorButton);
        controlsPanel.add(strokeCheckBox);
        controlsPanel.add(new JLabel("Tipo de Trazo:"));
        controlsPanel.add(strokeTypeComboBox);
        controlsPanel.add(new JLabel("Ancho del Trazo:"));
        controlsPanel.add(strokeWidthSlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

class StrokeStyle extends JPanel
{

    public StrokeStyle()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el estilo del trazo
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Estilo del trazo
        JComboBox<String> strokeStyleComboBox = new JComboBox<>(new String[]
        {
            "Continua", "Punteada", "Rayada"
        });
        JSlider dashPatternSlider = new JSlider(1, 10, 1);
        dashPatternSlider.setMajorTickSpacing(1);
        dashPatternSlider.setPaintTicks(true);
        dashPatternSlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(new JLabel("Estilo del Trazo:"));
        controlsPanel.add(strokeStyleComboBox);
        controlsPanel.add(new JLabel("Patrón de Guiones:"));
        controlsPanel.add(dashPatternSlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

class ColorPanel extends JPanel
{

    private JSlider redSlider, greenSlider, blueSlider;
    private JSlider red1Slider, green1Slider, blue1Slider;
    private JPanel colorBox, colorBox1, gradientBox;
    private JCheckBox gradientCheckBox;
    private JCheckBox rellenoCB;
    private boolean gradientEnabled = false;
    private int red = 0, green = 0, blue = 0;
    private int red1 = 0, green1 = 0, blue1 = 0;

    public ColorPanel()
    {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel slidersPanel = new JPanel();
        slidersPanel.setLayout(new GridLayout(3, 1));

        // Crear sliders para el primer color
        redSlider = createSlider("Red", slidersPanel);
        greenSlider = createSlider("Green", slidersPanel);
        blueSlider = createSlider("Blue", slidersPanel);

        // Panel adicional para manejar el segundo color
        JPanel extraSlidersPanel = new JPanel();
        extraSlidersPanel.setLayout(new GridLayout(3, 1));
        red1Slider = createSlider("Red 2", extraSlidersPanel);
        green1Slider = createSlider("Green 2", extraSlidersPanel);
        blue1Slider = createSlider("Blue 2", extraSlidersPanel);

        // Desactivar sliders adicionales inicialmente
        extraSlidersPanel.setVisible(false);

        // CheckBox para habilitar el degradado
        gradientCheckBox = new JCheckBox("Habilitar Degradado");
        gradientCheckBox.addActionListener((ActionEvent e) ->
        {
            gradientEnabled = gradientCheckBox.isSelected();
            extraSlidersPanel.setVisible(gradientEnabled); // Mostrar/ocultar sliders adicionales
            updateGradient(); // Actualizar el panel de degradado
            GV.paint = true;
        });
        rellenoCB = new JCheckBox("Relleno");
        rellenoCB.addActionListener((ActionEvent e) ->
        {
            GV.r = rellenoCB.isSelected();
        });

        // Panel para los sliders principales y el CheckBox
        JPanel controlsPanel = new JPanel(new BorderLayout());
        JPanel a = new JPanel(new BorderLayout());
        slidersPanel.setPreferredSize(new Dimension(80, slidersPanel.preferredSize().height));
        a.add(slidersPanel, BorderLayout.WEST);
        a.add(rellenoCB, BorderLayout.EAST);
        controlsPanel.add(a, BorderLayout.NORTH);
        controlsPanel.add(gradientCheckBox, BorderLayout.CENTER);
        controlsPanel.add(extraSlidersPanel, BorderLayout.SOUTH);

        // Cuadro para mostrar el primer color
        colorBox = new JPanel();
        colorBox.setPreferredSize(new Dimension(100, 100));

        // Cuadro para mostrar el segundo color
        colorBox1 = new JPanel();
        colorBox1.setPreferredSize(new Dimension(100, 100));

        colorBox.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Color selectedColor = JColorChooser.showDialog(null, "Elige un color", new Color(red, green, blue));
                if (selectedColor != null)
                {
                    red = selectedColor.getRed();
                    green = selectedColor.getGreen();
                    blue = selectedColor.getBlue();
                    updateSliders();
                    updateColor();
                }
            }
        });

        colorBox1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Color selectedColor = JColorChooser.showDialog(null, "Elige un color", new Color(red, green, blue));
                if (selectedColor != null)
                {
                    red1 = selectedColor.getRed();
                    green1 = selectedColor.getGreen();
                    blue1 = selectedColor.getBlue();
                    updateSliders1();
                    updateColor1();
                }
            }
        });

        // Cuadro para mostrar el degradado final
        gradientBox = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (gradientEnabled)
                {
                    Graphics2D g2d = (Graphics2D) g;
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(red, green, blue), getWidth(), getHeight(), new Color(red1, green1, blue1));
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else
                {
                    g.setColor(new Color(red, green, blue));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        gradientBox.setPreferredSize(new Dimension(200, 100));

        // Panel para organizar las cajas de color
        JPanel colorsPanel = new JPanel();
        colorsPanel.setLayout(new GridLayout(1, 3));
        colorsPanel.add(colorBox);
        colorsPanel.add(colorBox1);
        colorsPanel.add(gradientBox);

        // Agregar todo al panel principal
        add(controlsPanel, BorderLayout.NORTH);
        add(colorsPanel, BorderLayout.CENTER);

        updateColor();
        updateColor1();
    }

    private JSlider createSlider(String title, JPanel panel)
    {
        JPanel sliderPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title);
        JSlider slider = new JSlider(0, 255, 0);
        slider.addChangeListener((ChangeEvent e) ->
        {
            if (slider == redSlider)
            {
                red = slider.getValue();
            } else if (slider == greenSlider)
            {
                green = slider.getValue();
            } else if (slider == blueSlider)
            {
                blue = slider.getValue();
            } else if (slider == red1Slider)
            {
                red1 = slider.getValue();
            } else if (slider == green1Slider)
            {
                green1 = slider.getValue();
            } else if (slider == blue1Slider)
            {
                blue1 = slider.getValue();
            }
            updateColor();
            updateColor1();
            updateGradient();
        });
        sliderPanel.add(label, BorderLayout.WEST);
        sliderPanel.add(slider, BorderLayout.CENTER);
        panel.add(sliderPanel);
        return slider;
    }

    private void updateColor()
    {
        GV.color = new Color(red, green, blue);
        colorBox.setBackground(new Color(red, green, blue));
    }

    private void updateColor1()
    {
        GV.color2 = new Color(red1, green1, blue1);
        colorBox1.setBackground(new Color(red1, green1, blue1));
    }

    public void updateSliders()
    {
        blueSlider.setValue(blue);
        redSlider.setValue(red);
        greenSlider.setValue(green);
    }

    public void updateSliders1()
    {
        blue1Slider.setValue(blue1);
        red1Slider.setValue(red1);
        green1Slider.setValue(green1);
    }

    private void updateGradient()
    {
        gradientBox.repaint();
    }
}

/*class Fill extends JPanel
{

    private final JButton solidColorButton;
    private final JButton gradientButton;
    private final JButton noFillButton;
    private final JPanel previewPanel;

    public Fill()
    {
        setLayout(new BorderLayout());

        // Panel de botones para elegir tipo de relleno
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        solidColorButton = new JButton("Solid");
        gradientButton = new JButton("Gradient");
        noFillButton = new JButton("No Fill");

        buttonPanel.add(solidColorButton);
        buttonPanel.add(gradientButton);
        buttonPanel.add(noFillButton);

        // Panel de vista previa del color
        previewPanel = new JPanel();
        previewPanel.setBackground(Color.RED);
        previewPanel.setPreferredSize(new Dimension(100, 50));

        // Eventos
        solidColorButton.addActionListener(e -> chooseColor());
        noFillButton.addActionListener(e -> previewPanel.setBackground(null));

        add(buttonPanel, BorderLayout.NORTH);
        add(previewPanel, BorderLayout.CENTER);
    }

    private void chooseColor()
    {
        Color color = JColorChooser.showDialog(this, "Choose Fill Color", previewPanel.getBackground());
        if (color != null)
        {
            previewPanel.setBackground(color);
        }
    }
}

class StrokePaint extends JPanel
{

    private final JButton strokeColorButton;
    private final JPanel previewPanel;

    public StrokePaint()
    {
        setLayout(new BorderLayout());

        strokeColorButton = new JButton("Choose Stroke Color");
        previewPanel = new JPanel();
        previewPanel.setBackground(Color.BLUE);
        previewPanel.setPreferredSize(new Dimension(100, 50));

        strokeColorButton.addActionListener(e -> chooseColor());

        add(strokeColorButton, BorderLayout.NORTH);
        add(previewPanel, BorderLayout.CENTER);
    }

    private void chooseColor()
    {
        Color color = JColorChooser.showDialog(this, "Choose Stroke Color", previewPanel.getBackground());
        if (color != null)
        {
            previewPanel.setBackground(color);
        }
    }
}

class StrokeStyle extends JPanel
{

    private final JSpinner strokeWidthSpinner;
    private final JComboBox<String> strokeTypeCombo;

    public StrokeStyle()
    {
        setLayout(new GridLayout(2, 2));

        // Espesor del trazo
        JLabel strokeWidthLabel = new JLabel("Stroke Width:");
        strokeWidthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // Tipo de línea
        JLabel strokeTypeLabel = new JLabel("Stroke Type:");
        String[] strokeTypes =
        {
            "Solid", "Dashed", "Dotted"
        };
        strokeTypeCombo = new JComboBox<>(strokeTypes);

        add(strokeWidthLabel);
        add(strokeWidthSpinner);
        add(strokeTypeLabel);
        add(strokeTypeCombo);
    }
}

/*class Fill extends JPanel{

    public Fill()
    {
        setBackground(Color.red);
    } 
}

class StokePaint extends JPanel{

    public StokePaint()
    {
        setBackground(Color.BLUE);
    } 
}

class StokeStyle extends JPanel{

    public StokeStyle()
    {
        setBackground(Color.YELLOW);
    } 
}*/
